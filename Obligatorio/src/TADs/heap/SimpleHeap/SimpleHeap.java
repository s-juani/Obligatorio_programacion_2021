package TADs.heap.SimpleHeap;
import TADs.heap.exceptions.*;

public interface SimpleHeap<T extends Comparable<T>> {
    void add(T element) throws HeapOverflowException;
    T remove() throws EmptyHeapException, HeapOverflowException;
    int size();
    void print();
}
