package day2;
import adventOfCode.LinesParser;

import java.util.ArrayList;

public class Day2 {

    private static final String INPUT_FILE = "C:\\Learn\\advent-of-code\\src\\adventOfCode\\inputDay2.txt";
    static ArrayList<String> lines;

    /**
     * Lines in input file looks like this format: "1-3 a: abcde"
     * Each policy (line in input file) actually describes two positions in the password, where 1 means the first
     * character, 2 means the second character, and so on. (Be careful; Toboggan Corporate Policies have no concept
     * of "index zero"!) Exactly one of these positions must contain the given letter. Other occurrences of the letter
     * are irrelevant for the purposes of policy enforcement.
     **/
    public static void main(String[] args) throws Exception {
        lines = LinesParser.getLinesFromFile(INPUT_FILE);
        long validPassPuzzle1 = puzzle(lines, 1);
        long validPassPuzzle2 = puzzle(lines, 2);
        System.out.format("\nPuzzle 1 solution: %d", validPassPuzzle1);
        System.out.format("\nPuzzle 2 solution: %d", validPassPuzzle2);
    }

    /* Takes a List with input file lines and process them, check each password validity and returns total
     * number of valid passwords */
    static long puzzle(ArrayList<String> lines, int puzzle){
        return lines.stream()
                .map(line -> line.split("-| |: "))
                .filter(lineArray -> passIsValid(lineArray, puzzle))
                .count();
    }

    private static boolean passIsValid(String[] lineArray, int puzzle) {
        switch (puzzle) {
            case 1: return policiesPuzzle1(lineArray);
            case 2: return policiesPuzzle2(lineArray);
            default: return false;
        }
    }

    /**
     * Check if a password is valid with these parameters:
     *  lineArray[0] -> min number of ocurrences of the key char
     *  lineArray[1] -> max number of ocurrences of the key char
     *  lineArray[2] -> key char
     *  lineArray[3] -> password
     */
    static boolean policiesPuzzle1(String[] lineArray) {
        int min, max, letterCodePoint;
        long ocurrencesMatched;
        min = Integer.parseInt(lineArray[0]);
        max = Integer.parseInt(lineArray[1]);
        letterCodePoint = lineArray[2].codePointAt(0);
        ocurrencesMatched = lineArray[3].chars().filter(codePoint -> codePoint == letterCodePoint).count();
        return ocurrencesMatched >= min && ocurrencesMatched<= max;
    }

    /**
     * Check if a password is valid with these parameters:
     *  lineArray[0] -> absolute position of the key char, option 1
     *  lineArray[1] -> absolute position of the key char, option 2
     *  lineArray[2] -> key char
     *  lineArray[3] -> password
     */
    private static boolean policiesPuzzle2(String[] lineArray) {
        int pos1, pos2, letterCodePoint;
        boolean pos1Match, pos2Match;
        pos1 = Integer.parseInt(lineArray[0]);
        pos2 = Integer.parseInt(lineArray[1]);
        letterCodePoint = lineArray[2].codePointAt(0);
        pos1Match = lineArray[3].charAt(pos1 - 1) == letterCodePoint;
        pos2Match = lineArray[3].charAt(pos2 - 1) == letterCodePoint;
        return pos1Match ^ pos2Match;
    }
}
