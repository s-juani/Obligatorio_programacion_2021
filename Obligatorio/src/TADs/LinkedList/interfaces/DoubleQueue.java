package TADs.LinkedList.interfaces;
import TADs.LinkedList.exceptions.*;

public interface DoubleQueue<Type> {
    void enqueueFirst(Type element);
    void enqueueLast(Type element);
    Type dequeueFirst() throws EmptyQueueException;
    Type dequeueLast() throws EmptyQueueException;
    int size();
    void makeEmpty();
    void printQueue();
}
