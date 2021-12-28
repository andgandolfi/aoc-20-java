package xyz.gandolfi.aoc20.day25;

public class LoopSizeFinder {
    private static final int divisionNum = 20201227;

    public static int findLoopSize(int pubKey, int subjNumber) {
        int value = 1;
        int loopSize = 0;
        while (value != pubKey) {
            value *= subjNumber;
            value %= divisionNum;
            loopSize++;
        } ;
        return loopSize;
    }

    public static long findEncryptionKey(int pubKey, int loopSize) {
        long value = 1;
        for (int i = 0; i < loopSize; ++i) {
            value *= pubKey;
            value %= divisionNum;
        }
        return value;
    }
}
