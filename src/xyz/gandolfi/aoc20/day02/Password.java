package xyz.gandolfi.aoc20.day02;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Password {
    private String password;
    private char character;
    private int param1;
    private int param2;

    private final Pattern regExp = Pattern.compile("^(\\d+)-(\\d+)\\s([a-z]):\\s([a-z]+)$");

    public Password(String inputLine) {
        Matcher matcher = regExp.matcher(inputLine);
        if (matcher.matches()) {
            param1 = Integer.parseInt(matcher.group(1));
            param2 = Integer.parseInt(matcher.group(2));
            character = matcher.group(3).charAt(0);
            password = matcher.group(4);
        }
    }

    public boolean isValidOccurrences() {
        int charCount = 0;
        for (int i = 0; i < password.length(); ++i)
            if (password.charAt(i) == character)
                charCount++;
        return param1 <= charCount && charCount <= param2;
    }

    public boolean isValidPositions() {
        return (password.charAt(param1 - 1) == character) ^ (password.charAt(param2 - 1) == character);
    }
}
