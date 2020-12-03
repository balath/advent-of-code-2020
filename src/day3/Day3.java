package day3;

import java.util.ArrayList;
import java.util.Arrays;

public class Day3 {

    static final String INPUT_FILE = "C:\\Learn\\advent-of-code\\src\\adventOfCode\\inputDay3.txt";
    static final char TREE_CHAR = '#';

    static ArrayList<String> map = new ArrayList<>();
    static int mapHeight, mapLength;

    public static void main(String[] args) throws Exception {
        initFields();
        System.out.format("\nPuzzle 1 solution: %d",puzzle1());
        System.out.format("\nPuzzle 2 solution: %d",puzzle2());
    }

    static void initFields() throws Exception {
        map = adventOfCode.LinesParser.getLinesFromFile(INPUT_FILE);
        mapHeight = map.size();
        mapLength = map.get(0).length();
    }

    /**
     * Starting at the top-left corner of your map and following a slope of right 3 and down 1,
     * how many trees would you encounter?
     * @return number of trees encountered
     */
    static int puzzle1() {
        return traverseMap(0,0,3, 1);
    }

    /**
     * Determine the number of trees you would encounter if, for each of the following slopes, you start at the
     * top-left corner and traverse the map all the way to the bottom:     *
     *     Right 1, down 1.
     *     Right 3, down 1. (This is the slope you already checked.)
     *     Right 5, down 1.
     *     Right 7, down 1.
     *     Right 1, down 2.
     * What do you get if you multiply together the number of trees encountered on each of the listed slopes?
     * @return product of trees encountered on each slope
     */
    static int puzzle2() {
        return Arrays.stream(new int[][]{
                {1, 1},
                {3, 1},
                {5, 1},
                {7, 1},
                {1, 2}
        }).mapToInt(slope -> traverseMap(0,0,slope[0], slope[1]))
        .boxed()
        .reduce(1, (n, m) -> n * m);
    }

    /**
     * Recursively traverse the map to bottom, checking if current position matches a tree and accumulating these.
     * @return encountered trees
     */
    private static int traverseMap(int currentRow, int currentCol, int moveRight, int moveDown) {
        int treeMatch = map.get(currentRow).charAt(currentCol) == TREE_CHAR ? 1 : 0;
        int nextRow = (currentRow + moveDown);
        int nextCol = (currentCol + moveRight) % mapLength;
        if (nextRow >= mapHeight) return treeMatch;
        return treeMatch + traverseMap(nextRow, nextCol, moveRight, moveDown);
    }
}
