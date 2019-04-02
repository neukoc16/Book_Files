package htl.neuabuer.book_files.Producer;

import htl.neuabuer.book_files.BL.Book;
import htl.neuabuer.book_files.Queue.FullException;
import htl.neuabuer.book_files.Queue.MyQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Producer implements Runnable {

    private MyQueue<Book> books;

    public Producer(MyQueue<Book> books) {
        this.books = books;
    }

    @Override
    public void run() {
        while (true) {
            JFileChooser jfc = new JFileChooser("./files");
            int y = jfc.showOpenDialog(null);
            synchronized (books) {
                if (y == JFileChooser.APPROVE_OPTION) {
                    String word = JOptionPane.showInputDialog("Enter word: ");
                    try {
                        books.put(new Book(jfc.getSelectedFile().getPath(), word));
                    } catch (FullException ex) {
                        Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    books.notifyAll();
                }
                try {
                    books.wait();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
