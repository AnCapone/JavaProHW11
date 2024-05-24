import java.io.File;
import java.util.ArrayList;

public class Controller {
    View view = new View();
    public void booksList() {
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

    public File getBook(String bookName) {
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
}
