import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

public class View {
    public void printMessage(String message) { // print message to console
        System.out.println(message);
    }

    public void printBookList(List<String> books) {  //print the list of books
        printMessage("Books:");
        for (String book : books) {
            printMessage(book);
        }
    }

    public String readBookName() { //read the name of the book from console
        printMessage("Enter the name of the book:");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            return reader.readLine();
        } catch (IOException e) {
            return null;
        }
    }

    public void printStatistic(Map<String, Integer> parsedWords, List<Map.Entry<String, Integer>> frequentedWords) { //print the statistic to console
        printMessage("Words count: " + parsedWords.size());
        printMessage("Frequented words:");
        frequentedWords.stream().map(word -> word.getKey() + " - " + word.getValue()).forEach(this::printMessage);
        //All words are not displayed to console to prevent a large number of lines in the console.
        // Only the total number of words and the 10 most used words are displayed
    }
}
