package xyz.gandolfi.aoc20.day06;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Person {
    private final Set<String> answers;

    public Person(String answers) {
        this.answers = new HashSet<>(Arrays.stream(answers.split("")).toList());
    }

    public Set<String> getAnswers() {
        return answers;
    }
}
