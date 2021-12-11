package xyz.gandolfi.aoc20.day12;

import java.util.List;

public class RunnerA implements Runner {
    private final List<Instruction> instructions;

    private int horizontalDelta = 1;
    private int verticalDelta = 0;

    private int positionEast = 0;
    private int positionNorth = 0;

    public RunnerA(List<Instruction> instructions) {
        this.instructions = instructions;
    }

    public void run() {
        for (Instruction ins : instructions) {
            switch (ins.getAction()) {
                case 'N':
                    positionNorth += ins.getValue();
                    break;
                case 'S':
                    positionNorth -= ins.getValue();
                    break;
                case 'E':
                    positionEast += ins.getValue();
                    break;
                case 'W':
                    positionEast -= ins.getValue();
                    break;
                case 'F':
                    positionNorth += ins.getValue() * verticalDelta;
                    positionEast += ins.getValue() * horizontalDelta;
                    break;
                case 'L':
                    for (int l = 0; l < ins.getValue() / 90; ++l) {
                        int verticalTmp = verticalDelta;
                        verticalDelta = horizontalDelta;
                        horizontalDelta = -verticalTmp;
                    }
                    break;
                case 'R':
                    for (int r = 0; r < ins.getValue() / 90; ++r) {
                        int verticalTmp = verticalDelta;
                        verticalDelta = -horizontalDelta;
                        horizontalDelta = verticalTmp;
                    }
                    break;
            }
        }
    }

    public int getPositionNorth() {
        return positionNorth;
    }

    public int getPositionEast() {
        return positionEast;
    }
}
