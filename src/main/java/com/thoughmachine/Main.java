package main.java.com.thoughmachine;

import main.java.com.thoughmachine.model.AuctionResult;
import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        AuctionRunner auctionRunner = new AuctionRunner();
        auctionRunner.parseAndInitializeFromFile("input.txt");
        List<AuctionResult> auctionResults = auctionRunner.runAuction();
        System.out.println("finish"); //modify to be printed by the given form
        }
    }

