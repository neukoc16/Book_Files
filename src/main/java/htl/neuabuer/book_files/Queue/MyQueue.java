package htl.neuabuer.book_files.Queue;

import java.util.LinkedList;

public class MyQueue<T> {

    private final LinkedList<T> data = new LinkedList<>();
    private final int maxSize;

    public MyQueue(int maxSize) {
        this.maxSize = maxSize;
    }

    public void put(T value) throws FullException {
        if (data.size() == maxSize) {
            throw new FullException();
        }
        data.add(value);
    }

    public T get() throws EmptyException {
        if (data.isEmpty()) {
            throw new EmptyException();
        }
        return data.poll();
    }
}
