package xyz.gandolfi.aoc20.day25;

import xyz.gandolfi.aoc20.Utils;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> inputLines = Utils.getInputFileLines("day25.txt");
        assert inputLines != null;

        int cardPubKey = Integer.parseInt(inputLines.get(0));
        int doorPubKey = Integer.parseInt(inputLines.get(1));

        System.out.print("Day 25a: ");
        int cardLoopSize = LoopSizeFinder.findLoopSize(cardPubKey, 7);
        int doorLoopSize = LoopSizeFinder.findLoopSize(doorPubKey, 7);
        long cardEncryptionKey = LoopSizeFinder.findEncryptionKey(cardPubKey, doorLoopSize);
        long doorEncryptionKey = LoopSizeFinder.findEncryptionKey(doorPubKey, cardLoopSize);
        assert cardEncryptionKey == doorEncryptionKey;
        System.out.println(cardEncryptionKey);

        System.out.print("Day 25b: ");
        System.out.println("No part B for this day");
    }
}
