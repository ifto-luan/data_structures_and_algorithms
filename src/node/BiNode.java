package node;

public class BiNode<T> {

    private T content;
    private BiNode<T> left;
    private BiNode<T> right;

    public BiNode(T content) {
        this.content = content;
    }

    public BiNode(T content, BiNode<T> left, BiNode<T> right) {
        this.content = content;
        this.left = left;
        this.right = right;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public BiNode<T> getLeft() {
        return left;
    }

    public void setLeft(BiNode<T> left) {
        this.left = left;
    }

    public BiNode<T> getRight() {
        return right;
    }

    public void setRight(BiNode<T> right) {
        this.right = right;
    }

}
