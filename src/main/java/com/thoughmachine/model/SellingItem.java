package main.java.com.thoughmachine.model;

import main.java.com.thoughmachine.exception.InvalidInputException;

public class SellingItem {
    private Long timestamp;
    private int userId;
    private String itemId;
    private double reservePrice;
    private Long closeTime;

    public SellingItem() {
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

    public double getReservePrice() {
        return reservePrice;
    }

    public void setReservePrice(double reservePrice) {
        this.reservePrice = reservePrice;
    }

    public Long getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Long closeTime) {
        this.closeTime = closeTime;
    }

    public SellingItem parseItemFromString(String inputLine) throws InvalidInputException {
        SellingItem sellingItem;
        String[] inputArray = inputLine.split("\\|");

        if (inputArray.length == 6) {
            sellingItem = new SellingItem();
            sellingItem.setTimestamp(Long.parseLong(inputArray[0]));
            sellingItem.setUserId(Integer.parseInt(inputArray[1]));
            sellingItem.setItemId(inputArray[3]);
            sellingItem.setReservePrice(Double.parseDouble(inputArray[4]));
            sellingItem.setCloseTime(Long.parseLong(inputArray[5]));
        } else {
            throw new InvalidInputException("Problem occurred during parsing selling items");
        }

        return sellingItem;
    }
}
