package xyz.gandolfi.aoc20.day07;

import java.util.*;

public class Bags {
    private final Map<String, Map<String, Integer>> containsRules;
    private final Map<String, Set<String>> containedRules;

    public Bags(List<String> inputLines) {
        containsRules = new HashMap<>();
        containedRules = new HashMap<>();
        for (String line : inputLines) {
            String[] parts = line.split(" bags contain ");
            String source = parts[0];
            String[] destinations = parts[1].split(", ");
            Map<String, Integer> destMap = new HashMap<>();
            if (destinations[0].startsWith("no ")) {
                containsRules.put(source, destMap);
            } else {
                for (String dest : destinations) {
                    String[] destParts = dest.split(" ");
                    String destColor = destParts[1] + " " + destParts[2];
                    destMap.put(destColor, destParts[0].equals("no") ? 0 : Integer.parseInt(destParts[0]));
                    Set<String> containedSet = containedRules.getOrDefault(destColor, new HashSet<>());
                    containedSet.add(source);
                    containedRules.put(destColor, containedSet);
                }
                containsRules.put(source, destMap);
            }
        }
    }

    public int countBagsContaining(String bagColor) {
        Stack<String> toBeChecked = new Stack<>();
        toBeChecked.push(bagColor);
        Set<String> checked = new HashSet<>();
        int count = -1;

        while (!toBeChecked.isEmpty()) {
            String curr = toBeChecked.pop();
            if (checked.contains(curr)) continue;
            checked.add(curr);
            count++;
            for (String containedBag : containedRules.getOrDefault(curr, new HashSet<>()))
                toBeChecked.push(containedBag);
        }

        return count;
    }

    public int countBagsNestedIn(String bagColor) {
        int sum = 0;

        for (Map.Entry<String, Integer> innerBag : containsRules.get(bagColor).entrySet())
            sum += innerBag.getValue() + innerBag.getValue() * countBagsNestedIn(innerBag.getKey());

        return sum;
    }
}
