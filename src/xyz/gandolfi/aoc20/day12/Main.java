package xyz.gandolfi.aoc20.day12;

import xyz.gandolfi.aoc20.Utils;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> inputLines = Utils.getInputFileLines("day12.txt");
        assert inputLines != null;

        List<Instruction> instructions = inputLines.stream().map(Instruction::new).toList();

        System.out.print("Day 12a: ");
        Runner runnerA = new RunnerA(instructions);
        runnerA.run();
        System.out.println(Math.abs(runnerA.getPositionEast()) + Math.abs(runnerA.getPositionNorth()));

        System.out.print("Day 12b: ");
        Runner runnerB = new RunnerB(instructions);
        runnerB.run();
        System.out.println(Math.abs(runnerB.getPositionEast()) + Math.abs(runnerB.getPositionNorth()));
    }
}
