package xyz.gandolfi.aoc20.day17;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Space4D {
    private Set<Point4D> points;
    private Point4D minSpaceLimit;
    private Point4D maxSpaceLimit;

    public Space4D(List<String> inputLines) {
        points = new HashSet<>();
        for (int y = 0; y < inputLines.size(); ++y) {
            String line = inputLines.get(y);
            for (int x = 0; x < line.length(); ++x)
                if (line.charAt(x) == '#')
                    points.add(new Point4D(x, y, 0, 0));
        }
        configureSpaceLimits();
    }

    public Space4D(Collection<Point4D> points) {
        this.points = new HashSet<>(points);
        configureSpaceLimits();
    }

    public void evolveNSteps(int n) {
        for (int i = 0; i < n; ++i)
            evolveOneStep();
    }

    public void evolveOneStep() {
        Set<Point4D> newPoints = new HashSet<>();
        for (int w = minSpaceLimit.getW() - 1; w <= maxSpaceLimit.getW() + 1; ++w)
            for (int z = minSpaceLimit.getZ() - 1; z <= maxSpaceLimit.getZ() + 1; ++z)
                for (int y = minSpaceLimit.getY() - 1; y <= maxSpaceLimit.getY() + 1; ++y)
                    for (int x = minSpaceLimit.getX() - 1; x <= maxSpaceLimit.getX() + 1; ++x) {
                        Point4D curr = new Point4D(x, y, z, w);
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

    private int getNeighborsCount(Point4D around) {
        int count = 0;
        for (int w = around.getW() - 1; w <= around.getW() + 1; ++w)
            for (int z = around.getZ() - 1; z <= around.getZ() + 1; ++z)
                for (int y = around.getY() - 1; y <= around.getY() + 1; ++y)
                    for (int x = around.getX() - 1; x <= around.getX() + 1; ++x) {
                        if (x == around.getX() && y == around.getY() && z == around.getZ() && w == around.getW())
                            continue;
                        if (points.contains(new Point4D(x, y, z, w)))
                            ++count;
                    }
        return count;
    }

    private void configureSpaceLimits() {
        minSpaceLimit = new Point4D(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);
        maxSpaceLimit = new Point4D(Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE);
        for (Point4D p : points) {
            minSpaceLimit.setX(Math.min(minSpaceLimit.getX(), p.getX()));
            minSpaceLimit.setY(Math.min(minSpaceLimit.getY(), p.getY()));
            minSpaceLimit.setZ(Math.min(minSpaceLimit.getZ(), p.getZ()));
            minSpaceLimit.setW(Math.min(minSpaceLimit.getW(), p.getW()));
            maxSpaceLimit.setX(Math.max(maxSpaceLimit.getX(), p.getX()));
            maxSpaceLimit.setY(Math.max(maxSpaceLimit.getY(), p.getY()));
            maxSpaceLimit.setZ(Math.max(maxSpaceLimit.getZ(), p.getZ()));
            maxSpaceLimit.setW(Math.max(maxSpaceLimit.getW(), p.getW()));
        }
    }

    public Set<Point4D> getPoints() {
        return points;
    }

    @Override
    public String toString() {
        return "Space{" +
                "points=" + points +
                '}';
    }
}
