package structures;
public class NTreeNode<T> {
    private T data;
    private MyList<NTreeNode<T>> children;

    public NTreeNode(T data) {
        this.data = data;
        this.children = new MyList<>();
    }

    public boolean add(NTreeNode<T> son) {
        return children.add(son);
    }

    public T getData() {
        return data;
    }

    public MyList<NTreeNode<T>> getChildren() {
        return children;
    }
}

