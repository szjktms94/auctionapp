package main.java.com.thoughmachine.modell;

/*timestamp|user_id|action|item|reserve_price|close_time

        `timestamp` will be an integer representing a unix epoch time and is the auction start time,
        `user_id` is an integer user id
        `action` will be the string "SELL"
        `item` is a unique string code for that item.
        `reserve_price` is a decimal representing the item reserve price in the site's local currency.
        `close_time` will be an integer representing a unix epoch time*/

public class SellingItem {
    private Long timestamp;
    private int userId;
    private final String ACTION = "SELL";
    private double reservePrice;
    private Long closeTime;

    public SellingItem(Long timestamp, int userId, double reservePrice, Long closeTime) {
        this.timestamp = timestamp;
        this.userId = userId;
        this.reservePrice = reservePrice;
        this.closeTime = closeTime;
    }

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

    public String getACTION() {
        return ACTION;
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

    public static SellingItem parseItemFromStringArray(String inputLine) {
        SellingItem sellingItem = new SellingItem();
        String[] inputArray = inputLine.split("|");

        if (inputArray.length == 6) {
            SellingItem sellItem = new SellingItem();
            sellItem.setTimestamp(Long.parseLong(inputArray[0]));
            sellItem.setUserId(Integer.parseInt(inputArray[1]));
            sellItem.setReservePrice(Double.parseDouble(inputArray[3]));
            sellItem.setCloseTime(Long.parseLong(inputArray[4]));
        } else {
            return null;
        }

        return sellingItem;
    }
}