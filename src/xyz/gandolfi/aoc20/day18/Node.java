package xyz.gandolfi.aoc20.day18;

public abstract class Node {
    protected Node left;
    protected Node right;

    public Node() {}

    public Node(Node left, Node right) {
        this();
        this.left = left;
        this.right = right;
    }

    abstract Long getValue();
}
