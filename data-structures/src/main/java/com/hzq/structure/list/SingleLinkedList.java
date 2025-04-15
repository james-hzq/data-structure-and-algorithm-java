package com.hzq.structure.list;

/**
 * 单向链表
 *
 * @author hzq
 * @date 2025/4/15 10:36
 */
public class SingleLinkedList<T> {

    private static class Node<T> {
        T item;
        Node<T> next;

        Node(T item) {
            this(item, null);
        }

        Node(T item, Node<T> next) {
            this.item = item;
            this.next = next;
        }

        public void setNull() {
            this.item = null;
            this.next = null;
        }
    }

    private final Node<T> head;
    private int size;

    public SingleLinkedList() {
        this.head = new Node<>(null);
        this.size = 0;
    }

    public SingleLinkedList(T[] array) {
        this.head = new Node<>(null);
        for (int i = array.length - 1; i >= 0; i--) {
            this.head.next = new Node<>(array[i], this.head.next);
        }
        this.size = array.length;
    }

    public void addFirst(T item) {
        add(0, item);
    }

    public void addLast(T item) {
        add(size, item);
    }

    public void add(int index, T item) {
        if (index < 0 || index > this.size) throw new IllegalArgumentException("添加的索引越界");

        Node<T> node = this.head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }

        node.next = new Node<>(item, node.next);
        this.size++;
    }

    public T removeFirst() {
        return remove(0);
    }

    public T removeLast() {
        return remove(this.size - 1);
    }

    public T remove(int index) {
        if (index < 0 || index >= this.size) throw new IllegalArgumentException("删除的索引越界");

        Node<T> node = this.head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }

        Node<T> delNode = node.next;
        T oldItem = delNode.item;
        node.next = delNode.next;
        delNode.setNull();
        this.size--;
        return oldItem;
    }

    public T updateFirst(T item) {
        return update(0, item);
    }

    public T updateLast(T item) {
        return update(this.size - 1, item);
    }

    public T update(int index, T item) {
        if (index < 0 || index >= this.size) throw new IllegalArgumentException("修改的索引越界");

        Node<T> node = this.head;
        for (int i = 0; i <= index; i++) {
            node = node.next;
        }

        T oldItem = node.item;
        node.item = item;
        return oldItem;
    }

    public T getFirst() {
        return get(0);
    }

    public T getLast() {
        return get(this.size - 1);
    }

    public T get(int index) {
        if (index < 0 || index >= this.size) throw new IllegalArgumentException("查找的索引越界");

        Node<T> node = this.head;
        for (int i = 0; i <= index; i++) {
            node = node.next;
        }

        return node.item;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public String toString() {
        if (this.head.next == null) {
            return "[]";
        }
        StringBuilder str = new StringBuilder().append('[');
        Node<T> node = this.head;

        while (node.next != null) {
            str.append(node.next.item.toString());
            node = node.next;

            if (node.next != null) {
                str.append(',');
            }
        }
        return str.append(']').toString();
    }
}
