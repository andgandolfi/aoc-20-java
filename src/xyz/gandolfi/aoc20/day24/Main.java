package xyz.gandolfi.aoc20.day24;

import xyz.gandolfi.aoc20.Utils;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> inputLines = Utils.getInputFileLines("day24.txt");
        assert inputLines != null;

        List<PathToTile> paths = inputLines.stream()
            .map(PathToTile::new)
            .toList();

        Floor floor = new Floor();

        System.out.print("Day 24a: ");
        floor.flipManyTiles(paths.stream().map(PathToTile::getDestinationTile).toList());
        System.out.println(floor.getBlackTilesCount());

        System.out.print("Day 24b: ");
        floor.evolveNDays(100);
        System.out.println(floor.getBlackTilesCount());
    }
}
