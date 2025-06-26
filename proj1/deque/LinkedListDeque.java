package deque;

import java.util.Iterator;

public class LinkedListDeque<T> {
    private static class node<T> {
        public T item;
        public node next;
        public node prev;
        public node(T item, node prev, node next){
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    private node head;
    private int size;

    public LinkedListDeque(){
        head = new node(null, null, null);//这里不直接在构建函数里写(null,head,head)是因为此时head未初始化，会被赋值为null
        head.prev = head;                                 //故在head初始化后再改变prev和next
        head.next = head;
    }

    public void addFirst(T item){
        node first = head.next;
        node new_node = new node(item, head, first);
        head.next = new_node;
        first.prev = new_node;
        if (first == head){     // 若first指向node，说明原链表为空，需要将末尾的指针也指向新节点，即新节点既是起点也是终点
            head.prev = new_node;
        }
        size += 1;
    }

    public void addLast(T item){
        node last = head.prev;
        node new_node = new node(item, last, head);
        head.prev = new_node;
        last.next = new_node;
        if (last == head){      // 同理，若原链表为空，则新节点既是起点也是终点
            head.next = new_node;
        }
        size += 1;
    }

    public boolean isEmpty(){
        if (head.next == head && head.prev == head) return true;
        return false;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        node now = head.next;
        while (now != head){
            System.out.println(now.item+" ");
            now = now.next;
        }
        System.out.println();
    }

    public T removeFirst(){
        if (this.isEmpty()) return null;
        T item = (T) head.next.item; //这里需要将next的item类型转换为T，否则默认为object
        head.next.item = null;
        if (head.prev == head.next) head.prev = head; //若始终节点都为第一个节点，说明链表只有一个节点，将终节点指回head
        head.next = head.next.next;
        size -= 1;
        return item;
    }

    public T removeLast(){
        if (this.isEmpty()) return null;
        T item = (T) head.prev.item; //这里需要将next的item类型转换为T，否则默认为object
        head.prev.item = null;
        if (head.prev == head.next) head.next = head; //若始终节点都为第一个节点，说明链表只有一个节点，将始节点指回head
        head.prev = head.prev.prev;
        size -= 1;
        return item;
    }

    public T get(int index){
        node now = head.next;
        if(now == head) return null;
        for (int i = 0; i < index; i++){
            now = now.next;
            if(now == head) return null;
        }
        return (T)now.item;
    }

//    public Iterator<T> iterator(){}

    @Override
    public boolean equals(Object o){
        if(!(o instanceof LinkedListDeque)) return false;
        LinkedListDeque no = (LinkedListDeque)o;
        if(no.size() != this.size()) return false;
        for(int i = 0; i < size; i++){
            if(no.get(i) != this.get(i)) return false;
        }
        return true;
    }

    public T getRecursive(int index){
        return getRecursive(head.next, index);
    }
    private T getRecursive(node now, int index){
        if (index == 0){
            return (T)now.item;
        }
        if (now.next == head){
            return null;
        }
        return getRecursive(now.next, --index);
    }
}
