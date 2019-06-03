package main.java.com.thoughmachine.model;

import main.java.com.thoughmachine.exception.InvalidInputException;

public class Bid {
    private Long timestamp;
    private int userId;
    private String itemId;
    private double bidAmount;

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

    public Bid parseItemFromString(String inputLine) throws InvalidInputException {
        Bid bid;
        String[] inputArray = inputLine.split("\\|");

        if (inputArray.length == 5) {
            bid = new Bid();
            bid.setTimestamp(Long.parseLong(inputArray[0]));
            bid.setUserId(Integer.parseInt(inputArray[1]));
            bid.setItemId(inputArray[3]);
            bid.setBidAmount(Double.parseDouble(inputArray[4]));
        } else {
            throw new InvalidInputException("Problem occurred during parsing bids");
        }

        return bid;
    }
}
