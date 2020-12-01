package day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

public class day1 {

    final static String INPUT_FILE = "inputDay1.txt";
    final static Integer SEARCHED_SUM = 2020;
    static ArrayList<Integer> inputList = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        readFile();
        puzzle1();
        puzzle2();
    }

    static void puzzle1() {
        System.out.format("\n-------------------------------");
        System.out.format("\nFind the two entries that sum to 2020, what do you get if you multiply them together?:");
        findTupleSum();
    }

    static void findTupleSum(){
        HashMap<Integer, Integer> diferences = new HashMap<>();
        Integer solutionIndex;
        for (int index = 0; index < inputList.size(); index++) {
            Integer currentNumber = inputList.get(index);
            solutionIndex = diferences.get(currentNumber);
            if (solutionIndex != null) {
                System.out.format("\nTuple: %d  %d", currentNumber, inputList.get(solutionIndex));
                System.out.format("\nProduct: %d", currentNumber * inputList.get(solutionIndex));
                return;
            }
            diferences.put(SEARCHED_SUM - currentNumber, index);
        }
    }

    static void puzzle2(){
        System.out.format("\n-------------------------------");
        System.out.format("\nWhat is the product of the three entries that sum to 2020?:");
        find3TupleSum(0, new ArrayList<>(), 0);
    }

    private static void find3TupleSum(int index, ArrayList<Integer> takenNumbers, int partialSum) {
        if (partialSum == SEARCHED_SUM && takenNumbers.size() == 3) {
            System.out.format("\n3-tuple:");
            takenNumbers.forEach(num -> System.out.format(" %d ",num));
            System.out.format("\nProduct: %d", takenNumbers.stream().reduce((n1, n2) -> n1 * n2).get());
            return;
        }
        if (index < inputList.size()) {
            find3TupleSum(index + 1, new ArrayList<>(takenNumbers), partialSum);
            Integer currentNumber = inputList.get(index);
            if (currentNumber + partialSum <= SEARCHED_SUM) {
                partialSum += currentNumber;
                takenNumbers.add(currentNumber);
                find3TupleSum(index + 1, new ArrayList<>(takenNumbers), partialSum);
            }
        }
    }

    static void readFile() throws Exception {
        String line;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(INPUT_FILE));
            line = bufferedReader.readLine();
            do {
                inputList.add(Integer.parseInt(line));
                line = bufferedReader.readLine();
            } while (line != null);
            bufferedReader.close();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
