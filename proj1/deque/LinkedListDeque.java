package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {
    private static class Node<T> {
        T item;
        Node next;
        Node prev;
        Node(T item, Node prev, Node next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    private Node head;
    private int size;

    public LinkedListDeque() {
        head = new Node(null, null, null);  //这里不直接在构建函数里写(null,head,head)是因为此时head未初始化，会被赋值为null
        head.prev = head;                                   //故在head初始化后再改变prev和next
        head.next = head;
    }

    @Override
    public void addFirst(T item) {
        Node first = head.next;
        Node newNode = new Node(item, head, first);
        head.next = newNode;
        first.prev = newNode;
        if (first == head) {     // 若first指向node，说明原链表为空，需要将末尾的指针也指向新节点，即新节点既是起点也是终点
            head.prev = newNode;
        }
        size += 1;
    }

    @Override
    public void addLast(T item) {
        Node last = head.prev;
        Node newNode = new Node(item, last, head);
        head.prev = newNode;
        last.next = newNode;
        if (last == head) {      // 同理，若原链表为空，则新节点既是起点也是终点
            head.next = newNode;
        }
        size += 1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        Node now = head.next;
        while (now != head) {
            System.out.println(now.item + " ");
            now = now.next;
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        if (this.isEmpty()) {
            return null;
        }
        T item = (T) head.next.item; //这里需要将next的item类型转换为T，否则默认为object
        head.next.item = null;
        if (head.prev == head.next) {
            head.prev = head; //若始终节点都为第一个节点，说明链表只有一个节点，将终节点指回head
        }
        head.next = head.next.next;
        head.next.prev = head;
        size -= 1;
        return item;
    }

    @Override
    public T removeLast() {
        if (this.isEmpty()) {
            return null;
        }
        T item = (T) head.prev.item; //这里需要将next的item类型转换为T，否则默认为object
        head.prev.item = null;
        if (head.prev == head.next) {
            head.next = head; //若始终节点都为第一个节点，说明链表只有一个节点，将始节点指回head
        }
        head.prev = head.prev.prev;
        head.prev.next = head;
        size -= 1;
        return item;
    }

    @Override
    public T get(int index) {
        Node now = head.next;
        if (now == head) {
            return null;
        }
        for (int i = 0; i < index; i++) {
            now = now.next;
            if (now == head) {
                return null;
            }
        }
        return (T) now.item;
    }

    private class LinkedListDequeIterator implements Iterator<T> {
        int pos = 0;
        Node now = head.next;
        @Override
        public boolean hasNext() {
            return (pos < size);
        }

        @Override
        public T next() {
            T item = (T) now.item;
            now = now.next;
            pos += 1;
            return item;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Deque) {
            Deque no = (Deque) o;
            if (this.size() != no.size()) {
                return false;
            }
            Node t = this.head.next;
            for (int i = 0; i < this.size(); i++) {
                if (!t.item.equals(no.get(i))) {
                    return false;
                }
                t = t.next;
            }
            return true;
        }
        return false;
    }

    public T getRecursive(int index) {
        return getRecursive(head.next, index);
    }
    private T getRecursive(Node now, int index) {
        if (index == 0) {
            return (T) now.item;
        }
        if (now.next == head) {
            return null;
        }
        return getRecursive(now.next, --index);
    }
}
