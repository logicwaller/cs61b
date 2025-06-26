package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private T[] array;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        array = (T[]) new Object[8];
        size = 0;
        nextFirst = array.length - 1;
        nextLast = 0;
    }

    /* 计算循环下的新索引,若isPrev为true，则计算index-1的新索引，否则计算index+1的新索引*/
    private int circularIndex(boolean isPrev, int index) {
        int res;
        if (isPrev) {
            res = index - 1;
            if (res < 0) {
                res = array.length - 1;
            }
        } else {
            res = index + 1;
            if (res > array.length -1) {
                res = 0;
            }
        }
        return res;
    }

    @Override
    public void addFirst(T item) {
        if (size == array.length) {
            resize(size * 2);
        }
        array[nextFirst] = item;
        size += 1;
        nextFirst = circularIndex(true, nextFirst);
    }

    @Override
    public void addLast(T item) {
        if (size == array.length) {
            resize(size * 2);
        }
        array[nextLast] = item;
        size += 1;
        nextLast = circularIndex(false, nextLast);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        int start = circularIndex(false, nextFirst);    //数组起始点为nextFirst的下一个
        int end = circularIndex(true, nextLast);        //数组终点为nextLast的前一个
        while (start != end) {
            System.out.println(array[start] + " ");
            start = circularIndex(false, start);
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        int first = circularIndex(false, nextFirst);
        T item = array[first];
        array[first] = null;
        nextFirst = first;
        size -= 1;
        if (size < array.length / 4 && array.length >= 16) {
            resize(array.length / 4);
        }
        return item;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        int last = circularIndex(true, nextLast);
        T item = array[last];
        array[last] = null;
        nextLast = last;
        size -= 1;
        if (size < array.length / 4 && array.length >= 16) {
            resize(array.length / 4);
        }
        return item;
    }

    /* 缩小array的长度 */
    private void resize(int length) {
        T[] newA = (T[]) new Object[length];
        int start = circularIndex(false, nextFirst);
        for (int i = 0; i < size; i++) {
            newA[i] = array[start];
            start = circularIndex(false, start);
        }

        array = newA;
        nextFirst = array.length - 1;
        nextLast = size;            //这里nextFirst重指向为新数组的最后一个位置，nextLast为新数组未被赋值的第一个位置
    }

    @Override
    public T get(int index) {
        if (index > size - 1) return null;
        int start = circularIndex(false, nextFirst);
        start = (start + index) % array.length;
        return array[start];
    }

    private class ArryDequeIterator implements Iterator<T> {
        int pos = 0;
        int index = circularIndex(false, nextFirst);
        @Override
        public boolean hasNext() {
            return (pos < size);
        }

        @Override
        public T next() {
            T item = array[index];
            index = circularIndex(false, index);
            pos += 1;
            return item;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ArryDequeIterator();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Deque) {
            Deque no = (Deque) o;
            if (no.size() != this.size) {
                return false;
            }
            for (int i = 0; i < size; i++) {
                if (!no.get(i).equals(this.get(i))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

}
