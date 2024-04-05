package queue;

import node.Node;

class DynamicQueue<T> implements Queue<T> {

    private Node<T> head;
    private Node<T> tail;

    public DynamicQueue() {

    }

    public void add(T element) {

        if (this.head == null && this.tail == null) {

            Node<T> newNode = new Node<>(element);
            this.head = newNode;
            this.tail = newNode;

        } else if ((this.head != null) && (this.head == this.tail)) {

            Node<T> newNode = new Node<>(element);
            this.head.setReference(newNode);
            this.tail = newNode;

        } else {

            Node<T> newNode = new Node<>(element);
            this.tail.setReference(newNode);
            this.tail = newNode;

        }

    }

    public T pop() throws QueueException {

        if (isEmpty()) {
            throw new QueueException("Queue is empty. Cannot perform pop");
        }

        T element = this.head.getContent();
        this.head = this.head.getReference();
        return element;

    }

    public T head() throws QueueException {

        if (isEmpty()) {
            throw new QueueException("Queue is empty. Cannot see the head");
        }

        return this.head.getContent();

    }

    public T tail() throws QueueException {

        if (isEmpty()) {
            throw new QueueException("Queue is empty. Cannot see the tail");
        }

        return this.tail.getContent();

    }

    public boolean isEmpty() {
        return this.head == null && this.tail == null;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append("[");
        sb.append(head.getContent() + " (head) -> ");

        Node<T> iterator = this.head.getReference();

        while (iterator != null && iterator != this.tail) {

            sb.append(iterator.getContent() + " -> ");
            iterator = iterator.getReference();

        }

        sb.append(this.tail.getContent() + " (tail)]");

        return sb.toString();

    }

    @Override
    public int size() {

        if (isEmpty()) {
            return 0;
        }

        int size = 1;

        Node<T> checkReference = head;

        while (checkReference.getReference() != null) {

            size++;
            checkReference = checkReference.getReference();

        }

        return size;

    }

}