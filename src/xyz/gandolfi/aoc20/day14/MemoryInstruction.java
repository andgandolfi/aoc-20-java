package xyz.gandolfi.aoc20.day14;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class MemoryInstruction extends Instruction {
    protected long memAddress;
    protected long memValue;

    private static final Pattern pattern = Pattern.compile("^mem\\[(\\d+)] = (\\d+)");

    public MemoryInstruction(String instructionString) {
        Matcher matcher = pattern.matcher(instructionString);
        matcher.matches();
        memAddress = Integer.parseInt(matcher.group(1));
        memValue = Long.parseLong(matcher.group(2));
    }

    @Override
    public String toString() {
        return "MemoryInstruction{" +
                "addr=" + memAddress +
                ", val=" + memValue +
                '}';
    }
}
