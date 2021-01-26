package by.maksim.hashmap;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class MyHashMapBucket {
    private List<MyKeyValueEntry> entries;

    public MyHashMapBucket() {
        if(entries == null) {
            entries = new LinkedList<>();
        }
    }

    public List<MyKeyValueEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<MyKeyValueEntry> entries) {
        this.entries = entries;
    }

    public void addEntry(MyKeyValueEntry entry) {
        this.entries.add(entry);
    }

    public void removeEntry(MyKeyValueEntry entry) {
        this.entries.remove(entry);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyHashMapBucket that = (MyHashMapBucket) o;
        return Objects.equals(entries, that.entries);
    }

    @Override
    public int hashCode() {
        return Objects.hash(entries);
    }

    @Override
    public String toString() {
        return "MyHashMapBucket{" +
                "entries=" + entries +
                '}';
    }
}
