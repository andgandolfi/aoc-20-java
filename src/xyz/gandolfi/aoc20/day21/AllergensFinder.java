package xyz.gandolfi.aoc20.day21;

import java.util.*;

public class AllergensFinder {
    private final List<MenuEntry> menuEntries;

    public AllergensFinder(List<MenuEntry> menuEntries) {
        this.menuEntries = menuEntries;
    }

    public Set<String> getAllKnownAllergens() {
        Set<String> allergens = new HashSet<>();
        for (MenuEntry entry : menuEntries)
            allergens.addAll(entry.getAllergens());
        return allergens;
    }

    public Set<String> findPossibleIngredientsForAllergen(String allergen) {
        Set<String> commonIngredients = null;
        for (MenuEntry entry : menuEntries)
            if (entry.getAllergens().contains(allergen))
                if (commonIngredients == null)
                    commonIngredients = new HashSet<>(entry.getIngredients());
                else
                    commonIngredients.retainAll(entry.getIngredients());
        return commonIngredients;
    }

    public Map<String, String> findMappingForAllKnownAllergens() {
        Map<String, String> allergenToIngredientMapping = new HashMap<>();
        Set<String> identifiedIngredients = new HashSet<>();
        Set<String> identifiedAllergens = new HashSet<>();
        Set<String> allergensToBeIdentified = getAllKnownAllergens();

        while (allergensToBeIdentified.size() > 0) {
            for (String allergen : allergensToBeIdentified) {
                Set<String> possibleIngredients = findPossibleIngredientsForAllergen(allergen);
                possibleIngredients.removeAll(identifiedIngredients);
                if (possibleIngredients.size() == 1) {
                    String ingredient = possibleIngredients.stream().findFirst().get();
                    allergenToIngredientMapping.put(allergen, ingredient);
                    identifiedAllergens.add(allergen);
                    identifiedIngredients.add(ingredient);
                }
            }
            allergensToBeIdentified.removeAll(identifiedAllergens);
        }

        return allergenToIngredientMapping;
    }
}
