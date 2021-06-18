package TADs.LinkedList.interfaces;
import TADs.LinkedList.exceptions.EmptyQueueException;

public interface Queue <Type> {
    void enqueue(Type element);
    Type dequeue() throws EmptyQueueException, EmptyQueueException;
    Type first() throws EmptyQueueException;
    int size();
    void makeEmpty();
    void printQueue();

}
