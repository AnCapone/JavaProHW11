import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Controller {
    View view = new View();

    public void booksList() {  //create a list of books located in the src folder
        File folder = new File("src");
        File[] files = folder.listFiles();
        ArrayList<String> books = new ArrayList<>();
        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().endsWith(".txt")) {
                    books.add(file.getName());
                }
            }
        }
        if (books.isEmpty()) {
            view.printMessage("No books found in the src folder.");
        } else {
            view.printBookList(books);
        }
    }

    public File getBook(String bookName) {  //check existing the book in the src folder
        File book = new File("src", bookName + ".txt");
        if (book.exists()) {
            view.printMessage("Book " + bookName + " is found.");
            view.printMessage("Start parsing the book...");
            return book;
        } else {
            view.printMessage("Book " + bookName + " is not found.");
            return null;
        }
    }

    public Map<String, Integer> parseBook(File book) {  //parse the book and count the words
        HashMap<String, Integer> words = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(book))) {
            String line; //temporary variable to store the line
            while ((line = reader.readLine()) != null) {
                String clearLine = line.replaceAll("\\p{Punct}", "").toLowerCase(); //remove punctuation marks and convert to lowercase
                String[] lineWords = clearLine.split("\\s+"); //split the line into words
                for (String word : lineWords) {
                    if (word.isEmpty()) { //skip empty "words"
                        continue;
                    }
                    words.put(word.toLowerCase(), words.getOrDefault(word, 0) + 1); //add words to the map or increment the counter
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        view.printMessage("The book is parsed.");
        return words;
    }

    public void createStatisticFile(String bookName, Map<String, Integer> parsedWords, List<Map.Entry<String, Integer>> frequentedWords) {
        File statisticFile = new File("src", bookName + "_statistic.txt"); //create a file with the statistic
        try (FileWriter writer = new FileWriter(statisticFile)) {
            writer.write("The most frequented words in the book " + bookName + ":\n");
            for (Map.Entry<String, Integer> word : frequentedWords) { //write the most frequented words to the file
                writer.write(word.getKey() + " - " + word.getValue() + "\n");
            }
            writer.write("\nAll words in the book " + bookName + ":\n");
            for (Map.Entry<String, Integer> word : parsedWords.entrySet()) {
                writer.write(word.getKey() + " - " + word.getValue() + "\n"); //write all words to the file
            }
            writer.write("\nTotal unique words in the book " + bookName + ": " + parsedWords.size()); //write the total number of unique words to the file
            view.printMessage("The statistic file is created.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
