package xyz.gandolfi.aoc20.day11;

import xyz.gandolfi.aoc20.Utils;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> inputLines = Utils.getInputFileLines("day11.txt");
        assert inputLines != null;

        System.out.print("Day 11a: ");
        Seats seatsA = new Seats(inputLines);
        seatsA.evolveUntilStable();
        System.out.println(seatsA.getOccupiedSeats());

        System.out.print("Day 11b: ");
        Seats seatsB = new Seats(inputLines);
        seatsB.evolveLongRangeUntilStable();
        System.out.println(seatsB.getOccupiedSeats());
    }
}
