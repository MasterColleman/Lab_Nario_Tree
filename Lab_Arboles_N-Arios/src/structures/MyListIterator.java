package structures;

import java.util.ListIterator;
import java.util.NoSuchElementException;

public class MyListIterator<T> implements ListIterator<T> {
    private Node<T> current;
    private Node<T> lastReturned;
    private final MyList<T> list;
    private int index;

    public MyListIterator(MyList<T> list, int startIndex) {
        if (startIndex < 0 || startIndex > list.size()) {
            throw new IndexOutOfBoundsException();
        }
        this.list = list;
        this.current = getNodeAt(list, startIndex);
        this.index = startIndex;
        this.lastReturned = null;
    }

    @Override
    public boolean hasNext() {
        return current != null;
    }

    @Override
    public T next() {
        if (!hasNext()) throw new NoSuchElementException();
        lastReturned = current;
        T data = current.getData();
        current = current.getNext();
        index++;
        return data;
    }

    @Override
    public boolean hasPrevious() {
        return index > 0;
    }

    @Override
    public T previous() {
        if (!hasPrevious()) throw new NoSuchElementException();
        current = (current == null) ? getNodeAt(list, list.size() - 1) : getPreviousNode(list, current);
        lastReturned = current;
        index--;
        return current.getData();
    }

    @Override
    public int nextIndex() {
        return index;
    }

    @Override
    public int previousIndex() {
        return index - 1;
    }

    @Override
    public void remove() {
        if (lastReturned == null) throw new IllegalStateException();
        list.remove(lastReturned.getData());
        lastReturned = null;
    }

    @Override
    public void set(T t) {
        if (lastReturned == null) throw new IllegalStateException();
        lastReturned.setData(t);
    }

    @Override
    public void add(T t) {
        if (t == null) throw new NullPointerException("Cannot add null elements");
        Node<T> newNode = new Node<>(t);
        if (list.isEmpty()) {
            list.setHead(newNode);
        } else if (current == null) {
            Node<T> last = list.getHead();
            while (last.getNext() != null) {
                last = last.getNext();
            }
            last.setNext(newNode);
        } else if (current == list.getHead()) {
            newNode.setNext(list.getHead());
            list.setHead(newNode);
        } else {
            Node<T> prev = list.getHead();
            while (prev.getNext() != current) {
                prev = prev.getNext();
            }
            prev.setNext(newNode);
            newNode.setNext(current);
        }
        index++;
        lastReturned = null;
    }

    private Node<T> getNodeAt(MyList<T> list, int position) {
        Node<T> temp = list.getHead();
        for (int i = 0; i < position; i++) {
            temp = temp.getNext();
        }
        return temp;
    }

    private Node<T> getPreviousNode(MyList<T> list, Node<T> node) {
        Node<T> temp = list.getHead();
        while (temp != null && temp.getNext() != node) {
            temp = temp.getNext();
        }
        return temp;
    }
}
