package main.java.com.thoughmachine;

import main.java.com.thoughmachine.modell.SellingItem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        String file ="input.txt";

        BufferedReader reader = new BufferedReader(new FileReader(file));
        StringBuilder sb = new StringBuilder();

        String currentLine;

        List<SellingItem> sellingItems = new ArrayList<>();

        while ((currentLine = reader.readLine()) != null) {
                if(currentLine.contains("SELL")) {
                    SellingItem sellItem = SellingItem.parseItemFromStringArray(currentLine);
                    sellingItems.add(sellItem);
                }
                if(currentLine.contains("BID")) {
                    sb.append(currentLine);
                    sb.append("BIIIIDIDDDD");
                } else {
                    sb.append(currentLine);
                    sb.append("HEARTBEAAAT");
                }
            }
        reader.close();

        System.out.println(sb.toString());

        }
    }

