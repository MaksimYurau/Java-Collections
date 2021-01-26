package by.maksim.arraylist.linkedlist;

public interface MyLinkedList<E> {
    void addFirst(E e);
    void addLast(E e);
    int size();
    E getElementByIndex(int counter);
}
