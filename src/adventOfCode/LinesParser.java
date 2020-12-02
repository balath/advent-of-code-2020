package adventOfCode;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;


public class LinesParser {

    public static ArrayList<String> getLinesFromFile(String inputFile) throws Exception {
        ArrayList<String> lines = new ArrayList<>();
        String line;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile));
            line = bufferedReader.readLine();
            if (line == null) throw new NullPointerException("Empty file");
            do {
                lines.add(line);
                line = bufferedReader.readLine();
            } while (line != null);
            bufferedReader.close();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return lines;
    }
}
