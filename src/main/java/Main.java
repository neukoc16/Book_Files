
import htl.neuabuer.book_files.Consumer.Consumer;
import htl.neuabuer.book_files.Producer.Producer;
import htl.neuabuer.book_files.Queue.MyQueue;

public class Main {

    public static void main(String[] args) {
        MyQueue books = new MyQueue<>(3);
        Producer p = new Producer(books);
        Consumer c = new Consumer(books);
        new Thread(p).start();
        new Thread(c).start();
    }
}
