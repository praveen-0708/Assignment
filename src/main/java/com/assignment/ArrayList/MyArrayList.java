package com.assignment.ArrayList;

import java.util.*;

public class MyArrayList<E> implements List<E> {
    private static final int DEFAULT_SIZE = 10;
    private Object[] dataArray;
    private int size;
    public MyArrayList(int sizeOfArrayList){
        this.size=0;
        if(sizeOfArrayList>=0){
            this.dataArray=new Object[sizeOfArrayList];
        }
        else
            throw new IllegalArgumentException("ArrayList size cannot be negative");
    }

    public MyArrayList(){
        this.size=0;
        this.dataArray=new Object[DEFAULT_SIZE];
    }

    private void increaseArraySize(){
        int oldSize=dataArray.length;
        int newSize=oldSize + (oldSize>>1);
        dataArray= Arrays.copyOf(dataArray,newSize);
    }

    private void checkSize(int requiredSize){
        if(requiredSize>this.dataArray.length)
            increaseArraySize();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public boolean add(E newData) {
        checkSize(size+1);
        dataArray[size++]=newData;
        return true;
    }
    @Override
    public E get(int index) {
        if(index>=0 && index<size)
            return (E) dataArray[index];
        else
            throw new IllegalArgumentException("invalid index");
    }

    @Override
    public boolean contains(Object o) {
        for(int i=0;i<size;i++)
            if(o.equals(dataArray[i]))
                return true;
        return false;
    }

    @Override
    public E set(int index, E element) {
        E oldData=get(index);
        dataArray[index]=element;
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
        for (int i = size-1; i >= 0; i--)
            if (o.equals(dataArray[i]))
                return i;
        return -1;
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean addAll(Collection c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection c) {
        return false;
    }


    @Override
    public void add(int index, Object element) {

    }

    @Override
    public E remove(int index) {
        return null;
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

    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection c) {
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        return false;
    }

    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }
}
