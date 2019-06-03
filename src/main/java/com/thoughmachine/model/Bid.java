package main.java.com.thoughmachine.model;

/*timestamp|user_id|action|item|bid_amount

        `timestamp` will be an integer representing a unix epoch time and is the time of the bid,
        `user_id` is an integer user id
        `action` will be the string "BID"
        `item` is a unique string code for that item.
        `bid_amount` is a decimal representing a bid in the auction site's local currency.*/

public class Bid {
    private Long timestamp;
    private int userId;
    private final String ACTION = "BID";
    private String itemId;
    private double bidAmount;

    public Bid(Long timestamp, int userId, String itemId, double bidAmount) {
        this.timestamp = timestamp;
        this.userId = userId;
        this.itemId = itemId;
        this.bidAmount = bidAmount;
    }

    public Bid() {
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getACTION() {
        return ACTION;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public double getBidAmount() {
        return bidAmount;
    }

    public void setBidAmount(double bidAmount) {
        this.bidAmount = bidAmount;
    }

    public Bid parseItemFromString(String inputLine) {
        Bid bid;
        String[] inputArray = inputLine.split("\\|");

        if (inputArray.length == 5) {
            bid = new Bid();
            bid.setTimestamp(Long.parseLong(inputArray[0]));
            bid.setUserId(Integer.parseInt(inputArray[1]));
            bid.setItemId(inputArray[3]);
            bid.setBidAmount(Double.parseDouble(inputArray[4]));
        } else {
            return null;
        }

        return bid;
    }
}
