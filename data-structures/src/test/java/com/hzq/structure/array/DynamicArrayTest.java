package com.hzq.structure.array;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;


class DynamicArrayTest {

    private DynamicArray<Integer> emptyArray;
    private DynamicArray<String> stringArray;
    private DynamicArray<Integer> populatedArray;

    @BeforeEach
    public void setUp() {
        // 初始化测试对象
        emptyArray = new DynamicArray<>();
        stringArray = new DynamicArray<>(5);
        populatedArray = new DynamicArray<>(new Integer[]{1, 2, 3, 4, 5});
    }

    @Test
    public void testConstructors() {
        // 测试默认构造函数
        assertEquals(0, emptyArray.size());
        assertTrue(emptyArray.isEmpty());

        // 测试带容量的构造函数
        assertEquals(0, stringArray.size());
        assertTrue(stringArray.isEmpty());

        // 测试带数组的构造函数
        assertEquals(5, populatedArray.size());
        assertFalse(populatedArray.isEmpty());
        assertEquals(1, populatedArray.getFirst());
        assertEquals(5, populatedArray.getLast());

        // 测试非法参数
        assertThrows(IllegalArgumentException.class, () -> new DynamicArray<>(-1));
    }

    @Test
    public void testAddOperations() {
        // 测试addLast
        emptyArray.addLast(10);
        assertEquals(1, emptyArray.size());
        assertEquals(10, emptyArray.getLast());

        // 测试addFirst
        emptyArray.addFirst(5);
        assertEquals(2, emptyArray.size());
        assertEquals(5, emptyArray.getFirst());

        // 测试add(index, item)
        emptyArray.add(1, 7);
        assertEquals(3, emptyArray.size());
        assertEquals(7, emptyArray.get(1));

        // 测试索引越界
        assertThrows(IllegalArgumentException.class, () -> emptyArray.add(-1, 100));
        assertThrows(IllegalArgumentException.class, () -> emptyArray.add(emptyArray.size(), 100));
    }

    @Test
    public void testRemoveOperations() {
        // 测试removeLast
        Integer removed = populatedArray.removeLast();
        assertEquals(5, removed);
        assertEquals(4, populatedArray.size());
        assertEquals(4, populatedArray.getLast());

        // 测试removeFirst
        removed = populatedArray.removeFirst();
        assertEquals(1, removed);
        assertEquals(3, populatedArray.size());
        assertEquals(2, populatedArray.getFirst());

        // 测试remove(index)
        removed = populatedArray.remove(1);
        assertEquals(3, removed);
        assertEquals(2, populatedArray.size());

        // 测试索引越界
        assertThrows(IllegalArgumentException.class, () -> populatedArray.remove(-1));
        assertThrows(IllegalArgumentException.class, () -> populatedArray.remove(populatedArray.size()));

        // 测试空数组删除
        DynamicArray<Integer> empty = new DynamicArray<>();
        assertThrows(IllegalArgumentException.class, empty::removeLast);
        assertThrows(IllegalArgumentException.class, empty::removeFirst);
    }

    @Test
    public void testUpdateOperations() {
        // 测试update
        Integer oldValue = populatedArray.update(2, 30);
        assertEquals(3, oldValue);
        assertEquals(30, populatedArray.get(2));

        // 测试updateFirst
        oldValue = populatedArray.updateFirst(10);
        assertEquals(1, oldValue);
        assertEquals(10, populatedArray.getFirst());

        // 测试updateLast
        oldValue = populatedArray.updateLast(50);
        assertEquals(5, oldValue);
        assertEquals(50, populatedArray.getLast());

        // 测试索引越界
        assertThrows(IllegalArgumentException.class, () -> populatedArray.update(-1, 100));
        assertThrows(IllegalArgumentException.class, () -> populatedArray.update(populatedArray.size(), 100));
    }

    @Test
    public void testGetOperations() {
        // 测试get
        assertEquals(3, populatedArray.get(2));

        // 测试getFirst
        assertEquals(1, populatedArray.getFirst());

        // 测试getLast
        assertEquals(5, populatedArray.getLast());

        // 测试索引越界
        assertThrows(IllegalArgumentException.class, () -> populatedArray.get(-1));
        assertThrows(IllegalArgumentException.class, () -> populatedArray.get(populatedArray.size()));
    }

    @Test
    public void testDynamicGrowth() {
        // 测试动态扩容
        DynamicArray<Integer> growArray = new DynamicArray<>(3);
        growArray.addLast(1);
        growArray.addLast(2);
        growArray.addLast(3);
        // 此时数组已满，再添加应该触发扩容
        growArray.addLast(4);
        assertEquals(4, growArray.size());
        assertEquals(4, growArray.getLast());
    }

    @Test
    public void testToString() {
        // 测试toString方法
        String arrayString = populatedArray.toString();
        assertTrue(arrayString.contains("1"));
        assertTrue(arrayString.contains("2"));
        assertTrue(arrayString.contains("3"));
        assertTrue(arrayString.contains("4"));
        assertTrue(arrayString.contains("5"));
    }
}
