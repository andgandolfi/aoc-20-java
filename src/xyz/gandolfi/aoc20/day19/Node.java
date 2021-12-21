package xyz.gandolfi.aoc20.day19;

import java.util.Arrays;
import java.util.Set;
import java.util.Map;

public abstract class Node {
    static Node parseNode(String ruleStrValue) {
        if (ruleStrValue.contains(" | ")) {
            String[] parts = ruleStrValue.split(" \\| ");
            return new OrNode(parseNode(parts[0]), parseNode(parts[1]));
        } else if (ruleStrValue.charAt(0) == '"') {
            return new LiteralNode(ruleStrValue.substring(1, ruleStrValue.length() - 1));
        } else {
            return new AndNode(Arrays.stream(ruleStrValue.split(" ")).map(Integer::parseInt).toList());
        }
    }

    abstract void addMissingReferences(Map<Integer, Node> rules);

    abstract Set<Integer> matches(String message, int fromIndex);
}
