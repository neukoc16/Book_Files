package htl.neuabuer.book_files.Producer;

import htl.neuabuer.book_files.BL.Book;
import htl.neuabuer.book_files.Queue.MyQueue;

public class Producer implements Runnable {

    private MyQueue<Book> books;

    public Producer(MyQueue<Book> books) {
        this.books = books;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (books) {

            }
        }
    }
}
