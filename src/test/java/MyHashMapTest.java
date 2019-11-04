import org.junit.jupiter.api.BeforeEach;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

class MyHashMapTest {
    MyHashMap<Integer, String> myMap = new MyHashMap<Integer, String>();
    HashMap<Integer, String> map = new HashMap<Integer, String>();

    @BeforeEach
    void setUp() {
        for (int i = 0; i < 100; i++) {
            myMap.put(i, "val" + i);
            map.put(i, "val" + i);
        }
        myMap.put(1, "val11");
        myMap.put(2, "val22");
        myMap.put(3, "val33");
        myMap.put(4, "val44");
        myMap.put(5, "val55");

        map.put(1, "val11");
        map.put(2, "val22");
        map.put(3, "val33");
        map.put(4, "val44");
        map.put(5, "val55");

    }

    @org.junit.jupiter.api.Test
    void put() {
        for (int i = 0; i < 100; i++) {
            assertEquals(map.containsValue("val" + i), myMap.containsValue("val" + i));
        }
        assertEquals(map.containsValue("val11"), myMap.containsValue("val11"));
        assertEquals(map.containsValue("val22"), myMap.containsValue("val22"));
        assertEquals(map.containsValue("val33"), myMap.containsValue("val33"));
        assertEquals(map.containsValue("val44"), myMap.containsValue("val44"));
        assertEquals(map.containsValue("val55"), myMap.containsValue("val55"));

    }

    @org.junit.jupiter.api.Test
    void size() {
        assertEquals(map.size(), myMap.size());
    }

    @org.junit.jupiter.api.Test
    void isEmpty() {
        assertEquals(map.isEmpty(), myMap.isEmpty());
    }

    @org.junit.jupiter.api.Test
    void containsKey() {
        for (int i = 0; i < 105; i++) {
            assertEquals(map.containsKey(i), myMap.containsKey(i));
        }

    }

    @org.junit.jupiter.api.Test
    void containsValue() {
        for (int i = 0; i < 105; i++) {
            assertEquals(map.containsValue("val" + i), myMap.containsValue("val" + i));
        }
    }

    @org.junit.jupiter.api.Test
    void get() {
        for (int i = 0; i < 105; i++) {
            assertEquals(map.get(i), myMap.get(i));
        }
    }

    @org.junit.jupiter.api.Test
    void remove() {
        map.remove(1);
        myMap.remove(1);
        assertEquals(map.size(), myMap.size());
        assertEquals(map.get(1), myMap.get(1));
        assertEquals(map.containsValue("val1"), myMap.containsValue("val1"));
        assertEquals(map.containsKey(1), myMap.containsKey(1));

    }

    @org.junit.jupiter.api.Test
    void putAll() {
        TreeMap treeMap = new TreeMap();
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

    @org.junit.jupiter.api.Test
    void clear() {
        map.clear();
        myMap.clear();
        assertEquals(map.entrySet().size(), myMap.entrySet().size());
    }

    @org.junit.jupiter.api.Test
    void keySet() {
        Set<Integer> mapKeySet = map.keySet();
        Set<Integer> myMapKeySet = myMap.keySet();
        for (Integer key : mapKeySet) {
            assertTrue(myMapKeySet.contains(key));
        }
        assertEquals(mapKeySet.size(), myMapKeySet.size());

    }

    @org.junit.jupiter.api.Test
    void values() {
        Collection<String> mapValues = map.values();
        Collection myMapValues = myMap.values();
        for (String value : mapValues) {
            assertTrue(myMap.containsValue(value));
        }
        assertEquals(mapValues.size(), myMapValues.size());
    }

    @org.junit.jupiter.api.Test
    void entrySet() {

        Set mapEntry = map.entrySet();
        Set myMapEntry = myMap.entrySet();
        assertEquals(mapEntry.size(), myMapEntry.size());
    }
}