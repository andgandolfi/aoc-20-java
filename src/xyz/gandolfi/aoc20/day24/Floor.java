package xyz.gandolfi.aoc20.day24;

import java.util.*;

public class Floor {
    private Map<Tile, Boolean> floor;

    public Floor() {
        floor = new HashMap<>();
    }

    public void flipTile(Tile tile) {
        Boolean tileStatus = floor.get(tile);
        if (tileStatus == null)
            floor.put(tile, true);
        else
            floor.put(tile, !tileStatus);
    }

    public void flipManyTiles(List<Tile> tiles) {
        for (Tile t : tiles)
            flipTile(t);
    }

    public void evolveOneDay() {
        Set<Tile> needsToFlip = new HashSet<>();
        for (Tile tile : floor.keySet()) {
            if (!floor.get(tile)) // if tile is white, skip
                continue;
            List<Tile> adjacentTiles = tile.getAdjacentTiles();
            int adjBlack = howManyBlackIn(adjacentTiles);
            if (adjBlack == 0 ||  adjBlack > 2)
                needsToFlip.add(tile);
            for (Tile adjTile : adjacentTiles) {
                Boolean adjTileBlack = floor.get(adjTile);
                if (adjTileBlack != null && adjTileBlack) // if adjacent tile is black, skip
                    continue;
                List<Tile> adjAdjacentTiles = adjTile.getAdjacentTiles();
                if (howManyBlackIn(adjAdjacentTiles) == 2)
                    needsToFlip.add(adjTile);
            }
        }
        for (Tile tile : needsToFlip) {
            Boolean currentTileState = floor.get(tile);
            if (currentTileState == null)
                floor.put(tile, true);
            else
                floor.put(tile, !currentTileState);
        }
    }

    public void evolveNDays(int n) {
        for (int i = 0; i < n; ++i)
            evolveOneDay();
    }

    private int howManyBlackIn(List<Tile> tiles) {
        int count = 0;
        for (Tile t : tiles) {
            Boolean isBlack = floor.get(t);
            if (isBlack != null && isBlack)
                ++count;
        }
        return count;
    }

    public int getBlackTilesCount() {
        int count = 0;
        for (boolean isBlack : floor.values())
            if (isBlack)
                ++count;
        return count;
    }
}
