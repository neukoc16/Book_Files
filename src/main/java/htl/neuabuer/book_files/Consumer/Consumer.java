package htl.neuabuer.book_files.Consumer;

import htl.neuabuer.book_files.BL.Book;
import htl.neuabuer.book_files.Queue.MyQueue;

public class Consumer implements Runnable {

    private MyQueue<Book> books;

    public Consumer(MyQueue<Book> books) {
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
