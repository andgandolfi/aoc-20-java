package xyz.gandolfi.aoc20.day15;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NumbersProcessor {
    private List<Integer> inputNums;
    private Map<Integer, Integer> visited;

    public NumbersProcessor(List<Integer> inputNums) {
        this.inputNums = inputNums;
        visited = new HashMap<>();
    }

    public int getNumberAt(int round) {
        int lastVisited = 0;
        for (int i = 1; i <= round; ++i) {
            if (i <= inputNums.size()) {
                visited.put(lastVisited, i - 1);
                lastVisited = inputNums.get(i - 1);
                continue;
            }
            int lastVisitedLastTime = visited.getOrDefault(lastVisited, 0);
            visited.put(lastVisited, i - 1);
            lastVisited = lastVisitedLastTime == 0 ? 0 : i - 1 - lastVisitedLastTime;
        }

        return lastVisited;
    }
}
