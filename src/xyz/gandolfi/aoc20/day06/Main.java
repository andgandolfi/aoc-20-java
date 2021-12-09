package xyz.gandolfi.aoc20.day06;

import xyz.gandolfi.aoc20.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        List<String> inputLines = Utils.getInputFileLines("day06.txt");
        assert inputLines != null;
        List<Group> groups = new ArrayList<>();
        Group group = new Group();
        groups.add(group);
        for (String line : inputLines) {
            if (line.isBlank()) {
                group = new Group();
                groups.add(group);
            } else {
                group.addPerson(new Person(line));
            }
        }

        System.out.print("Day 06a: ");
        var countsAllAnswersFromAllGroups = groups.stream()
            .map(Group::getAllAnswersUnion)
            .map(Set::size)
            .reduce(0, Integer::sum);
        System.out.println(countsAllAnswersFromAllGroups);

        System.out.print("Day 06b: ");
        var countsCommonAnswersFromAllGroups = groups.stream()
                .map(Group::getAllAnswersIntersection)
                .map(Set::size)
                .reduce(0, Integer::sum);
        System.out.println(countsCommonAnswersFromAllGroups);
    }
}
