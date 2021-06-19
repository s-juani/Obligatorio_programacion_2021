package TADs.LinkedList.interfaces;

import TADs.LinkedList.Nodo;
import TADs.LinkedList.PriorityNode;
import TADs.LinkedList.exceptions.EmptyQueueException;

public interface ListWithPriority<T> {
    T dequeue() throws EmptyQueueException, EmptyQueueException;
    T first() throws EmptyQueueException;
    PriorityNode<T> getPriorityHead();
    int size();
    void makeEmpty();
    void printList();
    void enqueueWithPriority(T element, int prioridad);
    boolean find(T value);
    PriorityNode<T> findPriorityNode(T value);
    T get(int position);
}
