package xyz.gandolfi.aoc20.day19;

import xyz.gandolfi.aoc20.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        List<String> inputLines = Utils.getInputFileLines("day19.txt");
        assert inputLines != null;

        List<String> rulesPartA = inputLines.stream()
            .takeWhile(l -> !l.isBlank())
            .toList();

        List<String> rulesPartB = rulesPartA.stream()
            .map(s -> s.startsWith("8: ") ? "8: 42 | 42 8" : s)
            .map(s -> s.startsWith("11: ") ? "11: 42 31 | 42 11 31" : s)
            .toList();

        List<String> messages = inputLines.stream()
            .dropWhile(l -> !l.isBlank())
            .skip(1)
            .toList();

        RulesTree treeA = RulesTree.parseRulesTree(rulesPartA);
        Node rootA = treeA.getRoot();

        RulesTree treeB = RulesTree.parseRulesTree(rulesPartB);
        Node rootB = treeB.getRoot();

        System.out.print("Day 19a: ");
        List<String> matchingMessagesA = new ArrayList<>();
        for (String m : messages) {
            Set<Integer> charMatchesCounts = rootA.matches(m,  0);
            if (charMatchesCounts.contains(m.length()))
                matchingMessagesA.add(m);
        }
        System.out.println(matchingMessagesA.size());

        System.out.print("Day 19b: ");
        List<String> matchingMessagesB = new ArrayList<>();
        for (String m : messages) {
            Set<Integer> charMatchesCounts = rootB.matches(m,  0);
            if (charMatchesCounts.contains(m.length()))
                matchingMessagesB.add(m);
        }
        System.out.println(matchingMessagesB.size());
    }
}
