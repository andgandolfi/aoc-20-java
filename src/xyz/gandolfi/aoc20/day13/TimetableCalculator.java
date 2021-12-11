package xyz.gandolfi.aoc20.day13;

import java.util.ArrayList;
import java.util.List;

public class TimetableCalculator {
    private final List<Integer> busses;
    private final int earliestTimestamp;

    public TimetableCalculator(List<Integer> busses, int earliestTimestamp) {
        this.busses = busses;
        this.earliestTimestamp = earliestTimestamp;
    }

    public int findEarliestBusAfterTimestamp() {
        int bestWaitingTime = Integer.MAX_VALUE;
        int bestBus = 0;

        for (Integer bus : busses) {
            if (bus == null) continue;
            int division = (int) Math.ceil((double)earliestTimestamp / bus);
            int time = division * bus;
            int timeToWait = time - earliestTimestamp;
            if (timeToWait < bestWaitingTime) {
                bestWaitingTime = (time - earliestTimestamp);
                bestBus = bus;
            }
        }
        return bestBus * bestWaitingTime;
    }

    private long[] findFirstValidTimeAndNext(long startFrom, long step, int bus, int offset) {
        List<Long> results = new ArrayList<>();
        long curr = startFrom;
        while (results.size() < 2) {
            if ((curr + offset) % bus == 0) {
                results.add(curr);
            }
            curr += step;
        }
        return new long[] {results.get(0), results.get(1)};
    }

    public long findFirstTimeAllAlign() {
        long startFrom = 0;
        long step = 1;
        for (int i = 0; i < busses.size(); ++i) {
            Integer bus = busses.get(i);
            if (bus == null) continue;
            long[] result = findFirstValidTimeAndNext(startFrom, step, bus, i);
            startFrom = result[0];
            step = result[1] - result[0];
        }
        return startFrom;
    }
}