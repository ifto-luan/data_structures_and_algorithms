package stack;

import node.Node;

public class DynamicStack<T> implements Stack<T> {

    private Node<T> top;

    public DynamicStack() {

    }

    @Override
    public void add(T element) {

        if (top == null) {
            top = new Node<T>(element);
        }

        top = new Node<T>(element, top);
        
    }

    @Override
    public boolean isEmpty() {
        return top == null;
    }

    @Override
    public T pop() throws StackException {

        if (isEmpty()) {
            throw new StackException("The stack is empty. Cannot perform pop");
        }

        T content = top.getContent();
        top =  top.getReference();
        return content;

    }

    @Override
    public T top() throws StackException {

        if (top == null) {
            throw new StackException("The stack is empty. Cannot see the top");
        }

        return top.getContent();
    }

    @Override
    public int size() {

        if (top == null) {
            return 0;
        }
        
        int size = 0;

        Node<T> checkTop = top;

        while(checkTop.getReference() != null) {

            size++;
            checkTop = checkTop.getReference();

        }

        return size;

    }

    
}
