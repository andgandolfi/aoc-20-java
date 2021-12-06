package xyz.gandolfi.aoc20.day02;

import java.util.ArrayList;
import java.util.List;

public class PasswordsDb {
    List<Password> passwords;

    public PasswordsDb(List<String> inputLines) {
        passwords = new ArrayList<>(inputLines.size());
        for (String input : inputLines)
            passwords.add(new Password(input));
    }

    public List<Password> getValidOccurrences() {
        return passwords.stream().filter(Password::isValidOccurrences).toList();
    }

    public List<Password> getValidPositions() {
        return passwords.stream().filter(Password::isValidPositions).toList();
    }
}
