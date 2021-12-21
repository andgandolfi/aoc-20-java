package xyz.gandolfi.aoc20.day19;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class OrNode extends Node {
    private final Node node1;
    private final Node node2;

    public OrNode(Node node1, Node node2) {
        this.node1 = node1;
        this.node2 = node2;
    }

    @Override
    void addMissingReferences(Map<Integer, Node> rules) {
        node1.addMissingReferences(rules);
        node2.addMissingReferences(rules);
    }

    @Override
    Set<Integer> matches(String message, int fromIndex) {
        Set<Integer> nextIndexes = new HashSet<>();
        nextIndexes.addAll(node1.matches(message, fromIndex));
        nextIndexes.addAll(node2.matches(message, fromIndex));
        return nextIndexes;
    }
}
