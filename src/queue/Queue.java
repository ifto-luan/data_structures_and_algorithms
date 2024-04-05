package queue;

public interface Queue<T> {

    public void add(T element);
    public T pop() throws QueueException;
    public T head() throws QueueException;
    public boolean isEmpty();
    public int size();
}