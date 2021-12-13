package xyz.gandolfi.aoc20.day14;

public class MaskInstruction extends Instruction {
    private final String mask;

    public MaskInstruction(String instructionString) {
        String[] parts = instructionString.split(" = ");
        mask = parts[1];
    }

    public State apply(State state) {
        state.setMask(mask);
        return state;
    }

    @Override
    public String toString() {
        return "MaskInstruction{" +
                "mask='" + mask + '\'' +
                '}';
    }
}
