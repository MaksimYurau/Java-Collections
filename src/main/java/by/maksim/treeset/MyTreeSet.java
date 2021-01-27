package by.maksim.treeset;

import java.util.Objects;

public class MyTreeSet {

    class Leaf<E> implements Comparable<E> {
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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Leaf<?> leaf = (Leaf<?>) o;
            return Objects.equals(right, leaf.right) &&
                    Objects.equals(left, leaf.left) &&
                    Objects.equals(element, leaf.element);
        }

        @Override
        public String toString() {
            return "Leaf{" +
                    "right=" + right +
                    ", left=" + left +
                    ", element=" + element +
                    '}';
        }
    }
}
