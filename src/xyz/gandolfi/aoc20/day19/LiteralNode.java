package xyz.gandolfi.aoc20.day19;


import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LiteralNode extends Node {
    private final String value;

    public LiteralNode(String value) {
        this.value = value;
    }

    @Override
    void addMissingReferences(Map<Integer, Node> rules) {
    }

    @Override
    Set<Integer> matches(String message, int fromIndex) {
        Set<Integer> nextIndexes = new HashSet<>();
        if (message.startsWith(value, fromIndex))
            nextIndexes.add(fromIndex + value.length());
        return nextIndexes;
    }
}
