package xyz.gandolfi.aoc20.day10;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JoltageCalculator {
    public static int[] getAdapterDifferences(List<Integer> inputAdapters) {
        List<Integer> sortedInput = inputAdapters.stream().sorted().toList();
        int[] differences = new int[3];
        int curr = 0;
        for (int i : sortedInput) {
            int diff = i - curr;
            differences[diff - 1]++;
            curr = i;
        }
        differences[2]++;
        return differences;
    }

    public static long getNumberOfDifferentArrangements(List<Integer> inputAdapters) {
        List<Integer> sortedInput = inputAdapters.stream().sorted().toList();
        Map<Integer, Long> prevResults = new HashMap<>();
        prevResults.put(0, 1L);
        long sum = 0L;
        for (int i : sortedInput) {
            sum = prevResults.getOrDefault(i - 1, 0L) +
                    prevResults.getOrDefault(i - 2, 0L) +
                    prevResults.getOrDefault(i - 3, 0L);
            prevResults.put(i, sum);
        }
        return sum;
    }
}
