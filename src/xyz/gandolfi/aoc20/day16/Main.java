package xyz.gandolfi.aoc20.day16;

import xyz.gandolfi.aoc20.Utils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        List<String> inputLines = Utils.getInputFileLines("day16.txt");
        assert inputLines != null;

        Map<String, Rule> rules = inputLines.stream()
            .takeWhile(s -> !s.isBlank())
            .map(s -> s.split(": "))
            .collect(Collectors.toMap(s -> s[0], s -> new Rule(s[0], s[1])));

        List<Integer> myTicket = Arrays.stream(inputLines.stream()
                .dropWhile(s -> !s.equals("your ticket:"))
                .skip(1)
                .findFirst()
                .get()
                .split(","))
            .map(Integer::parseInt)
            .toList();

        List<List<Integer>> nearbyTickets = inputLines.stream()
            .dropWhile(s -> !s.equals("nearby tickets:"))
            .skip(1)
            .map(s -> Arrays.stream(s.split(",")).map(Integer::parseInt).toList())
            .toList();


        System.out.print("Day 16a: ");
        List<Integer> invalidValues = nearbyTickets.stream()
            .flatMap(Collection::stream)
            .filter(x -> rules.entrySet().stream()
                .map(Map.Entry::getValue)
                .noneMatch(rule -> rule.isValidValue(x))
            )
            .toList();
        int sumOfInvalidValues = invalidValues.stream()
            .reduce(0, Integer::sum);
        System.out.println(sumOfInvalidValues);


        System.out.print("Day 16b: ");
        List<List<Integer>> validNearbyTickets = nearbyTickets.stream()
            .filter(xs -> xs.stream()
                .allMatch(x -> rules.values().stream()
                    .anyMatch(r -> r.isValidValue(x))
                )
            )
            .toList();
        List<List<Integer>> validNearbyTicketsColumns = IntStream.range(0, myTicket.size()).boxed()
            .map(i -> validNearbyTickets.stream()
                .map(x -> x.get(i))
                .toList()
            )
            .toList();
        List<HashSet<Rule>> rulesPerColumns = validNearbyTicketsColumns.stream()
            .map(xs -> xs.stream()
                .map(x -> rules.values().stream()
                    .filter(rule -> rule.isValidValue(x))
                    .collect(Collectors.toSet())
                )
                .collect(() -> new HashSet<>(rules.values()), Set::retainAll, Set::retainAll)
            )
            .toList();
        Rule[] rulePerColumn = new Rule[rulesPerColumns.size()];
        Set<Rule> rulesAlreadyPositioned = new HashSet<>();
        boolean allFound = false;
        while (!allFound) {
            allFound = true;
            for (int i = 0; i < rulesPerColumns.size(); ++i) {
                HashSet<Rule> column = rulesPerColumns.get(i);
                column.removeAll(rulesAlreadyPositioned);
                if (column.size() > 1)
                    allFound = false;
                else if (column.size() == 1) {
                    Rule onlyLeftRule = column.stream().findFirst().get();
                    rulePerColumn[i] = onlyLeftRule;
                    rulesAlreadyPositioned.add(onlyLeftRule);
                }
            }
        }
        long departureProduct = 1;
        for (int i = 0; i < rulePerColumn.length; ++i)
            if (rulePerColumn[i].getRuleName().startsWith("departure"))
                departureProduct *= myTicket.get(i);
        System.out.println(departureProduct);
    }
}
