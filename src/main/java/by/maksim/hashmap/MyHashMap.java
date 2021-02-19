package by.maksim.hashmap;

public interface MyHashMap {
    void put(String k, String v);
    MyHashMapImpl.Entry get(String k);
}
