package xyz.gandolfi.aoc20.day17;

import java.util.Objects;

public class Point4D {
    private int x;
    private int y;
    private int z;
    private int w;

    public Point4D() {}

    public Point4D(int x, int y, int z, int w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public int getW() {
        return w;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public void setW(int w) {
        this.w = w;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point4D that = (Point4D) o;
        return x == that.x && y == that.y && z == that.z && w == that.w;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z, w);
    }

    @Override
    public String toString() {
        return "Point{" + x + ", " + y + ", " + z + ", " + w + '}';
    }
}
