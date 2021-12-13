package xyz.gandolfi.aoc20.day14;

public class MemoryInstructionV1 extends MemoryInstruction {
    public MemoryInstructionV1(String instructionString) {
        super(instructionString);
    }

    @Override
    public State apply(State state) {
        long value = memValue;
        value = value | Long.parseLong(state.getMask().replace('X', '0'), 2);
        value = value & Long.parseLong(state.getMask().replace('X', '1'), 2);
        state.setMemoryVal(memAddress, value);
        return state;
    }
}
