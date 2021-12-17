package xyz.gandolfi.aoc20.day18;

public class OperationNode extends Node {
    protected String operation;

    public OperationNode(String op, Node left, Node right) {
        super(left, right);
        operation = op;
    }

    @Override
    Long getValue() {
        Long leftValue = left.getValue();
        Long rightValue = right.getValue();

        if (leftValue == null || rightValue == null)
            return null;

        if (operation.equals("+"))
            return leftValue + rightValue;
        if (operation.equals("*"))
            return leftValue * rightValue;
        return null;
    }

    @Override
    public String toString() {
        return "(" + operation + " " + left + " " + right + ")";
    }
}
