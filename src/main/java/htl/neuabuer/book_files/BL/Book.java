package htl.neuabuer.book_files.BL;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Book {

    private final String inputfilename;
    private String text;

    public Book(String inputfilename, String text) {
        this.inputfilename = inputfilename;
        this.text = text;
    }

    public HashMap<String, Integer> countWords() {
        String words[] = text.split(" ");
        HashMap<String, Integer> countWords = new HashMap<>();

        for (String word : words) {
            if (countWords.containsKey(word)) {
                countWords.put(word, countWords.get(word) + 1);
            } else {
                countWords.put(word, 1);
            }
        }
        for (String string : countWords.keySet()) {
            if (countWords.get(string) == 1) {
                countWords.remove(string);
            }
        }
        return countWords;
    }

    public void load() throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(new File(inputfilename)));
        while (br.ready()) {
            text += br.readLine();
        }
    }
}
