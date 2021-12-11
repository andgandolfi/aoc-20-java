package xyz.gandolfi.aoc20.day12;

import java.util.List;

public class RunnerB implements Runner {
    private final List<Instruction> instructions;

    private int directionEast = 10;
    private int directionNorth = 1;

    private int positionEast = 0;
    private int positionNorth = 0;

    public RunnerB(List<Instruction> instructions) {
        this.instructions = instructions;
    }

    public void run() {
        for (Instruction ins : instructions) {
            switch (ins.getAction()) {
                case 'N':
                    directionNorth += ins.getValue();
                    break;
                case 'S':
                    directionNorth -= ins.getValue();
                    break;
                case 'E':
                    directionEast += ins.getValue();
                    break;
                case 'W':
                    directionEast -= ins.getValue();
                    break;
                case 'F':
                    positionEast += ins.getValue() * directionEast;
                    positionNorth += ins.getValue() * directionNorth;
                    break;
                case 'L':
                    for (int l = 0; l < ins.getValue() / 90; ++l) {
                        int directionNorthTmp = directionNorth;
                        directionNorth = directionEast;
                        directionEast = -directionNorthTmp;
                    }
                    break;
                case 'R':
                    for (int r = 0; r < ins.getValue() / 90; ++r) {
                        int directionNorthTmp = directionNorth;
                        directionNorth = -directionEast;
                        directionEast = directionNorthTmp;
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
