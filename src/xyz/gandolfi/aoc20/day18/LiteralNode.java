package xyz.gandolfi.aoc20.day18;

public class LiteralNode extends Node {
    private long value;

    public LiteralNode(long value) {
        super();
        this.value = value;
    }

    @Override
    Long getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
