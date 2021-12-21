package xyz.gandolfi.aoc20.day19;

import java.util.*;

public class AndNode extends Node {
    private List<Integer> nodesIds;
    private List<Node> nodes;
    private boolean nodeReferencesFixed = false;

    public AndNode(List<Integer> nodesIds) {
        this.nodesIds = nodesIds;
        nodes = new ArrayList<>(nodesIds.size());
    }

    @Override
    void addMissingReferences(Map<Integer, Node> rules) {
        if (nodeReferencesFixed) return;
        for (int nodeId : nodesIds)
            nodes.add(rules.get(nodeId));
        nodeReferencesFixed = true;
    }

    @Override
    Set<Integer> matches(String message, int fromIndex) {
        Set<Integer> currIndex = new HashSet<>();
        Set<Integer> nextIndex = new HashSet<>();
        currIndex.add(fromIndex);
        for (Node node : nodes) {
            for (int idx : currIndex)
                nextIndex.addAll(node.matches(message, idx));
            currIndex = nextIndex;
            nextIndex = new HashSet<>();
        }
        return currIndex;
    }
}
