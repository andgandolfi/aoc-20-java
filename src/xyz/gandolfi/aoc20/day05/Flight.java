package xyz.gandolfi.aoc20.day05;

import java.util.Arrays;
import java.util.List;

public class Flight {
    private final List<Seat> seats;
    private final int[][] seatsMatrix;

    public Flight(List<Seat> seats) {
        this.seats = seats;
        this.seatsMatrix = new int[128][8];

        for (Seat s : seats)
            seatsMatrix[s.getRow()][s.getColumn()] = 1;
    }

    public Seat getMyEmptySeat() {
        boolean valid = false;
        for (int i = 0; i < 128; ++i) {
            int rowSeats = sumRowSeats(i);
            if (rowSeats == 8) valid = true;
            if (valid && rowSeats == 7)
                for (int j = 0; j < 7; ++j)
                    if (seatsMatrix[i][j] == 0)
                        return new Seat(i, j);
        }
        return null;
    }

    private int sumRowSeats(int rowIdx) {
        return Arrays.stream(seatsMatrix[rowIdx]).sum();
    }
}
