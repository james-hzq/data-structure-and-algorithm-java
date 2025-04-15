package com.hzq.structure.array;

import java.io.Serial;
import java.io.Serializable;
import java.util.Arrays;

/**
 * 动态扩容数组
 *
 * @author hzq
 * @date 2025/4/14 12:16
 */
public class DynamicArray<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = -8626219106424551133L;

    private static final int DEFAULT_CAPACITY = 10;

    private Object[] array;
    private int capacity;
    private int size;

    public DynamicArray() {
        this(DEFAULT_CAPACITY);
    }

    public DynamicArray(int capacity) {
        if (capacity <= 0) throw new IllegalArgumentException("初始容量不得小于等于零");
        this.array = new Object[capacity];
        this.capacity = capacity;
        this.size = 0;
    }

    public DynamicArray(T[] items) {
        if ((long) items.length + DEFAULT_CAPACITY > Integer.MAX_VALUE) throw new IllegalArgumentException("传入数组过大");
        this.size = items.length;
        this.capacity = items.length + DEFAULT_CAPACITY;
        this.array = new Object[this.capacity];
        System.arraycopy(items, 0, this.array, 0, items.length);
    }

    public void addFirst(T item) {
        add(0, item);
    }

    public void addLast(T item) {
        if (this.size == this.capacity) grow(this.size + 1);
        this.array[this.size++] = item;
    }

    public void add(int index, T item) {
        if (index < 0 || index >= this.size) throw new IllegalArgumentException("添加索引越界");
        if (this.size == this.capacity) grow(this.size + 1);
        System.arraycopy(this.array, index, this.array, index + 1, this.size - index);
        this.array[index] = item;
        this.size++;
    }

    private void grow(int minCapacity) {
        // 检查是否需要的容量已超过 Integer.MAX_VALUE
        if (minCapacity <= 0) throw new OutOfMemoryError("数组容量过大");

        int newCapacity = (this.capacity >> 1) + this.capacity;
        // 检查计算后的新容量是否溢出或小于所需的最小容量
        if (newCapacity < 0 || newCapacity < minCapacity) {
            newCapacity = minCapacity;
        }
        // 确保不超过 Integer.MAX_VALUE - 8, 预留一些空间用于JVM内部使用
        if (newCapacity > Integer.MAX_VALUE - 8) {
            newCapacity = Integer.MAX_VALUE - 8;
        }

        Object[] newArray = new Object[newCapacity];
        System.arraycopy(this.array, 0, newArray, 0, this.size);
        this.array = newArray;
        this.capacity = newCapacity;
    }

    public T removeFirst() {
        return remove(0);
    }

    @SuppressWarnings("unchecked")
    public T removeLast() {
        if (isEmpty()) throw new IllegalArgumentException("数组为空，删除索引越界");
        T oldItem = (T) this.array[this.size - 1];
        this.array[--this.size] = null;
        return oldItem;
    }

    @SuppressWarnings("unchecked")
    public T remove(int index) {
        if (index < 0 || index >= this.size) throw new IllegalArgumentException("删除索引越界");
        T oldItem = (T) this.array[index];
        System.arraycopy(this.array, index + 1, this.array, index, this.size - index - 1);
        this.array[--this.size] = null;
        return oldItem;
    }

    public T updateFirst(T item) {
        return update(0, item);
    }

    public T updateLast(T item) {
        return update(this.size - 1, item);
    }

    @SuppressWarnings("unchecked")
    public T update(int index, T item) {
        if (index < 0 || index >= this.size) throw new IllegalArgumentException("修改索引越界");
        T oldItem = (T) this.array[index];
        this.array[index] = item;
        return oldItem;
    }

    public T getFirst() {
        return get(0);
    }

    public T getLast() {
        return get(this.size - 1);
    }

    @SuppressWarnings("unchecked")
    public T get(int index) {
        if (index < 0 || index >= this.size) throw new IllegalArgumentException("查找索引越界");
        return (T) this.array[index];
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int size() {
        return this.size;
    }

    @Override
    public String toString() {
        return Arrays.toString(this.array);
    }
}
