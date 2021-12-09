package xyz.gandolfi.aoc20.day08;

import java.util.List;

public class Runner {
    private List<Instruction> instructions;
    private boolean[] visitedInstructions;
    private int cursor = 0;
    private int accumulator = 0;

    public Runner(List<Instruction> instructions) {
        this.instructions = instructions;
        visitedInstructions = new boolean[instructions.size()];
    }

    public int run() {
        while (true) {
            if (cursor == instructions.size()) return 0;
            if (visitedInstructions[cursor]) return 1;
            visitedInstructions[cursor] = true;
            Instruction instruction = instructions.get(cursor);
            switch (instruction.getOperation()) {
                case NOP -> cursor++;
                case ACC -> {
                    accumulator += instruction.getArgument();
                    cursor++;
                }
                case JMP -> cursor += instruction.getArgument();
            }
        }
    }

    public int getAccumulator() {
        return accumulator;
    }
}
