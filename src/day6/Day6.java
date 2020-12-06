package day6;

import adventOfCode.LinesParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;

public class Day6 {

    static final String INPUT_FILE = "C:\\Learn\\advent-of-code\\src\\adventOfCode\\inputDay6.txt";
    //static final String TEST_FILE = "C:\\Learn\\advent-of-code\\src\\day6\\dataTest.txt";
    static ArrayList<String> customsDeclarations;

    public static void main(String[] args) throws Exception {
        customsDeclarations = LinesParser.getParagraphsFromFile(INPUT_FILE);
        System.out.format("\nPuzzle 1 solution: %d",puzzle1());
        System.out.format("\nPuzzle 2 solution: %d",puzzle2());

    }

    private static long puzzle1() {
        return customsDeclarations
                .parallelStream()
                .map(questions -> questions.replaceAll(" ", "").chars().distinct().count())
                .reduce((long)0,Long::sum);
    }

    private static long puzzle2() {
        return customsDeclarations
                .parallelStream()
                .map(answers -> {
                    String[] answersArray = answers.split(" ");
                    ArrayList<HashSet<Integer>> answersSets = Arrays
                            .stream(answersArray)
                            .map(answer -> answer.chars().boxed().collect(Collectors.toCollection(HashSet::new)))
                            .collect(Collectors.toCollection(ArrayList::new));
                    return answersArray[0]
                            .chars()
                            .filter(letter -> answersSets.stream().allMatch(answer -> answer.contains(letter)))
                            .count();
                })
                .reduce((long)0,Long::sum);
    }
}
