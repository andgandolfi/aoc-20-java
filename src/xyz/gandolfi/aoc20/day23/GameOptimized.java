package xyz.gandolfi.aoc20.day23;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameOptimized {
    static class Node  {
        int value;
        Node next;

        @Override
        public String toString() {
            return "Node{" + value + " -> " + next.value + '}';
        }
    }

    private Map<Integer, Node> cups;
    private Node current;
    private int maxNum;

    public GameOptimized(List<Integer> inputNums) {
        cups = new HashMap<>(inputNums.size());
        Node prev = null;
        for (int n : inputNums) {
            Node node = new Node();
            node.value = n;
            cups.put(n, node);
            if (prev != null)
                prev.next = node;
            prev = node;
            if (n > maxNum)
                maxNum = n;
        }
        current = prev.next = cups.get(inputNums.get(0));
    }

    public void evolveOneStep() {
        Node curr = current;
        Node nextThree = curr.next;
        curr.next = curr.next.next.next.next;
        int destVal = curr.value;
        do {
            destVal = destVal - 1;
            destVal = destVal >= 1 ? destVal : maxNum;
        } while (destVal == nextThree.value || destVal == nextThree.next.value ||
                destVal == nextThree.next.next.value);
        Node dest = cups.get(destVal);
        Node prevNext = dest.next;
        dest.next = nextThree;
        dest.next.next.next.next = prevNext;
        current = curr.next;
    }

    public void evolveNSteps(int n) {
        for (int i = 0; i < n; ++i)
            evolveOneStep();
    }

    public int getResultA() {
        int result = 0;
        Node node1 = cups.get(1);
        Node currCup = node1;
        while (currCup.next != node1) {
            result = result * 10 + currCup.next.value;
            currCup = currCup.next;
        }
        return result;
    }

    public long getResultB() {
        Node node1 = cups.get(1);
        return (long) node1.next.value * node1.next.next.value;
    }

    public List<Integer> getCups() {
        List<Integer> result = new ArrayList<>();
        Node currCup = current;
        result.add(currCup.value);
        while (currCup.next != current) {
            result.add(currCup.next.value);
            currCup = currCup.next;
        }
        return result;
    }
}
