package xyz.gandolfi.aoc20.day20;

import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tile {
    private int tileId;
    private int[][] pixels;

    private int[] borders;
    private int[] bordersFlipped;

    private static final Pattern tileIdPattern = Pattern.compile("^Tile (\\d+):$");

    private Tile(int tileId) {
        this.tileId = tileId;
    }

    public Tile(int[][] pixels) {
        this.pixels = pixels;
        this.borders = new int[4];
        this.bordersFlipped = new int[4];
    }

    public Tile(List<String> inputLines) {
        Matcher matcher = tileIdPattern.matcher(inputLines.get(0));
        boolean matches = matcher.matches();
        assert matches;
        tileId = Integer.parseInt(matcher.group(1));

        pixels = new int[inputLines.size() - 1][inputLines.get(1).length()];
        for (int y = 0; y < inputLines.size() - 1; ++y) {
            String line = inputLines.get(y + 1);
            for (int x = 0; x < line.length(); ++x)
                pixels[y][x] = line.charAt(x) == '#' ? 1 : 0;
        }

        borders = new int[]{
            calculateRowBorderValue(0, false),
            calculateColumnBorderValue(pixels[0].length - 1, false),
            calculateRowBorderValue(pixels[0].length - 1, false),
            calculateColumnBorderValue(0, false),
        };

        bordersFlipped = new int[]{
            calculateRowBorderValue(0, true),
            calculateColumnBorderValue(pixels[0].length - 1, true),
            calculateRowBorderValue(pixels[0].length - 1, true),
            calculateColumnBorderValue(0, true),
        };
    }

    public int getTileId() {
        return tileId;
    }

    public int[] getBorders() {
        return borders;
    }

    public int getBorder(Border border) {
        return borders[border.ordinal()];
    }

    public int[] getBordersFlipped() {
        return bordersFlipped;
    }

    public int getBorderFlipped(Border border) {
        return bordersFlipped[border.ordinal()];
    }

    private int calculateRowBorderValue(int row, boolean flip) {
        int value = 0;
        for (int x = 0; x < pixels[row].length; ++x) {
            int idx = flip ? pixels[row].length - x - 1 : x;
            value = (value * 2) + pixels[row][idx];
        }
        return value;
    }

    private int calculateColumnBorderValue(int column, boolean flip) {
        int value = 0;
        for (int y = 0; y < pixels.length; ++y) {
            int idx = flip ? pixels.length - y - 1 : y;
            value = (value * 2) + pixels[idx][column];
        }
        return value;
    }

    public Tile flipVertically() {
        Tile newTile = new Tile(tileId);
        newTile.pixels = new int[pixels.length][pixels[0].length];
        for (int y = 0; y < pixels.length; ++y)
            newTile.pixels[y] = pixels[pixels.length - y - 1].clone();
        newTile.borders = new int[] {borders[2], bordersFlipped[1], borders[0], bordersFlipped[3]};
        newTile.bordersFlipped = new int[] {bordersFlipped[2], borders[1], bordersFlipped[0], borders[3]};
        return newTile;
    }

    public Tile flipHorizontally() {
        Tile newTile = new Tile(tileId);
        newTile.pixels = new int[pixels.length][pixels[0].length];
        for (int y = 0; y < pixels.length; ++y)
            for (int x = 0; x < pixels[y].length; ++x)
                newTile.pixels[y][x] = pixels[y][pixels[y].length - x - 1];
        newTile.borders = new int[] {bordersFlipped[0], borders[3], bordersFlipped[2], borders[1]};
        newTile.bordersFlipped = new int[] {borders[0], bordersFlipped[3], borders[2], bordersFlipped[1]};
        return newTile;
    }

    public Tile rotateClockwise() {
        Tile newTile = new Tile(tileId);
        newTile.pixels = new int[pixels[0].length][pixels.length];
        for (int y = 0; y < pixels.length; ++y)
            for (int x = 0; x < pixels[y].length; ++x)
                newTile.pixels[x][pixels.length - y - 1] = pixels[y][x];
        newTile.borders = new int[] {bordersFlipped[3], borders[0], bordersFlipped[1], borders[2]};
        newTile.bordersFlipped = new int[] {borders[3], bordersFlipped[0], borders[1], bordersFlipped[2]};
        return newTile;
    }

    public Tile rotateCounterClockwise() {
        Tile newTile = new Tile(tileId);
        newTile.pixels = new int[pixels[0].length][pixels.length];
        for (int y = 0; y < pixels.length; ++y)
            for (int x = 0; x < pixels[y].length; ++x)
                newTile.pixels[pixels[y].length - x - 1][y] = pixels[y][x];
        newTile.borders = new int[] {borders[1], bordersFlipped[2], borders[3], bordersFlipped[0]};
        newTile.bordersFlipped = new int[] {bordersFlipped[1], borders[2], bordersFlipped[3], borders[0]};
        return newTile;
    }

    public int[][] getBorderlessPixels() {
        int[][] borderlessPixels = new int[pixels.length - 2][pixels[0].length - 2];

        for (int y = 0; y < pixels.length - 2; ++y)
            for (int x = 0; x < pixels[y+1].length - 2; ++x)
                borderlessPixels[y][x] = pixels[y + 1][x + 1];

        return borderlessPixels;
    }

    public int[][] getPixels() {
        return pixels;
    }

    public int getPixelsCount() {
        int count = 0;
        for (int y = 0; y < pixels.length; ++y)
            for (int x = 0; x < pixels[y].length; ++x)
                if (pixels[y][x] == 1)
                    ++count;
        return count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tile tile = (Tile) o;
        return tileId == tile.tileId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(tileId);
    }

    @Override
    public String toString() {
//        return String.valueOf(tileId);
        StringBuilder sb = new StringBuilder();
        sb.append("Tile ").append(tileId).append(":\n");
        for (int y = 0; y < pixels.length; ++y) {
            for (int x = 0; x < pixels[y].length; ++x)
                sb.append(pixels[y][x] == 1 ? '#' : '.');
            sb.append('\n');
        }
        return sb.toString();
    }
}
