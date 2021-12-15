package xyz.gandolfi.aoc20.day17;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Space {
    private Set<Point> points;
    private Point minSpaceLimit;
    private Point maxSpaceLimit;

    public Space(List<String> inputLines) {
        points = new HashSet<>();
        for (int y = 0; y < inputLines.size(); ++y) {
            String line = inputLines.get(y);
            for (int x = 0; x < line.length(); ++x)
                if (line.charAt(x) == '#')
                    points.add(new Point(x, y, 0));
        }
        configureSpaceLimits();
    }

    public Space(Collection<Point> points) {
        this.points = new HashSet<>(points);
        configureSpaceLimits();
    }

    public void evolveNSteps(int n) {
        for (int i = 0; i < n; ++i)
            evolveOneStep();
    }

    public void evolveOneStep() {
        Set<Point> newPoints = new HashSet<>();
        for (int z = minSpaceLimit.getZ() - 1; z <= maxSpaceLimit.getZ() + 1; ++z)
            for (int y = minSpaceLimit.getY() - 1; y <= maxSpaceLimit.getY() + 1; ++y)
                for (int x = minSpaceLimit.getX() - 1; x <= maxSpaceLimit.getX() + 1; ++x) {
                    Point curr = new Point(x, y, z);
                    int neighbors = getNeighborsCount(curr);
                    if (points.contains(curr)) {
                        if (neighbors == 2 || neighbors == 3)
                            newPoints.add(curr);
                    } else {
                        if (neighbors == 3)
                            newPoints.add(curr);
                    }
                }
        points = newPoints;
        configureSpaceLimits();
    }

    private int getNeighborsCount(Point around) {
        int count = 0;
        for (int z = around.getZ() - 1; z <= around.getZ() + 1; ++z)
            for (int y = around.getY() - 1; y <= around.getY() + 1; ++y)
                for (int x = around.getX() - 1; x <= around.getX() + 1; ++x) {
                    if (x == around.getX() && y == around.getY() && z == around.getZ()) continue;
                    if (points.contains(new Point(x, y, z))) ++count;
                }
        return count;
    }

    private void configureSpaceLimits() {
        minSpaceLimit = new Point(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);
        maxSpaceLimit = new Point(Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE);
        for (Point p : points) {
            minSpaceLimit.setX(Math.min(minSpaceLimit.getX(), p.getX()));
            minSpaceLimit.setY(Math.min(minSpaceLimit.getY(), p.getY()));
            minSpaceLimit.setZ(Math.min(minSpaceLimit.getZ(), p.getZ()));
            maxSpaceLimit.setX(Math.max(maxSpaceLimit.getX(), p.getX()));
            maxSpaceLimit.setY(Math.max(maxSpaceLimit.getY(), p.getY()));
            maxSpaceLimit.setZ(Math.max(maxSpaceLimit.getZ(), p.getZ()));
        }
    }

    public Set<Point> getPoints() {
        return points;
    }

    @Override
    public String toString() {
        return "Space{" +
                "points=" + points +
                '}';
    }
}
