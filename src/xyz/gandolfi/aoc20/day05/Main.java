package xyz.gandolfi.aoc20.day05;

import xyz.gandolfi.aoc20.Utils;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> inputLines = Utils.getInputFileLines("day05.txt");
        assert inputLines != null;

        List<Seat> inputSeats = inputLines.stream().map(Seat::new).toList();

        System.out.print("Day 05a: ");
        int maxSeatId = inputSeats.stream()
            .map(Seat::getSeatId)
            .max(Integer::compare)
            .get();
        System.out.println(maxSeatId);

        System.out.print("Day 05b: ");
        Flight flight = new Flight(inputSeats);
        Seat myEmptySeat = flight.getMyEmptySeat();
        System.out.println(myEmptySeat.getSeatId());
    }
}
