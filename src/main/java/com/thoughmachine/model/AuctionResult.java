package main.java.com.thoughmachine.model;

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
}
