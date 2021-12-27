package xyz.gandolfi.aoc20.day21;

import xyz.gandolfi.aoc20.Utils;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        List<String> inputLines = Utils.getInputFileLines("day21.txt");
        assert inputLines != null;

        List<MenuEntry> entries = inputLines.stream()
            .map(MenuEntry::new)
            .toList();

        AllergensFinder allergensFinder = new AllergensFinder(entries);
        Map<String, String> allergenToIngredientMapping =
                allergensFinder.findMappingForAllKnownAllergens();

        System.out.print("Day 21a: ");
        Set<String> ingredientsNotToBeCounted = new HashSet<>(allergenToIngredientMapping.values());
        int countA = 0;
        for (MenuEntry entry : entries) {
            Set<String> ingredientsLeft = new HashSet<>(entry.getIngredients());
            ingredientsLeft.removeAll(ingredientsNotToBeCounted);
            countA += ingredientsLeft.size();
        }
        System.out.println(countA);

        System.out.print("Day 21b: ");
        var sortedIngredientsWithAllergens = allergenToIngredientMapping.entrySet().stream()
                .sorted((e1, e2) -> e1.getKey().compareTo(e2.getKey()))
                .map(Map.Entry::getValue)
                .toList();
        System.out.println(String.join(",", sortedIngredientsWithAllergens));
    }
}
