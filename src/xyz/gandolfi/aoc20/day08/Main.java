package xyz.gandolfi.aoc20.day08;

import xyz.gandolfi.aoc20.Utils;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> inputLines = Utils.getInputFileLines("day08.txt");
        assert inputLines != null;

        List<Instruction> instructions = inputLines.stream().map(Instruction::new).toList();
        Runner runner;

        System.out.print("Day 08a: ");
        runner = new Runner(instructions);
        runner.run();
        System.out.println(runner.getAccumulator());

        System.out.print("Day 08b: ");
        for (Instruction instruction : instructions) {
            Operation op = instruction.getOperation();
            if (op != Operation.NOP && op != Operation.JMP)
                continue;
            instruction.setOperation(op == Operation.NOP ? Operation.JMP : Operation.NOP);
            runner = new Runner(instructions);
            int status = runner.run();
            if (status == 0) {
                System.out.println(runner.getAccumulator());
                return;
            }
            instruction.setOperation(op == Operation.NOP ? Operation.NOP : Operation.JMP);
        }
    }
}
