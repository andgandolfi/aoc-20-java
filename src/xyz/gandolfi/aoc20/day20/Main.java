package xyz.gandolfi.aoc20.day20;

import xyz.gandolfi.aoc20.Utils;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> inputLines = Utils.getInputFileLines("day20.txt");
        assert inputLines != null;

        Image img = new Image(inputLines);

        System.out.print("Day 20a: ");
        List<Tile> cornerTiles = img.findCornerTiles();
        long multCornerIds = cornerTiles.stream()
            .map(Tile::getTileId)
            .map(id -> (long) id)
            .reduce(1L, (i, j) -> i * j);
        System.out.println(multCornerIds);

        System.out.print("Day 20b: ");
        Tile completeImg = img.getCompleteImageAsTile();
        int imgPixelsCount = completeImg.getPixelsCount();
        int monstersNum = SeaMonsterFinder.findMonstersInEveryPossibleRotationOrFlipOfImg(completeImg);
        int pixelsPerMonster = SeaMonsterFinder.getSeaMonsterPatternPoints().size();
        System.out.println(imgPixelsCount - (monstersNum * pixelsPerMonster));
    }
}
