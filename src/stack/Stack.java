package stack;

public interface Stack<T> {

    public void add(T element);
    public T pop() throws StackException;
    public T top() throws StackException;
    public boolean isEmpty();
    public int size();

}