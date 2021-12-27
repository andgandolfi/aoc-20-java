package xyz.gandolfi.aoc20.day20;

import java.util.ArrayList;
import java.util.List;

public class SeaMonsterFinder {
    private static final String seaMonsterPattern =
            "                  # \n" +
            "#    ##    ##    ###\n" +
            " #  #  #  #  #  #   ";
    private static final List<int[]> seaMonsterPatternPoints;

    static {
        seaMonsterPatternPoints = new ArrayList<>();
        String[] parts = seaMonsterPattern.split("\n");
        for (int y = 0; y < parts.length; ++y)
            for (int x = 0; x < parts[y].length(); ++x)
                if (parts[y].charAt(x) == '#')
                    seaMonsterPatternPoints.add(new int[]{x, y});
    }

    public static List<int[]> getSeaMonsterPatternPoints() {
        return seaMonsterPatternPoints;
    }

    public static int findMonstersInTile(Tile image) {
        int[][] imgPixels = image.getPixels();
        int maxX = seaMonsterPatternPoints.stream().map(p -> p[0]).max(Integer::compare).get();
        int maxY = seaMonsterPatternPoints.stream().map(p -> p[1]).max(Integer::compare).get();
        int monstersCount = 0;

        for (int y = 0; y < imgPixels.length - maxY; ++y)
            for (int x = 0; x < imgPixels[0].length - maxX; ++x) {
                boolean foundMonster = true;
                for (int[] point : seaMonsterPatternPoints) {
                    int px = point[0], py = point[1];
                    if (imgPixels[y + py][x + px] != 1) {
                        foundMonster = false;
                        break;
                    }
                }
                if (foundMonster)
                    monstersCount++;
            }
        return monstersCount;
    }

    public static int findMonstersInEveryPossibleRotationOrFlipOfImg(Tile image) {
        int count = 0;
        count += findMonstersInTile(image);
        count += findMonstersInTile(image.rotateClockwise());
        count += findMonstersInTile(image.rotateClockwise().rotateClockwise());
        count += findMonstersInTile(image.rotateCounterClockwise());
        count += findMonstersInTile(image.flipHorizontally());
        count += findMonstersInTile(image.flipHorizontally().rotateClockwise());
        count += findMonstersInTile(image.flipHorizontally().rotateClockwise().rotateClockwise());
        count += findMonstersInTile(image.flipHorizontally().rotateCounterClockwise());
        return count;
    }
}
