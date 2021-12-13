package xyz.gandolfi.aoc20.day14;

import xyz.gandolfi.aoc20.Utils;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> inputLines = Utils.getInputFileLines("day14.txt");
        assert inputLines != null;

        List<Instruction> instructionsV1 = inputLines.stream()
            .map(s -> Instruction.parseInstruction(s, 1))
            .toList();

        List<Instruction> instructionsV2 = inputLines.stream()
                .map(s -> Instruction.parseInstruction(s, 2))
                .toList();

        System.out.print("Day 14a: ");
        State stateA = new State();
        stateA.applyInstructions(instructionsV1);
        System.out.println(stateA.getSumOfValues());

        System.out.print("Day 14b: ");
        State stateB = new State();
        stateB.applyInstructions(instructionsV2);
        System.out.println(stateB.getSumOfValues());
    }
}
