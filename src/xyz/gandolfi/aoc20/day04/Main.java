package xyz.gandolfi.aoc20.day04;

import xyz.gandolfi.aoc20.Utils;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> inputLines = Utils.getInputFileLines("day04.txt");
        assert inputLines != null;
        List<Passport> inputPassports = new ArrayList<>();
        StringBuilder passportData = new StringBuilder();
        for (String line : inputLines) {
            if (line.isBlank()) {
                inputPassports.add(Passport.parse(passportData.toString()));
                passportData = new StringBuilder();
            } else {
                passportData.append(line);
                passportData.append(" ");
            }
        }
        inputPassports.add(Passport.parse(passportData.toString()));

        System.out.print("Day 04a: ");
        int countValidPassports = inputPassports.stream()
            .filter(Passport::hasAllNecessaryFields)
            .map(p -> 1)
            .reduce(0, Integer::sum);
        System.out.println(countValidPassports);

        System.out.print("Day 04b: ");
        countValidPassports = inputPassports.stream()
                .filter(Passport::isValid)
                .map(p -> 1)
                .reduce(0, Integer::sum);
        System.out.println(countValidPassports);
    }
}
