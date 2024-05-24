import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Model {

    Controller controller = new Controller();

    public void run() {
        controller.booksList();
        String bookName = controller.view.readBookName();
        File book = controller.getBook(bookName);
        Map<String, Integer> parsedWords = controller.parseBook(book);
        List<Map.Entry<String, Integer>> frequentedWords = mostFrequentedWords(parsedWords);
        controller.createStatisticFile(bookName, parsedWords, frequentedWords);
    }

    public List<Map.Entry<String, Integer>> mostFrequentedWords(Map<String, Integer> parsedWords) {
        List<Map.Entry<String, Integer>> sortedWords = parsedWords.entrySet()
                .stream()
                .filter(word -> word.getKey().length() > 2)
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(10)
                .collect(Collectors.toList());

        return sortedWords;

    }
}
