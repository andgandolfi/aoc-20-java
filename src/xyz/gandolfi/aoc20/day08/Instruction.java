package xyz.gandolfi.aoc20.day08;

import java.util.Locale;

public class Instruction {
    private Operation operation;
    private int argument;

    public Instruction(String inputLine) {
        String[] parts = inputLine.split(" ");
        operation = Operation.valueOf(parts[0].toUpperCase(Locale.ROOT));
        argument = Integer.parseInt(parts[1]);
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public int getArgument() {
        return argument;
    }

    public void setArgument(int argument) {
        this.argument = argument;
    }

    @Override
    public String toString() {
        return "Instruction{op=" + operation + ", arg=" + argument + '}';
    }
}
