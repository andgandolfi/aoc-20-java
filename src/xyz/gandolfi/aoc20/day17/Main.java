package xyz.gandolfi.aoc20.day17;

import xyz.gandolfi.aoc20.Utils;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> inputLines = Utils.getInputFileLines("day17.txt");
        assert inputLines != null;

        System.out.print("Day 17a: ");
        Space3D space3d = new Space3D(inputLines);
        space3d.evolveNSteps(6);
        System.out.println(space3d.getPoints().size());

        System.out.print("Day 17b: ");
        Space4D space4d = new Space4D(inputLines);
        space4d.evolveNSteps(6);
        System.out.println(space4d.getPoints().size());
    }
}
