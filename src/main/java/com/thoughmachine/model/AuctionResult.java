package main.java.com.thoughmachine.model;

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
