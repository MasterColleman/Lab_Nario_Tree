package structures;

import java.util.*;

public class NAryTree<T> {
    private NTreeNode<T> root;
    private Comparator<T> comparator;

    public NAryTree(T valueRoot, Comparator<T> comparator) {
        this.root = new NTreeNode<>(valueRoot);
        this.comparator = comparator;
    }

    public boolean add(T parent, T son) {
        NTreeNode<T> parentNTreeNode = search(parent);
        if (parentNTreeNode == null) return false;
        for (NTreeNode<T> child : parentNTreeNode.getChildren()) {
            if (comparator.compare(child.getData(), son) == 0) {
                return false;
            }
        }
        return parentNTreeNode.add(new NTreeNode<>(son));
    }

    public boolean remove(T parent, T son) {
        NTreeNode<T> parentNTreeNode = search(parent);
        if (parentNTreeNode == null) return false;
        List<NTreeNode<T>> children = parentNTreeNode.getChildren();
        for (int i = 0; i < children.size(); i++) {
            if (comparator.compare(children.get(i).getData(), son) == 0) {
                children.remove(i);
                return true;
            }
        }
        return false;
    }

    public NTreeNode<T> search(T value) {
        if (root == null) return null;
        if (comparator.compare(root.getData(), value) == 0) return root;
        Queue<NTreeNode<T>> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            NTreeNode<T> current = queue.poll();
            if (comparator.compare(current.getData(), value) == 0) {
                return current;
            }
            queue.addAll(current.getChildren());
        }
        return null;
    }

    public Iterator<T> iterator() {
        return new NAryTreeIterator<>(root);
    }

}