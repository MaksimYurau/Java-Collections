package by.maksim.treeset;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class MyTreeSet<E> implements MyTree<E> {
    private Leaf<E> root;
    private List<E> list;
    private int size = 0;

    public MyTreeSet(Leaf<E> root, List<E> list) {
        this.root = root;
        this.list = list;
    }

    @Override
    public boolean add(E e) {
        if (size == 0) {
            return initRootLeaf(e);
        }

        Leaf<E> newNode = new Leaf<>(e);
        Leaf<E> lastNode = new Leaf<>(e);

        if (lastNode == null) {
             return false;
        }
        size++;
        newNode.parent = lastNode;

        if (lastNode.compareTo(newNode) < 0) {
            lastNode.right = newNode;
            return true;
        } else {
            lastNode.left = newNode;
            return false;
        }
    }

    private Leaf<E> search(Leaf<E> leaf, Leaf<E> eLeaf) {
        int compare = leaf.compareTo(eLeaf);

        if (compare < 0 && leaf.right != null) {
            return search(leaf.right, eLeaf);
        }

        if (compare > 0 && leaf.left != null) {
            return search(leaf.left, eLeaf);
        }

        if (compare == 0) {
            return leaf;
        }
        return null;
    }

    private boolean initRootLeaf(final E e) {
        root.element = e;
        size++;
        return true;
    }

    @Override
    public List<E> get() {
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Leaf<E> find(E e) {
        return null;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int count = 0;

            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public E next() {
                count++;
                return null;
            }
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyTreeSet<?> myTreeSet = (MyTreeSet<?>) o;
        return size == myTreeSet.size &&
                Objects.equals(root, myTreeSet.root) &&
                Objects.equals(list, myTreeSet.list);
    }

    @Override
    public String toString() {
        return "MyTreeSet{" +
                "root=" + root +
                ", list=" + list +
                ", size=" + size +
                '}';
    }

    static class Leaf<E> implements Comparable<E> {
        private Leaf<E> parent;
        private Leaf<E> right;
        private Leaf<E> left;
        private E element;

        public Leaf(E element) {
            this.element = element;
        }

        @Override
        public int compareTo(Object obj) {
            Leaf<E> node = (Leaf<E>) obj;
            return this.hashCode() - node.hashCode();
        }

        @Override
        public int hashCode() {
            int hash = 31;
            hash = hash * 17 + element.hashCode();
            return hash;
        }

        public Leaf<E> getParent() {
            return parent;
        }

        public void setParent(Leaf<E> parent) {
            this.parent = parent;
        }

        public Leaf<E> getRight() {
            return right;
        }

        public void setRight(Leaf<E> right) {
            this.right = right;
        }

        public Leaf<E> getLeft() {
            return left;
        }

        public void setLeft(Leaf<E> left) {
            this.left = left;
        }

        public E getElement() {
            return element;
        }

        public void setElement(E element) {
            this.element = element;
        }
    }
}
