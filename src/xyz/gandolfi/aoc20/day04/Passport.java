package xyz.gandolfi.aoc20.day04;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Passport {
    private String birthYear;
    private String issueYear;
    private String expirationYear;
    private String height;
    private String hairColor;
    private String eyeColor;
    private String passportId;
    private String countryId;

    private Passport() { }

    public static Passport parse(String inputData) {
        Passport passport = new Passport();
        Map<String, String> parsedInput = passport.parseInputDataAsMap(inputData);
        passport.birthYear = parsedInput.get("byr");
        passport.issueYear = parsedInput.get("iyr");
        passport.expirationYear = parsedInput.get("eyr");
        passport.height = parsedInput.get("hgt");
        passport.hairColor = parsedInput.get("hcl");
        passport.eyeColor = parsedInput.get("ecl");
        passport.passportId = parsedInput.get("pid");
        passport.countryId = parsedInput.get("cid");
        return passport;
    }

    private Map<String, String> parseInputDataAsMap(String inputData) {
        Map<String, String> mapping = new HashMap<>();
        String[] parts = inputData.trim().split("\\s+");
        for (String p : parts) {
            String[] keyVal = p.split(":");
            mapping.put(keyVal[0], keyVal[1]);
        }
        return mapping;
    }

    private boolean validateBirthdayYear() {
        try {
            int year = Integer.parseInt(birthYear);
            return year >= 1920 && year <= 2002;
        } catch (NumberFormatException nfe)  {
            return false;
        }
    }

    private boolean validateIssueYear() {
        try {
            int year = Integer.parseInt(issueYear);
            return year >= 2010 && year <= 2020;
        } catch (NumberFormatException nfe)  {
            return false;
        }
    }

    private boolean validateExpirationYear() {
        try {
            int year = Integer.parseInt(expirationYear);
            return year >= 2020 && year <= 2030;
        } catch (NumberFormatException nfe)  {
            return false;
        }
    }

    private boolean validateHeight() {
        if (height == null || height.length() < 3) return false;
        int value;
        try {
            value = Integer.parseInt(height.substring(0, height.length() - 2));
        } catch (NumberFormatException nfe)  {
            return false;
        }
        return (height.endsWith("cm") && value >= 150 && value <= 193) ||
                (height.endsWith("in") && value >= 59 && value <= 76);
    }

    private boolean validateHairColor() {
        if (hairColor == null || hairColor.length() != 7 || !hairColor.startsWith("#")) return false;
        Pattern pattern = Pattern.compile("^[0-9a-f]{6}$");
        Matcher matcher = pattern.matcher(hairColor.substring(1));
        return matcher.matches();
    }

    private boolean validateEyeColor() {
        if (eyeColor == null || eyeColor.length() != 3) return false;
        Pattern pattern = Pattern.compile("^(amb|blu|brn|gry|grn|hzl|oth)$");
        Matcher matcher = pattern.matcher(eyeColor);
        return matcher.matches();
    }

    private boolean validatePassportId() {
        if (passportId == null || passportId.length() != 9) return false;
        Pattern pattern = Pattern.compile("^[0-9]{9}$");
        Matcher matcher = pattern.matcher(passportId);
        return matcher.matches();
    }

    public boolean hasAllNecessaryFields() {
        return birthYear != null &&
                issueYear != null &&
                expirationYear != null &&
                height != null &&
                hairColor != null &&
                eyeColor != null &&
                passportId != null;
    }

    public boolean isValid() {
        return hasAllNecessaryFields() &&
                validateBirthdayYear() &&
                validateIssueYear() &&
                validateExpirationYear() &&
                validateHeight() &&
                validateHairColor() &&
                validateEyeColor() &&
                validatePassportId();
    }
}
