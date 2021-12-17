package xyz.gandolfi.aoc20.day18;

import java.util.Arrays;
import java.util.LinkedList;

public class Expression {
    private final Node tree;

    private Expression(Node root) {
        tree = root;
    }

    public static Expression parseExpression(String expressionStr) {
        return parseExpression(expressionStr, false);
    }

    public static Expression parseExpressionWithPlusPrecedence(String expressionStr) {
        return parseExpression(expressionStr, true);
    }

    public static Expression parseExpression(String expressionStr, boolean plusPrecedence) {
        String[] tokensArray = expressionStr
                .replaceAll("\\(", "\\( ")
                .replaceAll("\\)", " \\)")
                .split("\\s+");

        LinkedList<String> tokens = new LinkedList<>(Arrays.stream(tokensArray).toList());
        return new Expression(parseTree(tokens, plusPrecedence));
    }

    private static Node parseTree(LinkedList<String> tokens, boolean plusPrecedence) {
        LinkedList<Node> nodes = new LinkedList<>();
        LinkedList<String> operations = new LinkedList<>();

        while (!tokens.isEmpty()) {
            String token = tokens.removeFirst();
            if (token.equals("("))
                nodes.add(parseTree(tokens, plusPrecedence));
            else if (token.equals(")"))
                break;
            else if (token.equals("+") || token.equals("*"))
                operations.addLast(token);
            else
                nodes.addLast(new LiteralNode(Long.parseLong(token)));
        }

        return plusPrecedence ?
                mergeMultipleOperationsWithPlusPrecedence(nodes, operations) :
                mergeMultipleOperationsWithNoPrecedence(nodes, operations);
    }

    private static Node mergeMultipleOperationsWithNoPrecedence(LinkedList<Node> nodes, LinkedList<String> operations) {
        while (!operations.isEmpty())
            nodes.addFirst(
                    new OperationNode(
                            operations.removeFirst(),
                            nodes.removeFirst(),
                            nodes.removeFirst()));

        return nodes.get(0);
    }

    private static Node mergeMultipleOperationsWithPlusPrecedence(LinkedList<Node> nodes, LinkedList<String> operations) {
        while (!operations.isEmpty()) {
            if (operations.size() > 1) {
                String op1 = operations.removeFirst();
                String op2 = operations.removeFirst();
                if (op1.equals(op2) || op1.equals("+")) {
                    nodes.addFirst(new OperationNode(op1, nodes.removeFirst(), nodes.removeFirst()));
                    operations.addFirst(op2);
                } else {
                    Node first = nodes.removeFirst();
                    nodes.addFirst(new OperationNode(op2, nodes.removeFirst(), nodes.removeFirst()));;
                    nodes.addFirst(first);
                    operations.addFirst(op1);
                }
            } else {
                nodes.addFirst(new OperationNode(operations.removeFirst(), nodes.removeFirst(), nodes.removeFirst()));
            }
        }

        return nodes.get(0);
    }

    public String getTreeRepresentation() {
        return tree.toString();
    }

    public long getResult() {
        return tree.getValue();
    }
}
