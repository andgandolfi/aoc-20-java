package xyz.gandolfi.aoc20.day11;

import java.util.List;

public class Seats {
    private char[][] currSeats;

    public Seats(List<String> inputLines) {
        int rows = inputLines.size();
        int columns = inputLines.get(0).length();
        currSeats = new char[rows][columns];
        for (int r = 0; r < rows; ++r)
            for (int c = 0; c < columns; ++c)
                currSeats[r][c] = inputLines.get(r).charAt(c);
    }

    public boolean evolveOneStep(boolean longRange) {
        char[][] currMatrix = currSeats;
        int rows = currMatrix.length;
        int columns = currMatrix[0].length;
        char[][] nextMatrix = new char[rows][columns];
        int freeSeatTolerance = longRange ? 5 : 4;

        for (int r = 0; r < rows; ++r)
            for (int c = 0; c < columns; ++c)
                nextMatrix[r][c] = switch (currMatrix[r][c]) {
                    case '.' -> '.';
                    case 'L' -> getOccupiedSeatsAround(currMatrix, r, c, longRange) == 0 ? '#' : 'L';
                    case '#' -> getOccupiedSeatsAround(currMatrix, r, c, longRange) >= freeSeatTolerance ? 'L' : '#';
                    default -> throw new IllegalStateException("Unexpected value: " + currMatrix[r][c]);
                };

        boolean isStable = areMatrixEqual(currMatrix, nextMatrix);
        currSeats = nextMatrix;
        return isStable;
    }

    private boolean areMatrixEqual(char[][] m1, char[][] m2) {
        int rows = m1.length;
        int columns = m1[0].length;

        for (int r = 0; r < rows; ++r)
            for (int c = 0; c < columns; ++c)
                if (m1[r][c] != m2[r][c]) return false;
        return true;
    }

    private int getOccupiedSeatsAround(char[][] matrix, int seatRow, int seatColumn, boolean longRange) {
        return longRange ?
                getLongRangeOccupiedSeatsAround(matrix, seatRow, seatColumn) :
                getShortRangeOccupiedSeatsAround(matrix, seatRow, seatColumn);
    }

    private int getShortRangeOccupiedSeatsAround(char[][] matrix, int seatRow, int seatColumn) {
        int rows = matrix.length;
        int columns = matrix[0].length;
        int occupiedCount = 0;

        for (int dr = Math.max(0, seatRow - 1); dr <= Math.min(rows - 1, seatRow + 1); ++dr)
            for (int dc = Math.max(0, seatColumn - 1); dc <= Math.min(columns - 1, seatColumn + 1); ++dc)
                if ((dr != seatRow || dc != seatColumn) && matrix[dr][dc] == '#')
                    occupiedCount++;
        return occupiedCount;
    }

    private int getLongRangeOccupiedSeatsAround(char[][] matrix, int seatRow, int seatColumn) {
        int occupiedCount = 0;

        for (int dr = -1; dr <= 1; ++dr)
            for (int dc = -1; dc <= 1; ++dc)
                if ((dr != 0 || dc != 0) && isFirstNonFloorSeatOccupiedInDirection(matrix, seatRow, seatColumn, dr, dc))
                    occupiedCount++;
        return occupiedCount;
    }

    private boolean isFirstNonFloorSeatOccupiedInDirection(char[][] matrix, int seatRow, int seatColumn,
                                                           int deltaRow, int deltaColumn) {
        int rows = matrix.length;
        int columns = matrix[0].length;

        int dr = seatRow + deltaRow;
        int dc = seatColumn + deltaColumn;

        while (dr >= 0 && dr < rows && dc >= 0 && dc < columns) {
            if (matrix[dr][dc] == '#') return true;
            if (matrix[dr][dc] == 'L') return false;
            dr += deltaRow;
            dc += deltaColumn;
        }
        return false;
    }

    public void evolveUntilStable() {
        while (true) {
            if (evolveOneStep(false)) break;
        }
    }

    public void evolveLongRangeUntilStable() {
        while (true) {
            if (evolveOneStep(true)) break;
        }
    }

    public int getOccupiedSeats() {
        int rows = currSeats.length;
        int columns = currSeats[0].length;
        int occupiedSeats = 0;

        for (int r = 0; r < rows; ++r)
            for (int c = 0; c < columns; ++c)
                if (currSeats[r][c] == '#') occupiedSeats++;
        return occupiedSeats;
    }
}
