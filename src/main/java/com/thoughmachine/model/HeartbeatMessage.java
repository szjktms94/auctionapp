package main.java.com.thoughmachine.model;

import main.java.com.thoughmachine.exception.InvalidInputException;

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

    public HeartbeatMessage parseItemFromString(String inputLine) throws InvalidInputException {
        HeartbeatMessage heartbeatMessage;
        String[] inputArray = inputLine.split("\\|");

        if (inputArray.length == 1) {
            heartbeatMessage = new HeartbeatMessage();
            heartbeatMessage.setTimestamp(Long.parseLong(inputArray[0]));
        } else {
            throw new InvalidInputException("Problem occurred  during parsing heartbeat messages");
        }

        return heartbeatMessage;
    }
}