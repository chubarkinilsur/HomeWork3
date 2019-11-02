import java.util.*;


public class MyHashMap<K,V> implements Map<K,V> {

    public int LENGTH = Integer.MAX_VALUE/1000;
    private int size = 0;
    private Node[] entry = new Node[LENGTH];


    public V put(K key, V value) {

        if (containsKey(key)) throw new IllegalArgumentException();

        Node currentNode = entry[indexFor(key)];
        Node<K, V> node = new Node<K, V>(key, value, currentNode);
        entry[indexFor(key)] = node;
        size++;
        return node.value;
    }

    public void update(Object key, Object value) {
        Node node = (Node) get(key);
        if (node != null)
            node.value = value;
        else throw new NoSuchElementException();
    }




    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size>0;
    }

    public boolean containsKey(Object key) {
        return get(key) != null;
    }

    public boolean containsValue(Object key) {
        return get(key)!=null;
    }



    public V get(Object key) {
        Node node = entry[indexFor(key)];
        while (node != null) {
            if (node.key != key)
                node = node.next;
            else break;
        }
        return (V) node.value;
    }

    public V remove(Object key) {

        if (get(key) == null) throw new NoSuchElementException();

        int hash = indexFor(key);
        Node node = entry[hash];
        if (node == null) return (V) key;
        Node prev_entry = null;
        while (node != null) {
            if (node.key.equals(key)) {
                Object value = entry[hash].value;
                if (prev_entry != null)
                    prev_entry.next = node.next;
                else
                    entry[hash] = null;
                size--;
                return (V) key;
            }
            prev_entry = node;
            node = node.next;
        }
        return (V) key;
    }

    public void putAll(Map m) {

    }

    public void clear() {

    }

    public Set keySet() {
        return null;
    }

    public Collection values() {
        return null;
    }

    public Set<Entry<K, V>> entrySet() {
        return null;
    }

    private int indexFor(Object key) {
        return key == null ? 0 : Math.abs(key.hashCode()) % LENGTH;
    }

    public V getValue(K key) {
        Node node = (Node) get(key);
        if (node == null) throw new NoSuchElementException();

        return (V) node.value;

    }

    static class Node<K,V> {

        private K key;
        private V value;
        private Node next;

        Node(K key, V value, Node node) {
            this.key = key;
            this.value = value;
            this.next = node;
        }
    }
}

