package day4;

import adventOfCode.LinesParser;

import java.util.*;
import java.util.stream.Collectors;

public class Day4 {

    static class PassportField {
        private String key;
        private String value;
        public PassportField(String field){
            String[] keyValue = field.split(":");
            key = keyValue[0];
            value = keyValue[1];
        }
        String getKey(){ return key; }
        String getValue(){ return value; }
    }

    static final String INPUT_FILE = "C:\\Learn\\advent-of-code\\src\\adventOfCode\\inputDay4.txt";
    static final Set<String> REQUIRED_FIELDS = Set.of("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid");

    static ArrayList<HashMap<String, String>> passports;

    public static void main(String[] args) throws Exception {
        parserPassportsFromInput(LinesParser.getParagraphsFromFile(INPUT_FILE));
        System.out.format("\nPuzzle 1 solution: %d",puzzle1());
        System.out.format("\nPuzzle 2 solution: %d",puzzle2());
    }

    static long puzzle1() {
        return passports
                .stream()
                .filter(passport -> REQUIRED_FIELDS.stream().allMatch(field -> passport.keySet().contains(field)))
                .count();
    }

    static long puzzle2() {
        return passports
                .stream()
                .filter(passport ->
                        REQUIRED_FIELDS.stream().allMatch(field -> passport.keySet().contains(field)) &&
                        validBirth(passport.get("byr")) &&
                        validExpiration(passport.get("eyr")) &&
                        validIssue(passport.get("iyr")) &&
                        validHair(passport.get("hcl")) &&
                        validEye(passport.get("ecl")) &&
                        validHeight(passport.get("hgt")) &&
                        validPid(passport.get("pid"))
                ).count();
    }
    static void parserPassportsFromInput(ArrayList<String> input) {
        passports = input
                .stream()
                .map(passportLine -> passportLine.split(" "))
                .map(fieldsArray -> Arrays.stream(fieldsArray)
                        .map(PassportField::new)
                        .collect(Collectors.toMap(
                                PassportField::getKey,
                                PassportField::getValue,
                                (k, v) -> k + v,
                                HashMap::new))
                ).collect(Collectors.toCollection(ArrayList::new));
    }

    static boolean validBirth(String s) {
        int num = Integer.parseInt(s);
        return num >= 1920 && num <=2002;
    }

    static boolean validIssue(String s) {
        int num = Integer.parseInt(s);
        return num >= 2010 && num <=2020;
    };

    static boolean validExpiration(String s) {
        int num = Integer.parseInt(s);
        return num >= 2020 && num <=2030;
    };

    static boolean validHeight(String s) {
        int num = Integer.parseInt(s.substring(0,s.length() - 2));
        if (s.endsWith("cm")) return num >= 150 && num <=193;
        return num >= 59 && num <= 76;
    };

    static boolean validHair(String s) {
        return s.matches("#[a-f0-9]{6}");
    }

    static boolean validEye(String s) {
        return Set.of("amb", "blu", "brn", "gry", "grn", "hzl", "oth").contains(s);
    }

    static boolean validPid(String s) {
        return s.matches("[0-9]{9}");
    }

}
