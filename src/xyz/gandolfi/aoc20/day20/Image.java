package xyz.gandolfi.aoc20.day20;

import java.util.*;

public class Image {
    private final List<Tile> tiles;
    private final Map<Integer, Set<Tile>> borderToTiles;

    public Image(List<String> inputLines) {
        tiles = new ArrayList<>();

        List<String> tileInput = new ArrayList<>();
        for (String line :  inputLines)
            if (line.isBlank()) {
                tiles.add(new Tile(tileInput));
                tileInput = new ArrayList<>();
            } else
                tileInput.add(line);
        if (!tileInput.isEmpty())
            tiles.add(new Tile(tileInput));

        borderToTiles = new HashMap<>();
        for (Tile t : tiles) {
            for (int border : t.getBorders()) {
                Set<Tile> match = borderToTiles.getOrDefault(border,  new HashSet<>());
                match.add(t);
                borderToTiles.put(border, match);
            }
            for (int border : t.getBordersFlipped()) {
                Set<Tile> match = borderToTiles.getOrDefault(border,  new HashSet<>());
                match.add(t);
                borderToTiles.put(border, match);
            }
        }
    }

    public List<Tile> findCornerTiles() {
        Map<Tile, Integer> tilesBorderCount = new HashMap<>();
        for (Map.Entry<Integer, Set<Tile>> e : borderToTiles.entrySet())
            if (e.getValue().size() >= 2)
                for (Tile t : e.getValue())
                    tilesBorderCount.put(t, tilesBorderCount.getOrDefault(t, 0) + 1);

        List<Tile> cornerTiles = new ArrayList<>();
        for (Map.Entry<Tile, Integer> e : tilesBorderCount.entrySet())
            if (e.getValue() == 4)
                cornerTiles.add(e.getKey());

        return cornerTiles;
    }

    public Tile getCompleteImageAsTile() {
        List<List<Tile>> allImgTiles =  getAllTilesComposingTheImage();

        List<List<int[][]>> borderlessTilesPixels = allImgTiles.stream()
                .map(tiles -> tiles.stream()
                        .map(Tile::getBorderlessPixels)
                        .toList())
                .toList();

        int[][] firstElem = borderlessTilesPixels.get(0).get(0);

        int[][] mergedPixels = new int[firstElem.length * borderlessTilesPixels.size()][firstElem[0].length * borderlessTilesPixels.get(0).size()];

        for (int row = 0; row < borderlessTilesPixels.size(); ++row)
            for (int col = 0; col < borderlessTilesPixels.get(0).size(); ++col)
                for (int y = 0; y < firstElem.length; ++y)
                    for (int x = 0; x < firstElem[y].length; ++x)
                        mergedPixels[(row * firstElem.length) + y][(col * firstElem[0].length) + x] =
                                borderlessTilesPixels.get(row).get(col)[y][x];

        return new Tile(mergedPixels);
    }

    private List<List<Tile>> getAllTilesComposingTheImage() {
        List<Tile> cornerTiles = findCornerTiles();
        Tile topLeftTile = cornerTiles.stream().findFirst().get();
        int rightBorderId = topLeftTile.getBorder(Border.RIGHT);
        if (borderToTiles.get(rightBorderId).size() == 1)
            topLeftTile = topLeftTile.flipHorizontally();
        int bottomBorderId = topLeftTile.getBorder(Border.BOTTOM);
        if (borderToTiles.get(bottomBorderId).size() == 1)
            topLeftTile = topLeftTile.flipVertically();

        List<List<Tile>> imageTiles = new ArrayList<>();
        List<Tile> row = new ArrayList<>();
        imageTiles.add(row);
        row.add(topLeftTile);
        int r = 0, c = 0;
        while (borderToTiles.get(imageTiles.get(r).get(c).getBorder(Border.BOTTOM)).size() >= 2) {
            while (borderToTiles.get(imageTiles.get(r).get(c).getBorder(Border.RIGHT)).size() >= 2) {
                Tile thisTile = imageTiles.get(r).get(c);

                if (r == 0) {
                    Tile tileOnTheRight = getTileOnTheRight(thisTile);
                    if (tileOnTheRight != null)
                        imageTiles.get(r).add(tileOnTheRight);
                }

                Tile tileOnTheBottom = getTileOnTheBottom(thisTile);

                if (tileOnTheBottom != null) {
                    if (c == 0) imageTiles.add(new ArrayList<>());
                    imageTiles.get(r + 1).add(tileOnTheBottom);
                }

                ++c;
            }
            Tile lastTile = imageTiles.get(r).get(c);
            Tile tileOnTheBottom = getTileOnTheBottom(lastTile);

            if (tileOnTheBottom != null)
                imageTiles.get(r + 1).add(tileOnTheBottom);

            c = 0;
            ++r;
        }

        return imageTiles;
    }

    private Tile getTileOnTheRight(Tile thisTile) {
        int thisTileRightBorder = thisTile.getBorder(Border.RIGHT);
        Optional<Tile> tileOnTheRightOpt = borderToTiles.get(thisTileRightBorder).stream()
                .filter(t -> t.getTileId() != thisTile.getTileId()).findFirst();
        if (tileOnTheRightOpt.isEmpty())
            return null;
        Tile tileOnTheRight = tileOnTheRightOpt.get();
        if (tileOnTheRight.getBorder(Border.LEFT) == thisTileRightBorder) {
            // OK: nothing to do
        } else if (tileOnTheRight.getBorder(Border.TOP) == thisTileRightBorder) {
            tileOnTheRight = tileOnTheRight.rotateClockwise().flipHorizontally();
        } else if (tileOnTheRight.getBorder(Border.RIGHT) == thisTileRightBorder) {
            tileOnTheRight = tileOnTheRight.flipHorizontally();
        } else if (tileOnTheRight.getBorder(Border.BOTTOM) == thisTileRightBorder) {
            tileOnTheRight = tileOnTheRight.rotateClockwise();
        } else if (tileOnTheRight.getBorderFlipped(Border.LEFT) == thisTileRightBorder) {
            tileOnTheRight = tileOnTheRight.flipVertically();
        } else if (tileOnTheRight.getBorderFlipped(Border.TOP) == thisTileRightBorder) {
            tileOnTheRight = tileOnTheRight.rotateCounterClockwise();
        } else if (tileOnTheRight.getBorderFlipped(Border.RIGHT) == thisTileRightBorder) {
            tileOnTheRight = tileOnTheRight.rotateClockwise().rotateClockwise();
        } else if (tileOnTheRight.getBorderFlipped(Border.BOTTOM) == thisTileRightBorder) {
            tileOnTheRight = tileOnTheRight.rotateClockwise().flipVertically();
        }
        return tileOnTheRight;
    }

    private Tile getTileOnTheBottom(Tile thisTile) {
        int thisTileBottomBorder = thisTile.getBorder(Border.BOTTOM);
        Optional<Tile> tileOnTheBottomOpt = borderToTiles.get(thisTileBottomBorder).stream()
                .filter(t -> t.getTileId() != thisTile.getTileId()).findFirst();
        if (tileOnTheBottomOpt.isEmpty())
            return null;
        Tile tileOnTheBottom = tileOnTheBottomOpt.get();
        if (tileOnTheBottom.getBorder(Border.TOP) == thisTileBottomBorder) {
            // OK: nothing to do
        } else if (tileOnTheBottom.getBorder(Border.RIGHT) == thisTileBottomBorder) {
            tileOnTheBottom = tileOnTheBottom.rotateCounterClockwise();
        } else if (tileOnTheBottom.getBorder(Border.BOTTOM) == thisTileBottomBorder) {
            tileOnTheBottom = tileOnTheBottom.flipVertically();
        } else if (tileOnTheBottom.getBorder(Border.LEFT) == thisTileBottomBorder) {
            tileOnTheBottom = tileOnTheBottom.rotateClockwise().flipHorizontally();
        } else if (tileOnTheBottom.getBorderFlipped(Border.TOP) == thisTileBottomBorder) {
            tileOnTheBottom = tileOnTheBottom.flipHorizontally();
        } else if (tileOnTheBottom.getBorderFlipped(Border.RIGHT) == thisTileBottomBorder) {
            tileOnTheBottom = tileOnTheBottom.rotateCounterClockwise().flipHorizontally();
        } else if (tileOnTheBottom.getBorderFlipped(Border.BOTTOM) == thisTileBottomBorder) {
            tileOnTheBottom = tileOnTheBottom.rotateClockwise().rotateClockwise();
        } else if (tileOnTheBottom.getBorderFlipped(Border.LEFT) == thisTileBottomBorder) {
            tileOnTheBottom = tileOnTheBottom.rotateClockwise();
        }
        return tileOnTheBottom;
    }

    public List<Tile> getTiles() {
        return tiles;
    }
}
