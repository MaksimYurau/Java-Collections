package by.maksim.arraylist;

import java.util.Iterator;

public class MyArrayListImpl<E> implements MyArrayList<E> {

    public static void main(String[] args) {
        MyArrayListImpl<String> strings = new MyArrayListImpl<>();
        strings.add("one");
        strings.add("two");
        strings.add("three");
        for (String s :
                strings) {
            System.out.println(s);
        }
    }

    private E[] values;

    public MyArrayListImpl() {
        values = (E[]) new Object[0];
    }

    @Override
    public boolean add(E e) {
        try {
            E[] temp = values;
            values = (E[]) new Object[temp.length + 1];
            System.arraycopy(temp, 0, values, 0, temp.length);
            values[values.length - 1] = e;
            return true;
        } catch (ClassCastException exception) {
            exception.printStackTrace();
        }
        return false;
    }

    @Override
    public void delete(int index) {
        try {
            E[] temp = values;
            values = (E[]) new Object[temp.length - 1];
            System.arraycopy(temp, 0, values, 0, temp.length);
            int amountElementAfterIndex = temp.length - index - 1;
            System.arraycopy(temp, index + 1, values, index, amountElementAfterIndex);
        } catch (ClassCastException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public E get(int index) {
        return values[index];
    }

    @Override
    public int size() {
        return values.length;
    }

    @Override
    public void update(int index, E e) {
        values[index] = e;
    }

    @Override
    public Iterator<E> iterator() {
        return new ArrayIterator<>(values);
    }
}
