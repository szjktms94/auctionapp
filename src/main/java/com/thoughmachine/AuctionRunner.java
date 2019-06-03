package main.java.com.thoughmachine;

import main.java.com.thoughmachine.exception.InvalidInputException;
import main.java.com.thoughmachine.model.AuctionResult;
import main.java.com.thoughmachine.model.Bid;
import main.java.com.thoughmachine.model.HeartbeatMessage;
import main.java.com.thoughmachine.model.SellingItem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class AuctionRunner {
    private List<SellingItem> sellingItems;
    private List<Bid> bids;
    private List<HeartbeatMessage> heartbeatMessages;
    private List<AuctionResult> auctionResults;

    public AuctionRunner(String inputFilePath) throws IOException, InvalidInputException {
        sellingItems = new ArrayList<>();
        bids = new ArrayList<>();
        heartbeatMessages = new ArrayList<>();
        auctionResults = new ArrayList<>();

        parseAndInitializeFromFile(inputFilePath);
    }

    private void parseAndInitializeFromFile(String inputFilePath) throws IOException, InvalidInputException {
        BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
        String currentLine;

        while ((currentLine = reader.readLine()) != null) {
            if(currentLine.contains("SELL")) {
                SellingItem sellItem = new SellingItem().parseItemFromString(currentLine);
                sellingItems.add(sellItem);
            } else if(currentLine.contains("BID")) {
                Bid bid = new Bid();
                bids.add(bid.parseItemFromString(currentLine));
            } else {
                HeartbeatMessage heartbeatMessage = new HeartbeatMessage();
                heartbeatMessages.add(heartbeatMessage.parseItemFromString(currentLine));
            }
        }
        reader.close();
    }

    private void runAuction() throws InvalidInputException {

        for (int i = 0; i < sellingItems.size(); i++){
            int finalI = i;
            if (isAuctionFinished(sellingItems.get(i).getItemId())) {
                List<Bid> orderedBids = bids.stream().filter(x -> sellingItems.get(finalI).getItemId().equals(x.getItemId()))
                        .sorted(Comparator.comparingDouble(Bid::getBidAmount))
                        .collect(Collectors.toList());

                List<Bid> validBids = orderedBids.stream().filter(x -> sellingItems.get(finalI).getTimestamp() <= x.getTimestamp()
                        && sellingItems.get(finalI).getCloseTime() >= x.getTimestamp()
                        && sellingItems.get(finalI).getReservePrice() <= x.getBidAmount()).collect(Collectors.toList());

                AuctionResult auctionResult = new AuctionResult();

                Bid winnerBid = null;
                Bid highestBid = null;
                Bid lowestBid = null;

                if(!orderedBids.isEmpty()) {
                    highestBid = orderedBids.get(orderedBids.size()-1);
                    lowestBid = orderedBids.get(0);
                }

                if(!validBids.isEmpty()) {
                    winnerBid = validBids.get(validBids.size()-1);
                }
                if(winnerBid != null) {
                    auctionResult.setPricePaid(validBids.size() >= 2 ? validBids.get(validBids.size()-2).getBidAmount() : winnerBid.getBidAmount()); //second big price
                    auctionResult.setUserId(winnerBid.getUserId());
                    auctionResult.setHighestBid(winnerBid.getBidAmount());
                    auctionResult.setStatus("SOLD");
                } else {
                    auctionResult.setPricePaid(0);
                    auctionResult.setStatus("UNSOLD");
                }

                auctionResult.setHighestBid(highestBid.getBidAmount());
                auctionResult.setLowestBid(lowestBid.getBidAmount());
                auctionResult.setCloseTime(sellingItems.get(i).getCloseTime());
                auctionResult.setItemId(sellingItems.get(i).getItemId());
                auctionResult.setTotalBidCount(validBids.size());
                auctionResults.add(auctionResult);
            } else {
                throw new InvalidInputException("There is unfinished auction in the input file");
            }

        }
    }

    private boolean isAuctionFinished(String itemId) {
        SellingItem sellingItem = sellingItems.stream().filter(x -> itemId.equals(x.getItemId())).findAny().get();

        List<Bid> bidsByTimestamp = bids.stream().filter(x -> itemId.equals(x.getItemId()))
                .sorted(Comparator.comparingDouble(Bid::getTimestamp))
                .collect(Collectors.toList());

        List<HeartbeatMessage> heartbeatMessagesByTimestamp = heartbeatMessages.stream()
                .sorted(Comparator.comparingLong(HeartbeatMessage::getTimestamp))
                .collect(Collectors.toList());

        Long biggestTimestampofBids = 0L;
        Long biggestTimestampofHeartBeatMessages = 0L;

        if (!bidsByTimestamp.isEmpty()) {
            biggestTimestampofBids = bidsByTimestamp.get(bidsByTimestamp.size()-1).getTimestamp();
        }

        if (!heartbeatMessagesByTimestamp.isEmpty()) {
            biggestTimestampofHeartBeatMessages = heartbeatMessagesByTimestamp.get(heartbeatMessagesByTimestamp.size()-1).getTimestamp();
        }

        return biggestTimestampofBids >= sellingItem.getCloseTime() || biggestTimestampofHeartBeatMessages >= sellingItem.getCloseTime();
    }

    public String printAuctionResults () throws InvalidInputException {
        runAuction();

        StringBuilder stringBuilder = new StringBuilder();
        NumberFormat ft = new DecimalFormat("#0.00");
        for (AuctionResult a : auctionResults) {
            if(a.getStatus().equals("SOLD")) {
            stringBuilder.append(a.getCloseTime() + "|" + a.getItemId() + "|"
                    + a.getUserId()+ "|" + a.getStatus() + "|" + ft.format(a.getPricePaid()) + "|"
                    + a.getTotalBidCount() + "|" + ft.format(a.getHighestBid()) + "|" + ft.format(a.getLowestBid()) + "\n");
            } else {
                stringBuilder.append(a.getCloseTime() + "|" + a.getItemId() + "|"
                        + "|" + a.getStatus() + "|" + ft.format(a.getPricePaid()) + "|"
                        + a.getTotalBidCount() + "|" + ft.format(a.getHighestBid()) + "|" + ft.format(a.getLowestBid()) + "\n");
            }
        }

        return stringBuilder.toString();
    }
}
