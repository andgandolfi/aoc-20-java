package xyz.gandolfi.aoc20.day24;

import java.util.List;
import java.util.Objects;

public class Tile {
    private int x;
    private int y;

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public List<Tile> getAdjacentTiles() {
        return List.of(
            new Tile(x, y-1),
            new Tile(x, y+1),
            new Tile(x-1, y),
            new Tile(x+1, y),
            new Tile(x+1, y-1),
            new Tile(x-1, y+1)
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tile tile = (Tile) o;
        return x == tile.x && y == tile.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "<" + x + "," + y + '>';
    }
}
