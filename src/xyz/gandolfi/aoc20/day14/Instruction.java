package xyz.gandolfi.aoc20.day14;

public abstract class Instruction {
    public static Instruction parseInstruction(String instructionString, int version) {
        if (instructionString == null)
            return null;
        if (instructionString.startsWith("mask "))
            return new MaskInstruction(instructionString);
        if (instructionString.startsWith("mem[")) {
            if (version == 1)
                return new MemoryInstructionV1(instructionString);
            else
                return new MemoryInstructionV2(instructionString);
        }
        return null;
    }

    public abstract State apply(State state);
}
