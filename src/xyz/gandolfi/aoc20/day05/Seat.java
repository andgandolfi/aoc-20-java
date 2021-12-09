package xyz.gandolfi.aoc20.day05;

import java.util.Arrays;

public class Seat {
    private final int row;
    private final int column;

    public Seat(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public Seat(String encodedSeat) {
        row = Arrays.stream(encodedSeat.split(""))
                .limit(7)
                .map(s -> s.equals("F") ? 0 : 1)
                .reduce(0, (acc, x) -> acc * 2 + x);
        column = Arrays.stream(encodedSeat.split(""))
                .skip(7)
                .map(s -> s.equals("L") ? 0 : 1)
                .reduce(0, (acc, x) -> acc * 2 + x);
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getSeatId() {
        return row * 8 + column;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "row=" + row +
                ", column=" + column +
                '}';
    }
}
