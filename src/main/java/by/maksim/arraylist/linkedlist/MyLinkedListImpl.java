package by.maksim.arraylist.linkedlist;

import java.util.Iterator;

public class MyLinkedListImpl<E> implements MyLinkedList<E>, Iterable<E>, DescendingIterator<E> {

    public static void main(String[] args) {
        MyLinkedListImpl<String> myLinkedListImpl = new MyLinkedListImpl<>();
        myLinkedListImpl.addFirst("a");
        myLinkedListImpl.addFirst("ab");
        myLinkedListImpl.addFirst("c");
        myLinkedListImpl.addFirst("abc");
        System.out.println(myLinkedListImpl.size());
        System.out.println(myLinkedListImpl.getElementByIndex(1));

        Iterator iterator = myLinkedListImpl.descendingIterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    @Override
    public Iterator<E> descendingIterator() {
        return new Iterator<E>() {
            int counter = size - 1;

            @Override
            public boolean hasNext() {
                return counter >= 0;
            }

            @Override
            public E next() {
                return getElementByIndex(counter--);
            }
        };
    }

    @Override
    public void addFirst(E e) {
        Node<E> next = firstNode;
        next.setCurrentElement(e);
        firstNode = new Node<>(null, next, null);
        next.setNextElement(next);
        size++;
    }

    @Override
    public void addLast(E e) {
        Node<E> prev = lastNode;
        prev.setCurrentElement(e);
        lastNode = new Node<>(null, prev, null);
        prev.setNextElement(prev);
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E getElementByIndex(int counter) {
        Node<E> target = firstNode.getNextElement();
        for (int i = 0; i < counter; i++) {
            target = getNextElement(target);
        }
        return target.getCurrentElement();
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int counter = 0;

            @Override
            public boolean hasNext() {
                return counter < size;
            }

            @Override
            public E next() {
                return getElementByIndex(counter++);
            }
        };
    }

    private Node<E> firstNode;
    private Node<E> lastNode;
    private int size = 0;

    private Node<E> getNextElement(Node<E> current) {
        return current.getNextElement();
    }

    private static class Node<E> {
        private E currentElement;
        private Node<E> nextElement;
        private Node<E> prevElement;

        public Node(E currentElement, Node<E> nextElement, Node<E> prevElement) {
            this.currentElement = currentElement;
            this.nextElement = nextElement;
            this.prevElement = prevElement;
        }

        public E getCurrentElement() {
            return currentElement;
        }

        public void setCurrentElement(E currentElement) {
            this.currentElement = currentElement;
        }

        public Node<E> getNextElement() {
            return nextElement;
        }

        public void setNextElement(Node<E> nextElement) {
            this.nextElement = nextElement;
        }

        public Node<E> getPrevElement() {
            return prevElement;
        }

        public void setPrevElement(Node<E> prevElement) {
            this.prevElement = prevElement;
        }
    }

    public MyLinkedListImpl() {
        lastNode = new Node<>(null, firstNode, null);
        firstNode = new Node<>(null, null, lastNode);
    }
}
