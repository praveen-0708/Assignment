package com.assignment.arraylist;

import java.util.*;

public class MyArrayList<E> implements List<E> {
    private static final int DEFAULT_SIZE = 10;
    private Object[] dataArray;
    private int size;

    public MyArrayList(int sizeOfArrayList) {
        this.size = 0;
        if (sizeOfArrayList >= 0) {
            this.dataArray = new Object[sizeOfArrayList];
        } else
            throw new IllegalArgumentException("ArrayList size cannot be negative");
    }

    public MyArrayList() {
        this.size = 0;
        this.dataArray = new Object[DEFAULT_SIZE];
    }

    private void increaseArraySize() {
        int oldSize = dataArray.length;
        int newSize = oldSize + (oldSize >> 1);
        dataArray = Arrays.copyOf(dataArray, newSize);
    }

    private void checkSize(int requiredSize) {
        if (requiredSize > this.dataArray.length) {
            increaseArraySize();
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean add(E newData) {
        checkSize(size + 1);
        dataArray[size++] = newData;
        return true;
    }

    @Override
    public E get(int index) {
        if (index >= 0 && index < size)
            return (E) dataArray[index];
        else
            throw new IllegalArgumentException("invalid index");
    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < size; i++) {
            if (o.equals(dataArray[i])) {
                return true;
            }
        }
        return false;
    }

    @Override
    public E set(int index, E element) {
        E oldData = get(index);
        dataArray[index] = element;
        return oldData;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++)
            if (o.equals(dataArray[i]))
                return i;
        return -1;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++)
            dataArray[i] = null;
        size = 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--)
            if (o.equals(dataArray[i]))
                return i;
        return -1;
    }

    @Override
    public void add(int index, Object element) {
        if (index > size || index < 0)
            throw new IndexOutOfBoundsException("Index Out of Bounds");
        checkSize(size + 1);
        System.arraycopy(dataArray, index, dataArray, index + 1, size - index);
        dataArray[index] = element;
        size++;
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("index out of bounds");
        E oldElement = (E) dataArray[index];
        int newPosition = size - index - 1;
        if (newPosition > 0) {
            System.arraycopy(dataArray, index + 1, dataArray, index, newPosition);
        }
        dataArray[--size] = null;
        return oldElement;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(dataArray, size);
    }

    @Override
    public boolean remove(Object o) {
        for (int index = 0; index < size; index++)
            if (o.equals(dataArray[index])) {
                remove(index);
                return true;
            }
        return false;
    }

    @Override
    public boolean addAll(Collection c) {
        Object[] a = c.toArray();
        int collectionSize = a.length;
        checkSize(size + collectionSize);
        System.arraycopy(a, 0, dataArray, size, collectionSize);
        size += collectionSize;
        if (collectionSize == 0)
            return false;
        else
            return true;
    }

    @Override
    public boolean addAll(int index, Collection c) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("index out of bounds");
        Object[] a = c.toArray();
        int collectionSize = a.length;
        checkSize(size + collectionSize);
        int newPosition = size - index;
        if (newPosition > 0)
            System.arraycopy(dataArray, index, dataArray, index + collectionSize, newPosition);
        System.arraycopy(a, 0, dataArray, index, collectionSize);
        size += collectionSize;
        if (collectionSize == 0)
            return false;
        else
            return true;
    }

    @Override
    public Object[] toArray(Object[] a) {
        return Arrays.copyOf(dataArray, size);
    }

    @Override
    public boolean retainAll(Collection c) {
        int newIndex = 0;
        boolean modified = false;
        for (int index = 0; index < size; index++) {
            if (c.contains(dataArray[index]))
                dataArray[newIndex++] = dataArray[index];
        }
        if (newIndex != size) {
            for (int index = newIndex; index < size; index++)
                dataArray[index] = null;
            size = newIndex;
            modified = true;
        }
        return modified;
    }

    @Override
    public boolean removeAll(Collection c) {
        int newIndex = 0;
        boolean modified = false;
        for (int index = 0; index < size; index++) {
            if (!c.contains(dataArray[index]))
                dataArray[newIndex++] = dataArray[index];
        }
        if (newIndex != size) {
            for (int index = newIndex; index < size; index++)
                dataArray[index] = null;
            size = newIndex;
            modified = true;
        }
        return modified;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object e : c)
            if (!contains(e))
                return false;
        return true;
    }

    @Override
    public Iterator iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<E> {
        int currentPosition;
        int previousPosition = -1;

        MyIterator() {

        }

        @Override
        public boolean hasNext() {
            return currentPosition != size;
        }

        @Override
        public E next() {
            int index = currentPosition;
            if (index >= size) {
                throw new NoSuchElementException();
            }
            Object[] dataArray = MyArrayList.this.dataArray;
            if (index >= dataArray.length)
                throw new ConcurrentModificationException();
            currentPosition = index + 1;
            previousPosition = index;
            return (E) dataArray[previousPosition];
        }
    }

    @Override
    public ListIterator listIterator() {
        return null;
    }

    @Override
    public ListIterator listIterator(int index) {
        return null;
    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        return null;
    }
}
