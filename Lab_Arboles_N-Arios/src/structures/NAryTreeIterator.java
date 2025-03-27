package structures;
import java.util.*;

public class NAryTreeIterator<T> implements Iterator<T> {
    private final Deque<NTreeNode<T>> stack = new ArrayDeque<>();
    private final Map<NTreeNode<T>, NTreeNode<T>> parentMap = new HashMap<>();
    private NTreeNode<T> lastReturned = null;

    public NAryTreeIterator(NTreeNode<T> root) {
        if (root != null) {
            stack.push(root);
            parentMap.put(root, null);
        }
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    @Override
    public T next() {
        if (!hasNext()) throw new NoSuchElementException();
        lastReturned = stack.pop();
        List<NTreeNode<T>> children = lastReturned.getChildren();
        for (int i = children.size() - 1; i >= 0; i--) {
            NTreeNode<T> child = children.get(i);
            stack.push(child);
            parentMap.put(child, lastReturned);
        }
        return lastReturned.getData();
    }

    @Override
    public void remove() {
        if (lastReturned == null) {
            throw new IllegalStateException("next() must be called before remove()");
        }
        NTreeNode<T> parent = parentMap.get(lastReturned);
        if (parent == null) {
            throw new UnsupportedOperationException("Cannot remove the root node");
        }
        parent.getChildren().remove(lastReturned);
        parentMap.remove(lastReturned);
        lastReturned = null;
    }
}
