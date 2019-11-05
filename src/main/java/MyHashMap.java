import java.util.*;

/**
 * MyHashMap домашнее задание №3 с применением родовых типов и реализацией интерфеса Map
 * @param <K>
 * @param <V>
 */
public class MyHashMap<K, V> implements Map<K, V> {
    /**
     * колличество корзин по умолчанию 16
     */
    private static int DEFAULT_INITIAL_CAPACITY = 16;
    /**
     * коофициен загрузки таблицы по умолчанию
     */
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private final float loadFactor;
    private int size = 0;
    private Node[] node;

    /**
     * Конструктор таблицы со значениями по умолчанию
     */
    public MyHashMap() {
        this(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    /**
     * Конструктор позволяющий внести собственные значения initialCapacity и loadFactor
     * @param initialCapacity
     * @param loadFactor
     */
    public MyHashMap(int initialCapacity, float loadFactor) {
        this.loadFactor = loadFactor;
        this.node = new Node[initialCapacity];
    }

    /**
     * Метод добавляет пару ключ key = значение value в таблицу, при внесении
     * в таблицу записи с существующим ключем key старое значение затирается
     * @param key может быть null
     * @param value может быть null
     * @return
     */
    @Override
    public V put(K key, V value) {

        if (containsKey(key)) return update(key, value);

        Node<K, V> currentNode = node[indexFor(key)];
        Node<K, V> node = new Node<K, V>(key, value, currentNode);
        this.node[indexFor(key)] = node;
        size++;
        if (size * loadFactor > this.node.length) {
            transfer();
        }
        return node.value;
    }

    private V update(K key, V value) {
        Node node = getNode(key);
        V result = (V) node.value;
        node.value = value;
        return result;
    }

    /**
     *  @return количество записей в таблице.
     */

    @Override
    public int size() {
        return size;
    }

    /**
     * @return есть ли хоть одна запись в таблице
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     *
     * @param key
     * @return true если в таблице имеется запись с ключем равным key, в противном случае возвращает false.
     */
    @Override
    public boolean containsKey(Object key) {
        return getNode(key) != null;
    }

    /**
     *
     * @param value
     * @return true если в таблице имеется запись со значением равным value, в противном случае возвращает false.
     */
    @Override
    public boolean containsValue(Object value) {
        for (int i = 0; i < node.length; i++) {
            Node node = this.node[i];
            while (node != null) {
                if (node.value.equals(value))
                    return true;
                node = node.next;
            }
        }
        return false;
    }


    /**
     *
     * @param key
     * @return возвращает значение заиси с ключем key. Может вернуть null.
     */
    @Override
    public V get(Object key) {
        return getNode(key) == null ? null : (V) getNode(key).value;
    }

    private Node getNode(Object key) {
        Node node = this.node[indexFor(key)];
        while (node != null) {
            if (!node.key.equals(key))
                node = node.next;
            else break;
        }
        return node;
    }

    /**
     * Удаляет запись со значением ключа равным key. При отсутствии подобной записи
     * выбрасывааю исключение NoSuchElementException
     * @param key
     * @return  возвращает значение записи по ключу key
     */
    @Override
    public V remove(Object key) {

        if (get(key) == null) throw new NoSuchElementException();

        int hash = indexFor(key);
        Node node = this.node[hash];

        Node prev_node = null;
        while (node != null) {
            if (node.key.equals(key)) {
                Object value = this.node[hash].value;
                if (prev_node != null)
                    prev_node.next = node.next;
                else
                    this.node[hash] = null;
                size--;
                return (V) value;
            }
            prev_node = node;
            node = node.next;
        }
        return null;
    }

    private void transfer() {
        Node[] newEntry = new Node[node.length * 2];
        for (int i = 0; i < node.length; i++) {
            Node<K, V> node = this.node[i];
            while (node != null) {
                K key = node.key;
                V value = node.value;
                int newIndex = key == null ? 0 : Math.abs(key.hashCode()) % newEntry.length;
                Node<K, V> newNode = newEntry[newIndex];
                newEntry[newIndex] = new Node<K, V>(key, value, newNode);
                node = node.next;

            }

        }
    }

    /**
     * Дабавляет записи таблицы map в существующую таблицу, при совпадение ключей,
     * записываюся значения из таблицы map/
     * @param map
     */
    public void putAll(Map<? extends K, ? extends V> map) {

        for (Entry<? extends K, ? extends V> entry : map.entrySet()) {
            this.put(entry.getKey(), entry.getValue());
        }
    }

    /**
     * Очищает таблицу. Количество количество корзин остается прежним.
     */
    @Override
    public void clear() {
        node = new Node[node.length];
    }

    /**
     * @return возвращает коллекцию ключей хранящихся в таблице
     */
    @Override
    public Set<K> keySet() {
        Set<K> keySet = new HashSet<K>();
        for (int i = 0; i < node.length; i++) {
            Node node = this.node[i];
            while (node != null) {
                keySet.add((K) node.key);
                node = node.next;
            }
        }
        return keySet;
    }

    /**
     * @return возвращает коллекцию значений хранящихся в таблице
     */
    @Override
    public Collection values() {
        Collection<V> list = new ArrayList<V>();
        for (int i = 0; i < node.length; i++) {
            Node node = this.node[i];
            while (node != null) {
                list.add((V) node.value);
                node = node.next;
            }
        }
        return list;
    }

    /**
     * @return возвращает коллекцию пар ключ=значение хранящихся в таблице.
     */
    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        Set entrySet = new HashSet();
        for (int i = 0; i < node.length; i++) {
            Node node = this.node[i];
            while (node != null) {
                entrySet.add(node);
                node = node.next;
            }
        }
        return entrySet;
    }

    private int indexFor(Object key) {
        return key == null ? 0 : Math.abs(key.hashCode()) % node.length;
    }

    class Node<K, V> {

        private K key;
        private V value;
        private Node next;

        Node(K key, V value, Node<K, V> node) {

            this.key = key;
            this.value = value;
            this.next = node;
        }

        @Override
        public String toString() {
            return key+"="+value;
        }
    }
}

