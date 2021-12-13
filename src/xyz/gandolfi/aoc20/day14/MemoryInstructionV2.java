package xyz.gandolfi.aoc20.day14;

import java.util.ArrayList;
import java.util.List;

public class MemoryInstructionV2 extends MemoryInstruction {
    public MemoryInstructionV2(String instructionString) {
        super(instructionString);
    }

    @Override
    public State apply(State state) {
        String lastMask = padLeftZeros(state.getMask(), 36);
        String memAddressBits = padLeftZeros(Long.toBinaryString(memAddress), 36);
        StringBuilder sb = new StringBuilder();
        int xs = 0;
        for (int i = 0; i < 36; ++i) {
            switch (lastMask.charAt(i)) {
                case 'X' -> {
                    sb.append('X');
                    xs++;
                }
                case '1' -> sb.append('1');
                case '0' -> sb.append(memAddressBits.charAt(i));
                default -> throw new IllegalStateException("Unexpected value: " + lastMask.charAt(i));
            }
        }
        String newAddressMask = sb.toString();
        List<String> addresses = new ArrayList<>();
        addresses.add(newAddressMask);
        for (int i = 0; i < xs; ++i) {
            List<String> tmp = new ArrayList<>();
            for (String addr : addresses) {
                tmp.add(addr.replaceFirst("X", "0"));
                tmp.add(addr.replaceFirst("X", "1"));
            }
            addresses = tmp;
        }
        for (String addr : addresses)
            state.setMemoryVal(Long.parseLong(addr, 2), memValue);
        return state;
    }

    private String padLeftZeros(String inputString, int length) {
        if (inputString.length() >= length) {
            return inputString;
        }
        StringBuilder sb = new StringBuilder();
        while (sb.length() < length - inputString.length()) {
            sb.append('0');
        }
        sb.append(inputString);

        return sb.toString();
    }
}
