package by.maksim.hashmap;

public interface MyHashMap<K, V> {
    void put(K key, V value);
    V get(K key);
    boolean containsKey(K key);
    int size();
    void delete(K key);
}
