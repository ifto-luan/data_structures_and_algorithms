package node;

public class Node<T> {
    
    private T content;
    private Node<T> reference;

    public Node(T content) {
        this.content = content;
    }

    public Node(T content, Node<T> reference) {
    
        this.content = content;
        this.reference = reference;

    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public Node<T> getReference() {
        return reference;
    }

    public void setReference(Node<T> reference) {
        this.reference = reference;
    }

    

}
