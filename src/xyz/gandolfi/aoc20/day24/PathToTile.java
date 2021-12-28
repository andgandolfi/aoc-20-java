package xyz.gandolfi.aoc20.day24;

public class PathToTile {
    private Tile destinationTile;

    public PathToTile(String path) {
        int x = 0, y = 0;
        String direction = "";
        for (int i = 0; i < path.length(); ++i) {
            char c = path.charAt(i);
            direction += c;
            if (c == 's' || c == 'n')
                continue;
            switch (direction) {
                case "e" -> x += 1;
                case "w" -> x -= 1;
                case "nw" -> y -= 1;
                case "se" -> y += 1;
                case "ne" -> { y -= 1; x += 1; }
                case "sw" -> { y += 1; x -= 1; }
                default -> throw new IllegalStateException("Unexpected value: " + direction);
            }
            direction = "";
        }
        destinationTile = new Tile(x, y);
    }

    public Tile getDestinationTile() {
        return destinationTile;
    }
}
