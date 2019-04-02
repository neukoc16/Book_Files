package htl.neuabuer.book_files.Consumer;

import htl.neuabuer.book_files.BL.Book;
import htl.neuabuer.book_files.Queue.EmptyException;
import htl.neuabuer.book_files.Queue.MyQueue;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

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
            int c;
            for (String string : hmap.keySet()) {
                c = hmap.get(string);
                if (c > 1) {
                    System.out.println("yes");
                }
            }
        }
    }
}
