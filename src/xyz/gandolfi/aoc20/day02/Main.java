package xyz.gandolfi.aoc20.day02;

import xyz.gandolfi.aoc20.Utils;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> inputLines = Utils.getInputFileLines("day02.txt");
        assert inputLines != null;

        PasswordsDb db = new PasswordsDb(inputLines);

        System.out.print("Day 02a: ");
        System.out.println(db.getValidOccurrences().size());

        System.out.print("Day 02b: ");
        System.out.println(db.getValidPositions().size());
    }
}
