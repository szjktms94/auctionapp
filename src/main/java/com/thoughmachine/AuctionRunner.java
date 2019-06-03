package main.java.com.thoughmachine;

import main.java.com.thoughmachine.model.AuctionResult;
import main.java.com.thoughmachine.model.Bid;
import main.java.com.thoughmachine.model.HeartbeatMessage;
import main.java.com.thoughmachine.model.SellingItem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

//AuctionHelper???
public class AuctionRunner {
    private List<SellingItem> sellingItems;
    private List<Bid> bids;
    private List<HeartbeatMessage> heartbeatMessages;

    public AuctionRunner() {
        sellingItems = new ArrayList<>();
        bids = new ArrayList<>();
        heartbeatMessages = new ArrayList<>();
    }

    public void parseAndInitializeFromFile(String inputFilePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
        String currentLine;

        while ((currentLine = reader.readLine()) != null) {
            if(currentLine.contains("SELL")) {
                SellingItem sellItem = new SellingItem().parseItemFromString(currentLine);
                sellingItems.add(sellItem);
            }
            if(currentLine.contains("BID")) {
                Bid bid = new Bid();
                bids.add(bid.parseItemFromString(currentLine));
            } else {
                HeartbeatMessage heartbeatMessage = new HeartbeatMessage();
                heartbeatMessages.add(heartbeatMessage.parseItemFromString(currentLine));
            }
        }
        reader.close();
    }

    public List<AuctionResult> runAuction() {
        List<AuctionResult> auctionResults = new ArrayList<>();

        for (int i = 0; i < sellingItems.size(); i++){
            int finalI = i;
            List<Bid> bidsByItem = bids.stream().filter(x -> sellingItems.get(finalI).getItemId().equals(x.getItemId()))
                    .filter(x -> sellingItems.get(0).getTimestamp() <= x.getTimestamp()
                            && sellingItems.get(0).getCloseTime() >= x.getTimestamp())
                    .collect(Collectors.toList());

            Bid winnerBid = bidsByItem.stream().max(Comparator.comparing(Bid::getBidAmount)).get();
            double lowestBid = bidsByItem.stream().min(Comparator.comparing(Bid::getBidAmount)).get().getBidAmount();

            AuctionResult auctionResult = new AuctionResult();
            auctionResult.setCloseTime(sellingItems.get(i).getCloseTime());
            auctionResult.setItemId(sellingItems.get(i).getItemId());
            auctionResult.setUserId(winnerBid.getUserId());
            auctionResult.setPricePaid(winnerBid.getBidAmount()); //second big price
            auctionResult.setTotalBidCount(bidsByItem.size());
            auctionResult.setHighestBid(winnerBid.getBidAmount());
            auctionResult.setLowestBid(lowestBid);
            auctionResults.add(auctionResult);
        }
        return auctionResults;
    }
}
