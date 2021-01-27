package by.maksim.treeset;

import java.util.List;

public interface MyTree<E> extends Iterable<E> {
    boolean add(E e);
    List<E> get();
    int size();
    MyTreeSet.Leaf<E> find(E e);
}
