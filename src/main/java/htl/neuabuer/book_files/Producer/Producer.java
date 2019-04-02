package htl.neuabuer.book_files.Producer;

import htl.neuabuer.book_files.BL.Book;
import htl.neuabuer.book_files.Queue.FullException;
import htl.neuabuer.book_files.Queue.MyQueue;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Producer implements Runnable {

    private final MyQueue<Book> books;

    public Producer(MyQueue<Book> books) {
        this.books = books;
    }

    @Override
    public void run() {
        File f = new File("./files");
        for (File listFile : f.listFiles()) {
            synchronized (books) {
                try {
                    books.put(new Book(listFile.getPath()));
                    books.notifyAll();
                } catch (FullException e) {
                    try {
                        books.wait();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }
}
