package main.java.com.thoughmachine.modell;

//close_time|item|user_id|status|price_paid|total_bid_count|highest_bid|lowest_bid
//`close_time` should be a unix epoch of the time the auction finished
//        `item` is the unique string item code.
//        `user_id` is the integer id of the winning user, or blank if the item did not sell.
//        `status` should contain either "SOLD" or "UNSOLD" depending on the auction outcome.
//        `price_paid` should be the price paid by the auction winner (0.00 if the item is UNSOLD), as a
//        number to two decimal places
//        `total_bid_count` should be the number of bids received for the item.
//        'highest_bid' the highest bid received for the item as a number to two decimal places
//        `lowest_bid` the lowest bid placed on the item as a number to two decimal places

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class AuctionResult {
    private Long closeTime;
    private String itemId;
    private int userId;
    private String status;
    private double pricePaid;
    private int totalBidCount;
    private double highestBid;
    private double lowestBid;

    public AuctionResult() {
    }

    public Long getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Long closeTime) {
        this.closeTime = closeTime;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getPricePaid() {
        return pricePaid;
    }

    public void setPricePaid(double pricePaid) {
        this.pricePaid = pricePaid;
    }

    public int getTotalBidCount() {
        return totalBidCount;
    }

    public void setTotalBidCount(int totalBidCount) {
        this.totalBidCount = totalBidCount;
    }

    public double getHighestBid() {
        return highestBid;
    }

    public void setHighestBid(double highestBid) {
        this.highestBid = highestBid;
    }

    public double getLowestBid() {
        return lowestBid;
    }

    public void setLowestBid(double lowestBid) {
        this.lowestBid = lowestBid;
    }

    //optimalize to analyze the type of the collection
    public List<AuctionResult> runAuction(List<SellingItem> sellingItems, List<Bid> bids, List<HeartbeatMessage> heartbeatMessages) {
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
