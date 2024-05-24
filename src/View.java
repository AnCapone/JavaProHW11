import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class View {
    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printBookList(List<String> books) {
        printMessage("Books:");
        for (String book : books) {
            printMessage(book);
        }
    }

    public String readBookName() {
        printMessage("Enter the name of the book:");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            return reader.readLine();
        } catch (IOException e) {
            return null;
        }
    }
}
