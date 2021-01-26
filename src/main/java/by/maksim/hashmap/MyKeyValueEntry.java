package by.maksim.hashmap;

import java.util.Objects;

public class MyKeyValueEntry<K, V> {
    private K key;
    private V value;

    public MyKeyValueEntry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyKeyValueEntry<?, ?> that = (MyKeyValueEntry<?, ?>) o;
        return Objects.equals(key, that.key) &&
                Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value);
    }

    @Override
    public String toString() {
        return "MyKeyValueEntry{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }
}
