package xyz.gandolfi.aoc20.day06;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Group {
    private final List<Person> persons;

    public Group() {
        this.persons = new ArrayList<>();
    }

    public Group(List<Person> persons) {
        this.persons = persons;
    }

    public Group addPerson(Person p) {
        persons.add(p);
        return this;
    }

    public Set<String> getAllAnswersUnion() {
        Set<String> allAnswers = new HashSet<>();
        for (Person p : persons)
            allAnswers.addAll(p.getAnswers());
        return allAnswers;
    }

    public Set<String> getAllAnswersIntersection() {
        if (persons.isEmpty()) return new HashSet<>();
        Set<String> allAnswers = persons.get(0).getAnswers();
        for (Person p : persons) {
            allAnswers.retainAll(p.getAnswers());
        }
        return allAnswers;
    }
}
