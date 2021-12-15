package xyz.gandolfi.aoc20.day16;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Rule {
    private final String ruleName;
    private final int[] ranges;
    private final Pattern pattern = Pattern.compile("^(\\d+)-(\\d+) or (\\d+)-(\\d+)$");

    public Rule(String ruleName, String ruleDefinitionString) {
        this.ruleName = ruleName;

        Matcher matcher = pattern.matcher(ruleDefinitionString);
        matcher.matches();
        ranges = new int[] {
                Integer.parseInt(matcher.group(1)),
                Integer.parseInt(matcher.group(2)),
                Integer.parseInt(matcher.group(3)),
                Integer.parseInt(matcher.group(4))
        };
    }

    public boolean isValidValue(int value) {
        return (value >= ranges[0] && value <= ranges[1]) || (value >= ranges[2] && value <= ranges[3]);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rule rule = (Rule) o;
        return Arrays.equals(ranges, rule.ranges);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(ranges);
    }

    public String getRuleName() {
        return ruleName;
    }

    @Override
    public String toString() {
        return "Rule{" +
                "ruleName='" + ruleName + '\'' +
                ", ranges=" + Arrays.toString(ranges) +
                '}';
    }
}
