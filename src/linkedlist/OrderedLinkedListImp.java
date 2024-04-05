package linkedlist;

import java.util.NoSuchElementException;

import node.Node;

public class OrderedLinkedListImp<T extends Comparable<T>> implements IList<T> {

    private Node<T> head;
    private Node<T> tail;

    public OrderedLinkedListImp() {

    }

    @Override
    public void add(T element) {

        Node<T> newNode = new Node<>(element);

        if (isEmpty()) {

            head = newNode;
            tail = newNode;
            return;

        }

        if (element.compareTo(head.getContent()) < 0) {
            
            addFirst(element);
            return;

        }

        if (element.compareTo(tail.getContent()) >= 0) {

            addLast(element);
            return;

        }

        Node<T> iterator = head;

        while (element.compareTo(iterator.getReference().getContent()) >= 0) {

            iterator = iterator.getReference();

        }

        newNode.setReference(iterator.getReference());
        iterator.setReference(newNode);
    
    }

    private void addFirst(T element) throws IllegalArgumentException {

        if (element.compareTo(head.getContent()) >= 0)
            throw new IllegalArgumentException("this is not lesser than the first element");

        Node<T> newNode = new Node<>(element);

        newNode.setReference(head);
        head = newNode;
        
    }
    
    private void addLast(T element) {
        
        if (element.compareTo(tail.getContent()) < 0)
            throw new IllegalArgumentException("this is not greater than the last element");
        
        
        Node<T> newNode = new Node<>(element);

        tail.setReference(newNode);
        tail = newNode;

    }

    @Override
    public T get(int index) {
        return getNode(index).getContent();
    }

    private Node<T> getNode(int index) {

        if (index < 0 || index >= size())
            throw new ArrayIndexOutOfBoundsException();

        if (index == 0) {

            return head;

        } else if (index == size() - 1) {

            return tail;

        }

        Node<T> node = head;
        int searchIndex = 0;

        while (searchIndex != index) {

            node = node.getReference();
            searchIndex++;
        }

        return node;

    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }


    @Override
    public T remove(T element) {

        if (isEmpty())
            throw new NoSuchElementException();

        
        if (element.equals(head.getContent())) {
        
            Node<T> temp = head;
            head = head.getReference();
            temp.setReference(null);

            return temp.getContent();

        }

        Node<T> iterator = head;

        while (!iterator.getReference().getContent().equals(element)) {

            if (iterator.getReference().getReference() == null)
                throw new NoSuchElementException("there is no '" + element + "' in this list");

            iterator = iterator.getReference();

        }
        
        Node<T> temp = iterator.getReference();

        iterator.setReference(temp.getReference());
        temp.setReference(null);

        return temp.getContent();

    }

    @Override
    public long size() {

        if (isEmpty()) {
            return 0;
        }

        if (head == tail) 
            return 1;

        Node<T> iterationNode = head;
        int size = 1;

        while (iterationNode.getReference() != null) {

            iterationNode = iterationNode.getReference();
            size++;

        }

        return size;

    }

    @Override
    public String toString() {

        if (isEmpty()) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder();

        sb.append("[");

        Node<T> iterationNode = head;

        while (iterationNode != null) {

            sb.append((iterationNode == head ? "" : ", ") + iterationNode.getContent());
            iterationNode = iterationNode.getReference();

        }

        sb.append("]");

        return sb.toString();

    }
}
