package xyz.gandolfi.aoc20.day12;

public class Instruction {
    private char action;
    private int value;

    public Instruction(String instructionString) {
        action = instructionString.charAt(0);
        value = Integer.parseInt(instructionString.substring(1));
    }

    public char getAction() {
        return action;
    }

    public int getValue() {
        return value;
    }
}
