package xyz.gandolfi.aoc20.day21;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MenuEntry {
    private Set<String> ingredients;
    private Set<String> allergens;

    public MenuEntry(String inputLine) {
        String[] parts = inputLine.split(" \\(contains ");
        ingredients = new HashSet<>(Arrays.asList(parts[0].split(" ")));
        allergens = new HashSet<>(Arrays.asList(parts[1].substring(0, parts[1].length() - 1).split(", ")));
    }

    public Set<String> getIngredients() {
        return ingredients;
    }

    public Set<String> getAllergens() {
        return allergens;
    }

    @Override
    public String toString() {
        return "MenuEntry{" +
                "ingredients=" + ingredients +
                ", allergens=" + allergens +
                '}';
    }
}
