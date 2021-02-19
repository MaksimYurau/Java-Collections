package by.maksim.hashmap;

public class MyHashMapImpl implements MyHashMap {
    private static final int SIZE = 16;

    private Entry[] table = new Entry[SIZE];

    static class Entry {
        final String key;
        String value;
        Entry next;

        Entry(String k, String v) {
            key = k;
            value = v;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getKey() {
            return key;
        }
    }

    public Entry get(String k) {
        int hash = k.hashCode() % SIZE;
        Entry e = table[hash];

        while (e != null) {
            if (e.key.equals(k)) {
                return e;
            }
            e = e.next;
        }
        return null;
    }

    public void put(String k, String v) {
        int hash = k.hashCode() % SIZE;
        Entry e = table[hash];

        if (e != null) {
            if (e.key.equals(k)) {
                e.value = v;
            } else {
                while (e.next != null) {
                    e = e.next;
                }
                e.next = new Entry(k, v);
            }
        } else {
            Entry entryInNewBucket = new Entry(k, v);
            table[hash] = entryInNewBucket;
        }
    }

    public static void main(String[] args) {
        MyHashMapImpl myHashMapImpl = new MyHashMapImpl();

        myHashMapImpl.put("Test", "test");
        myHashMapImpl.put("Test1", "test1");
        myHashMapImpl.put("Test2", "test2");

        Entry test = myHashMapImpl.get("Test");
        System.out.println("" + test.getValue());
        Entry test1 = myHashMapImpl.get("Test1");
        System.out.println("" + test1.getValue());
        Entry test2 = myHashMapImpl.get("Test2");
        System.out.println("" + test2.getValue());
    }
}
