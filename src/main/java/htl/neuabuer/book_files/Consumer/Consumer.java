package htl.neuabuer.book_files.Consumer;

import htl.neuabuer.book_files.BL.Book;
import htl.neuabuer.book_files.Queue.EmptyException;
import htl.neuabuer.book_files.Queue.MyQueue;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Consumer implements Runnable {

    private final MyQueue<Book> books;
    private HashMap<String, Integer> hmap;

    public Consumer(MyQueue<Book> books) {
        this.books = books;
    }

    @Override
    public void run() {
        while (true) {
            Book book = null;
            synchronized (books) {
                try {
                    book = books.get();
                    books.notifyAll();
                } catch (EmptyException ex) {
                    try {
                        books.wait();
                    } catch (InterruptedException ex1) {
                        Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex1);
                    }
                    continue;
                }
            }

            hmap = book.countWords();
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter("./output/" + book.getFile()));
                for (String string : hmap.keySet()) {
                    if (hmap.get(string) > 1) {
                        bw.write(string + ": " + hmap.get(string) + "\n");
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
