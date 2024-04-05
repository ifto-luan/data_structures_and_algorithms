package linkedlist;

public interface IList<T> {

    void add (T element);
    T remove(T element);
    T get(int index);
    long size();
    boolean isEmpty();

}