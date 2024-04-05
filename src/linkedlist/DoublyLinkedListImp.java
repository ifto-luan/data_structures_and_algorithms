package linkedlist;

import java.util.NoSuchElementException;

import node.BiNode;

public class DoublyLinkedListImp<T> implements IList<T> {

    private BiNode<T> head;
    private BiNode<T> tail;

    public DoublyLinkedListImp() {

    }

    public void add(int index, T element) throws ArrayIndexOutOfBoundsException {

        if (index < 0 || index >= size())
            throw new ArrayIndexOutOfBoundsException();

        if (index == 0) {

            addFirst(element);

        } else if (index == size() - 1) {

            addLast(element);

        } else {

            BiNode<T> newNode = new BiNode<>(element);
            BiNode<T> leftNode = getNode(index - 1);
            BiNode<T> indexNode = getNode(index);

            leftNode.setRight(newNode);
            newNode.setRight(indexNode);

            indexNode.setLeft(newNode);
            newNode.setLeft(leftNode);

        }

    }

    @Override
    public void add(T element) {
        addLast(element);
        
    }

    private void addFirst(T element) {

        if (isEmpty()) {

            BiNode<T> newNode = new BiNode<>(element);
            head = newNode;
            tail = head;

        }

        else {

            BiNode<T> newNode = new BiNode<>(element);
            head.setLeft(newNode);
            newNode.setRight(head);
            head = newNode;

        }

    }

    private void addLast(T element) {

        if (isEmpty()) {

            BiNode<T> newNode = new BiNode<>(element);
            head = newNode;
            tail = head;

        } else {

            BiNode<T> newNode = new BiNode<>(element);
            tail.setRight(newNode);
            newNode.setLeft(tail);
            tail = newNode;

        }

    }

    @Override
    public T get(int index) {
        return getNode(index).getContent();
    }

    private BiNode<T> getNode(int index) {

        if (index < 0 || index >= size())
            throw new ArrayIndexOutOfBoundsException();

        if (index == 0) {

            return head;

        } else if (index == size() - 1) {

            return tail;

        }

        BiNode<T> node = head;
        int searchIndex = 0;

        while (searchIndex != index) {

            node = node.getRight();
            searchIndex++;

        }

        return node;

    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    public T remove(int index) {

        if (index < 0 || index >= size())
            throw new ArrayIndexOutOfBoundsException();

        if (isEmpty())
            throw new NoSuchElementException();

        if (size() == 1 && index == 0) {

            T content = head.getContent();
            head = null;
            tail = null;
            return content;

        } else if (index == 1)
            return removeFirst();

        else if (index == size() - 1)
            return removeLast();

        else {

            BiNode<T> indexNode = getNode(index);
            BiNode<T> leftNode = getNode(index - 1);
            BiNode<T> rightNode = getNode(index);

            leftNode.setRight(rightNode);
            rightNode.setRight(leftNode);

            return indexNode.getContent();

        }

    }

    @Override
    public T remove(T element) {

        int index = searchElement(element);

        return index == -1 ? null : remove(index);

    }

    private int searchElement(T element) {

        BiNode<T> iterationBiNode = head;
        int searchIndex = 0;
        boolean found = false;

        while (iterationBiNode != null) {

            if (iterationBiNode.getContent().equals(element)) {
                found = true;
                break;
            }

            iterationBiNode = iterationBiNode.getRight();
            searchIndex++;

        }

        return found ? -1 : searchIndex;

    }

    private T removeFirst() {

        if (isEmpty())
            throw new NoSuchElementException();

        if (size() == 1)
            return remove(0);

        T content = head.getContent();
        BiNode<T> headRight = head.getRight();
        headRight.setLeft(null);
        head.setRight(null);
        head = headRight;
        return content;

    }

    private T removeLast() {

        if (isEmpty())
            throw new NoSuchElementException();

        if (size() == 1)
            return remove(0);

        T content = tail.getContent();
        BiNode<T> tailLeft = tail.getLeft();
        tailLeft.setRight(null);
        tail.setLeft(null);
        tail = tailLeft;

        return content;

    }

    @Override
    public long size() {

        if (isEmpty()) {
            return 0;
        }

        BiNode<T> iterationNode = head;
        int size = 1;

        while (iterationNode.getRight() != null) {

            iterationNode = iterationNode.getRight();
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

        BiNode<T> iterationNode = head;

        while (iterationNode != null) {

            sb.append((iterationNode == head ? "" : ", ") + iterationNode.getContent());
            iterationNode = iterationNode.getRight();

        }

        sb.append("]");

        return sb.toString();

    }

}
