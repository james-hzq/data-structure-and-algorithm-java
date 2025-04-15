package com.hzq.structure.list;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SingleLinkedListTest {

    private SingleLinkedList<Integer> emptyList;
    private SingleLinkedList<String> stringList;
    private SingleLinkedList<Integer> populatedList;

    @BeforeEach
    public void setUp() {
        // 初始化测试对象
        emptyList = new SingleLinkedList<>();
        stringList = new SingleLinkedList<>();
        populatedList = new SingleLinkedList<>(new Integer[]{1, 2, 3, 4, 5});
    }

    @Test
    @Order(1)
    public void testConstructors() {
        // 测试默认构造函数
        assertEquals(0, emptyList.size());
        assertTrue(emptyList.isEmpty());

        // 测试带数组的构造函数
        assertEquals(5, populatedList.size());
        assertFalse(populatedList.isEmpty());
        assertEquals(1, populatedList.getFirst());
        assertEquals(5, populatedList.getLast());
        assertEquals("[1,2,3,4,5]", populatedList.toString());
    }

    @Test
    @Order(2)
    public void testAddMethods() {
        // 测试addFirst方法
        emptyList.addFirst(10);
        assertEquals(1, emptyList.size());
        assertEquals(10, emptyList.getFirst());

        // 测试addLast方法
        emptyList.addLast(20);
        assertEquals(2, emptyList.size());
        assertEquals(20, emptyList.getLast());

        // 测试add方法
        emptyList.add(1, 15);
        assertEquals(3, emptyList.size());
        assertEquals(15, emptyList.get(1));
        assertEquals("[10,15,20]", emptyList.toString());

        // 测试边界情况
        assertThrows(IllegalArgumentException.class, () -> emptyList.add(-1, 100));
        assertThrows(IllegalArgumentException.class, () -> emptyList.add(4, 100));
    }

    @Test
    @Order(3)
    public void testRemoveMethods() {
        // 测试removeFirst方法
        Integer first = populatedList.removeFirst();
        assertEquals(1, first);
        assertEquals(4, populatedList.size());
        assertEquals(2, populatedList.getFirst());

        // 测试removeLast方法
        Integer last = populatedList.removeLast();
        assertEquals(5, last);
        assertEquals(3, populatedList.size());
        assertEquals(4, populatedList.getLast());

        // 测试remove方法
        Integer middle = populatedList.remove(1);
        assertEquals(3, middle);
        assertEquals(2, populatedList.size());
        assertEquals("[2,4]", populatedList.toString());

        // 测试边界情况
        assertThrows(IllegalArgumentException.class, () -> populatedList.remove(-1));
        assertThrows(IllegalArgumentException.class, () -> populatedList.remove(2));
    }

    @Test
    @Order(4)
    public void testUpdateMethods() {
        // 测试updateFirst方法
        Integer oldFirst = populatedList.updateFirst(10);
        assertEquals(1, oldFirst);
        assertEquals(10, populatedList.getFirst());

        // 测试updateLast方法
        Integer oldLast = populatedList.updateLast(50);
        assertEquals(5, oldLast);
        assertEquals(50, populatedList.getLast());

        // 测试update方法
        Integer oldMiddle = populatedList.update(2, 30);
        assertEquals(3, oldMiddle);
        assertEquals(30, populatedList.get(2));
        assertEquals("[10,2,30,4,50]", populatedList.toString());

        // 测试边界情况
        assertThrows(IllegalArgumentException.class, () -> populatedList.update(-1, 100));
        assertThrows(IllegalArgumentException.class, () -> populatedList.update(5, 100));
    }

    @Test
    @Order(5)
    public void testGetMethods() {
        // 测试getFirst方法
        assertEquals(1, populatedList.getFirst());

        // 测试getLast方法
        assertEquals(5, populatedList.getLast());

        // 测试get方法
        assertEquals(3, populatedList.get(2));

        // 测试边界情况
        assertThrows(IllegalArgumentException.class, () -> populatedList.get(-1));
        assertThrows(IllegalArgumentException.class, () -> populatedList.get(5));
    }

    @Test
    @Order(6)
    public void testEmptyListOperations() {
        // 测试空列表的操作
        assertThrows(IllegalArgumentException.class, () -> emptyList.removeFirst());
        assertThrows(IllegalArgumentException.class, () -> emptyList.removeLast());
        assertThrows(IllegalArgumentException.class, () -> emptyList.getFirst());
        assertThrows(IllegalArgumentException.class, () -> emptyList.getLast());

        // 测试空列表的字符串表示
        assertEquals("[]", emptyList.toString());
    }

    @Test
    @Order(7)
    public void testStringList() {
        // 测试字符串类型的链表
        stringList.addLast("Hello");
        stringList.addLast("World");
        stringList.addLast("Java");

        assertEquals(3, stringList.size());
        assertEquals("Hello", stringList.getFirst());
        assertEquals("Java", stringList.getLast());
        assertEquals("[Hello,World,Java]", stringList.toString());
    }
}
