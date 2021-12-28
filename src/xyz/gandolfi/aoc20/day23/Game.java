package xyz.gandolfi.aoc20.day23;

import java.util.LinkedList;
import java.util.List;

public class Game {
    private LinkedList<Integer> cups;
    private int maxNum;

    public Game(List<Integer> inputNums) {
        cups = new LinkedList<>(inputNums);
        maxNum = cups.stream().max(Integer::compareTo).get();
    }

    public void evolveOneStep() {
        int curr = cups.removeFirst();
        List<Integer> nextThree = List.of(cups.removeFirst(), cups.removeFirst(), cups.removeFirst());
        int dest = curr, destIdx;
        do {
            dest = dest - 1 >= 1 ? dest - 1 : maxNum;
            destIdx = cups.indexOf(dest);
        } while (destIdx < 0);
        cups.addAll(destIdx + 1, nextThree);
        cups.addLast(curr);
    }

    public void evolveNSteps(int n) {
        for (int i = 0; i < n; ++i)
            evolveOneStep();
    }

    public int getResultA() {
        int idx1 = cups.indexOf(1);
        List<Integer> resultArr = cups.subList(idx1 + 1, cups.size());
        resultArr.addAll(cups.subList(0, idx1));
        int result = 0;
        for (int n : resultArr)
            result = result * 10 + n;
        return result;
    }

    public long getResultB() {
        int idx1 = cups.indexOf(1);
        int n1 = cups.get((idx1 + 1) % 1_000_000);
        int n2 = cups.get((idx1 + 2) % 1_000_000);
        return (long) n1 * n2;
    }

    public LinkedList<Integer> getCups() {
        return cups;
    }
}
