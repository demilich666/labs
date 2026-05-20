package lab3.example1;

import java.util.LinkedList;

public class HashTable<K, V> {
    
    static class Entry<K, V> {
        private K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }

    private LinkedList<Entry<K, V>>[] table;
    private int capacity = 16; // размер массива
    private int size = 0;

    public HashTable() {
        table = new LinkedList[capacity];
    }
    
    private int hash(K key) {
        return Math.abs(key.hashCode()) % capacity;
    }
    
    public void put(K key, V value) {
        int index = hash(key);

        if (table[index] == null) {
            table[index] = new LinkedList<Entry<K, V>>();
        }

        LinkedList<Entry<K, V>> bucket = table[index];

        for (int i = 0; i < bucket.size(); i++) {
            Entry<K, V> entry = bucket.get(i);
            if (entry.getKey().equals(key)) {
                entry.setValue(value);
                return;
            }
        }

        bucket.add(new Entry<K, V>(key, value));
        size++;
    }
    
    public V get(K key) {
        int index = hash(key);

        LinkedList<Entry<K, V>> bucket = table[index];
        if (bucket == null) {
            return null;
        }

        for (int i = 0; i < bucket.size(); i++) {
            Entry<K, V> entry = bucket.get(i);
            if (entry.getKey().equals(key)) {
                return entry.getValue();
            }
        }

        return null;
    }
    
    public V remove(K key) {
        int index = hash(key);

        LinkedList<Entry<K, V>> bucket = table[index];
        if (bucket == null) {
            return null;
        }

        for (int i = 0; i < bucket.size(); i++) {
            Entry<K, V> entry = bucket.get(i);
            if (entry.getKey().equals(key)) {
                V value = entry.getValue();
                bucket.remove(i);
                size--;
                return value;
            }
        }

        return null;
    }
    
    public int size() {
        return size;
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
}
