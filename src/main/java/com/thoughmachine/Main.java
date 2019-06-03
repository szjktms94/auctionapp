package main.java.com.thoughmachine;

import main.java.com.thoughmachine.modell.AuctionResult;
import main.java.com.thoughmachine.modell.Bid;
import main.java.com.thoughmachine.modell.HeartbeatMessage;
import main.java.com.thoughmachine.modell.SellingItem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        //START parsing string input
        String file ="input.txt";

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String currentLine;

        List<SellingItem> sellingItems = new ArrayList<>();
        List<Bid> bids = new ArrayList<>();
        List<HeartbeatMessage> heartbeatMessages = new ArrayList<>();

        while ((currentLine = reader.readLine()) != null) {
                if(currentLine.contains("SELL")) {
                    SellingItem sellItem = new SellingItem().parseItemFromStringArray(currentLine);
                    sellingItems.add(sellItem);
                }
                if(currentLine.contains("BID")) {
                    Bid bid = new Bid();
                    bids.add(bid.parseItemFromStringArray(currentLine));
                } else {
                    HeartbeatMessage heartbeatMessage = new HeartbeatMessage();
                    heartbeatMessages.add(heartbeatMessage.parseItemFromStringArray(currentLine));
                }
            }
        reader.close();
        // END parsing string input

        //START calculate auction result
        AuctionResult auctionResult = new AuctionResult();
        auctionResult.runAuction(sellingItems,bids,heartbeatMessages);
        //END calculate auction result

        //Print the result
        }
    }

