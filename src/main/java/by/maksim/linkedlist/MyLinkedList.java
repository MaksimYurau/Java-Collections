package by.maksim.linkedlist;

public interface MyLinkedList<E> {
    void addFirst(E e);
    void addLast(E e);
    int size();
    E getElementByIndex(int counter);
}
