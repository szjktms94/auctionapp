package main.java.com.thoughmachine;

import main.java.com.thoughmachine.exception.InvalidInputException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try {
            AuctionRunner auctionRunner = new AuctionRunner("input.txt");
            String auctionResults = auctionRunner.printAuctionResults();
            System.out.println(auctionResults);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IOException: " + e.getMessage());
        } catch (InvalidInputException e) {
            e.printStackTrace();
            System.out.println("Parse exception: " + e.getMessage());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            System.out.println("Number format exception: " + e.getMessage());
        }

    }
}

