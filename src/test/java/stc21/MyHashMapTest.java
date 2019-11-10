package stc21;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeMap;


class MyHashMapTest {
    private MyHashMap<Integer, String> myMap = new MyHashMap<Integer, String>();
    private HashMap<Integer, String> map = new HashMap<Integer, String>();

    @BeforeEach
    void setUp() {
        for (int i = 0; i < 100; i++) {
            myMap.put(i, "val" + i);
            map.put(i, "val" + i);
        }
        myMap.put(1, "val11");
        map.put(1, "val11");
    }

    @Test
    void put() {
        for (int i = 0; i < 100; i++) {
            assertEquals(map.containsValue("val" + i), myMap.containsValue("val" + i));
        }
        assertEquals(map.containsValue("val11"), myMap.containsValue("val11"));
        assertEquals(map.containsValue("val22"), myMap.containsValue("val22"));
     }

    @Test
    void size() {
        assertEquals(map.size(), myMap.size());
    }

    @Test
    void isEmpty() {
        assertEquals(map.isEmpty(), myMap.isEmpty());
    }

    @Test
    void containsKey() {
        for (int i = 0; i < 100; i++) {
            assertEquals(map.containsKey(i), myMap.containsKey(i));
        }

    }

    @Test
    void containsValue() {
        for (int i = 0; i < 100; i++) {
            assertEquals(map.containsValue("val" + i), myMap.containsValue("val" + i));
        }
    }

    @Test
    void get() {
        for (int i = 0; i < 100; i++) {
            assertEquals(map.get(i), myMap.get(i));
        }
    }

    @Test
    void remove() {
        map.remove(1);
        myMap.remove(1);
        assertEquals(map.size(), myMap.size());
        assertEquals(map.get(1), myMap.get(1));
        assertEquals(map.containsValue("val1"), myMap.containsValue("val1"));
        assertEquals(map.containsKey(1), myMap.containsKey(1));

    }

    @Test
    void putAll() {
        TreeMap<Integer, String> treeMap = new TreeMap<Integer, String>();
        for (int i = 50; i < 150; i++) {
            treeMap.put(i,"val"+i);
        }
        map.putAll(treeMap);
        myMap.putAll(treeMap);
        assertEquals(map.size(),myMap.size());
        for (Integer key: map.keySet()) {
            assertTrue(myMap.containsKey(key));
        }
    }

    @Test
    void clear() {
        map.clear();
        myMap.clear();
        assertEquals(map.entrySet().size(), myMap.entrySet().size());
        assertEquals(map.size(),myMap.size());
    }

    @Test
    void keySet() {
        Set<Integer> mapKeySet = map.keySet();
        Set<Integer> myMapKeySet = myMap.keySet();
        for (Integer key : mapKeySet) {
            assertTrue(myMapKeySet.contains(key));
        }
        assertEquals(mapKeySet.size(), myMapKeySet.size());

    }

    @Test
    void values() {
        Collection<String> mapValues = map.values();
        Collection<String> myMapValues = myMap.values();
        for (String value : mapValues) {
            assertTrue(myMap.containsValue(value));
        }
        assertEquals(mapValues.size(), myMapValues.size());
    }

    @Test
    void entrySet() {

        final Set<java.util.Map.Entry<Integer, String>> mapEntry = map.entrySet();
        Set<java.util.Map.Entry<Integer, String>> myMapEntry = myMap.entrySet();
        assertEquals(mapEntry.size(), myMapEntry.size());
    }
}