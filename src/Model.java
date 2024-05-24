import java.io.File;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Model {

    Controller controller = new Controller();

    public void run() {  //run the application
        controller.booksList();
        String bookName = controller.view.readBookName();
        File book = controller.getBook(bookName);
        Map<String, Integer> parsedWords = controller.parseBook(book);
        List<Map.Entry<String, Integer>> frequentedWords = mostFrequentedWords(parsedWords);
        controller.createStatisticFile(bookName, parsedWords, frequentedWords);
        controller.view.printStatistic(parsedWords, frequentedWords);
    }

    public List<Map.Entry<String, Integer>> mostFrequentedWords(Map<String, Integer> parsedWords) {
        //sort the words by frequency and return the 10 most frequent words using the Stream API
        return parsedWords.entrySet()
                .stream()
                .filter(word -> word.getKey().length() > 2)
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(10)
                .collect(Collectors.toList());

    }
}
