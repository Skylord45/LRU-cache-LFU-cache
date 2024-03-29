import java.util.*;

public class LRUCache
{
    class Node {
        int key;
        int value;
        Node prev;
        Node next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    Node head = new Node(0,0);
    Node tail = new Node(0,0);
    Map <Integer, Node> mpp = new HashMap(); 
    int cap;

    LRUCache(int capacity)
    {
        cap = capacity;
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key)
    {
        if(mpp.containsKey(key)){
            Node node = mpp.get(key);
            remove(node);
            insert(node);
            return node.value;
        }
        return -1;
    }

    public void put(int key, int value)
    {
        if(mpp.containsKey(key)){
            remove(mpp.get(key));
        }   
        if(mpp.size() == cap){
            remove(tail.prev);
        }
        insert(new Node(key, value));
    }

    private void remove(Node node){
        mpp.remove(node.key);

        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    private void insert(Node node){
        mpp.put(node.key, node);
        node.next = head.next;
        node.next.prev = node;
        head.next = node;
        node.prev = head;
    }

}
 
