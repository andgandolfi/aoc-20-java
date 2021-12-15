package xyz.gandolfi.aoc20.day17;

import xyz.gandolfi.aoc20.Utils;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> inputLines = Utils.getInputFileLines("day17.txt");
        assert inputLines != null;

        Space space = new Space(inputLines);

        System.out.print("Day 17a: ");
        space.evolveNSteps(6);
        System.out.println(space.getPoints().size());

        System.out.print("Day 17b: ");
        System.out.println();
    }
}
