package xyz.gandolfi.aoc20.day03;

import java.util.List;

public class Forest {
    List<String> forestPortion;

    public Forest(List<String> inputLines) {
        forestPortion = inputLines;
    }

    public boolean isTree(int line, int column) {
        if (line >= forestPortion.size()) return false;
        String forestLine = forestPortion.get(line);
        return forestLine.charAt(column % forestLine.length()) == '#';
    }

    public int crossForest(int startLine, int startColumn,
                           int rightStep, int downStep) {
        int currLine = startLine;
        int currColumn = startColumn;
        int treesCount = 0;

        while (currLine < forestPortion.size()) {
            if (isTree(currLine, currColumn)) treesCount++;
            currLine += downStep;
            currColumn += rightStep;
        }

        return treesCount;
    }
}
