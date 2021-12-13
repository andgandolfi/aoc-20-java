package xyz.gandolfi.aoc20.day14;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class State {
    private final Map<Long, Long> memory;
    private String lastMask;

    public State() {
        memory = new HashMap<>();
    }

    void applyInstruction(Instruction instruction) {
        instruction.apply(this);
    }

    void applyInstructions(List<Instruction> instructions) {
        for (Instruction i : instructions)
            i.apply(this);
    }

    String getMask() {
        return lastMask;
    }

    public void setMask(String mask) {
        this.lastMask = mask;
    }

    Long getMemoryVal(long memoryAddr) {
        return memory.getOrDefault(memoryAddr, 0L);
    }

    Set<Map.Entry<Long, Long>> getMemoryDump() {
        return memory.entrySet();
    }

    void setMemoryVal(long memoryAddr, long memoryVal) {
        memory.put(memoryAddr, memoryVal);
    }

    long getSumOfValues() {
        return getMemoryDump().stream()
                .map(Map.Entry::getValue)
                .reduce(0L, Long::sum);
    }
}
