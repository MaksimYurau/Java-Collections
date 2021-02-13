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

    public MyTreeSet() {

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

    private Leaf<E> findLastLeaf(Leaf<E> oldLeaf, Leaf<E> newLeaf) {

        Leaf<E> lastLeaf = oldLeaf;
        int compare = oldLeaf.compareTo(newLeaf);

        if (compare < 0 && oldLeaf.right != null) {
            lastLeaf = findLastLeaf(oldLeaf.right, newLeaf);
            return lastLeaf;
        }

        if (compare > 0 && oldLeaf.left != null) {
            lastLeaf = findLastLeaf(oldLeaf.left, newLeaf);
            return lastLeaf;
        }

        if (compare == 0) {
            return null;
        }
        return lastLeaf;
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
        for (E e: this) {
            list.add(e);
        }
        return list;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Leaf<E> find(E e) {
        Leaf<E> eLeaf = new Leaf<>(e);
        return search(root, eLeaf);
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int count = 0;
            Iterator<Leaf<E>> iterator = new TreeIterator<>(root);

            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public E next() {
                count++;
                return iterator.next().element;
            }
        };
    }

    private static class TreeIterator<E> implements Iterator<Leaf<E>> {
        private Leaf<E> next;

        public TreeIterator(Leaf<E> root) {
            next = root;
            goToLeftMost();
        }

        private void goToLeftMost() {
            while (next.left != null) {
                next = next.left;
            }
        }

        @Override
        public boolean hasNext() {
            return next != null && next.element != null;
        }

        @Override
        public Leaf<E> next() {
            Leaf<E> r = next;

            if (next.right != null)
                return goRight(r);
            return goUp(r);
        }

        private Leaf<E> goRight(Leaf<E> r) {
            next = next.right;
            while (next.left != null) {
                next = next.left;
            }
            return r;
        }

        private Leaf<E> goUp(Leaf<E> r) {
            while (true) {
                if (next.parent == null) {
                    next = null;
                    return r;
                }

                if (next.parent == next) {
                    next = next.parent;
                    return r;
                }
                next = next.parent;
            }
        }
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

    public static void main(String[] args) {
        MyTree<Integer> tree = new MyTreeSet<>();
        tree.add(1);
        tree.add(13);
        tree.add(-21);
        tree.add(6);

        for (Integer i: tree.get()) {
            System.out.println(i);
        }
    }
}
