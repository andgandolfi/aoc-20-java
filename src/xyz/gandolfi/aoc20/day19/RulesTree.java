package xyz.gandolfi.aoc20.day19;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RulesTree {
    private Map<Integer, Node> rules;
    private Node root;

    private RulesTree() {}

    public static RulesTree parseRulesTree(List<String> inputLines) {
        RulesTree rulesTree = new RulesTree();
        rulesTree.rules = new HashMap<>(inputLines.size());

        for (String line : inputLines) {
            String[] parts = line.split(": ");
            int key = Integer.parseInt(parts[0]);
            String value = parts[1];
            Node node = Node.parseNode(value);
            rulesTree.rules.put(key, node);
        }
        for (Node node : rulesTree.rules.values())
            node.addMissingReferences(rulesTree.rules);
        rulesTree.root = rulesTree.rules.get(0);

        return rulesTree;
    }

    public Node getRoot() {
        return root;
    }
}
