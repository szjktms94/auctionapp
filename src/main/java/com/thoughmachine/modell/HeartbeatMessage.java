package main.java.com.thoughmachine.modell;

public class HeartbeatMessage {
    Long timestamp;

    public HeartbeatMessage(Long timestamp) {
        this.timestamp = timestamp;
    }

    public HeartbeatMessage() {
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public HeartbeatMessage parseItemFromStringArray(String inputLine) {
        HeartbeatMessage heartbeatMessage;
        String[] inputArray = inputLine.split("\\|");

        if (inputArray.length == 1) {
            heartbeatMessage = new HeartbeatMessage();
            heartbeatMessage.setTimestamp(Long.parseLong(inputArray[0]));
        } else {
            return null;
        }

        return heartbeatMessage;
    }
}