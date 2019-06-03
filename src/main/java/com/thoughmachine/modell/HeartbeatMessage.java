package main.java.com.thoughmachine.modell;

public class HeartbeatMessage {
    Long timestamp;

    public HeartbeatMessage(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}