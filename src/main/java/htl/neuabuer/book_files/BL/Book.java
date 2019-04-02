package htl.neuabuer.book_files.BL;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Book {

    private final String inputfilename;
    private String text;

    public Book(String inputfilename) {
        this.inputfilename = inputfilename;
        try {
            load();
        } catch (IOException ex) {
            Logger.getLogger(Book.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getFile() {
        return new File(inputfilename).getName();
    }

    public HashMap<String, Integer> countWords() {
        String words[] = text.split("[^a-zA-Z]+");
        HashMap<String, Integer> countWords = new HashMap<>();

        for (String word : words) {
            if (countWords.containsKey(word)) {
                countWords.put(word, countWords.get(word) + 1);
            } else {
                countWords.put(word, 1);
            }
        }

        return countWords;
    }

    public void load() throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(new File(inputfilename)));
        while (br.ready()) {
            text += br.readLine() + " ";
        }
    }
}
