package by.maksim.hashmap;

import java.util.Objects;

public class MyHashMapImpl<K, V> implements MyHashMap<K, V> {
    private int capacity = 10;
    private MyHashMapBucket[] bucket;
    private int size = 0;

    public MyHashMapImpl() {
        this.bucket = new MyHashMapBucket[capacity];
    }
    private int getHash(K key) {
        return (key.hashCode() & 0xfffffff) % capacity;
    }

    private MyKeyValueEntry<K, V> getEntry(K key) {
        int hash = getHash(key);
        for (int i = 0; i < bucket[hash].getEntries().size(); i++) {
            MyKeyValueEntry<K, V> myKeyValueEntry = bucket[hash].getEntries().get(i);
            if(myKeyValueEntry.getKey().equals(key)) {
                return myKeyValueEntry;
            }
        }
        return null;
    }

    @Override
    public void put(K key, V value) {
        if(containsKey(key)) {
            MyKeyValueEntry<K, V> entry = getEntry(key);
            entry.setValue(value);
        } else {
            int hash = getHash(key);
            if(bucket[hash] == null) {
                bucket[hash] = new MyHashMapBucket();
            }
            bucket[hash].addEntry(new MyKeyValueEntry<>(key, value));
            size++;
        }
    }

    @Override
    public V get(K key) {
        return containsKey(key) ? (V) getEntry(key).getValue() : null;
    }

    @Override
    public boolean containsKey(K key) {
        int hash = getHash(key);
        return !(Objects.isNull(bucket[hash]) || Objects.isNull(getEntry(key)));
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void delete(K key) {
        if(containsKey(key)) {
            int hash = getHash(key);
            bucket[hash].removeEntry(getEntry(key));
            size--;
        }
    }
}
