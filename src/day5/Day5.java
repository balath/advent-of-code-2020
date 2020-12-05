package day5;

import adventOfCode.LinesParser;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Day5 {

    static final String INPUT_FILE = "C:\\Learn\\advent-of-code\\src\\adventOfCode\\inputDay5.txt";
    static ArrayList<String> boardingPasses;

    public static void main(String[] args) throws Exception {
        boardingPasses = LinesParser.getLinesFromFile(INPUT_FILE);
        System.out.format("\nPuzzle 1 solution: %d",puzzle1());
        System.out.format("\nPuzzle 2 solution: %d",puzzle2());

    }

    private static int puzzle2() {
        return calculateID()
                .stream()
                .sorted()
                .reduce((x, y) -> y == (x + 1)? y : x)
                .get() + 1;
    }

    private static int puzzle1() {
        return calculateID()
                .stream()
                .reduce((x, y) -> x > y ? x : y)
                .get();
    }

    private static ArrayList<Integer> calculateID() {
        return boardingPasses.stream()
                .map(seat -> seat
                        .replaceAll("F","0")
                        .replaceAll("B", "1")
                        .replaceAll("L", "0")
                        .replaceAll("R", "1")
                )
                .map(seat -> (
                        Integer.parseInt(seat.substring(0,7), 2) * 8)
                        + Integer.parseInt(seat.substring(7), 2)
                )
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
