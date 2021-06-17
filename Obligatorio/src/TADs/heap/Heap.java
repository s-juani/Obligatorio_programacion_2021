package TADs.heap;

import TADs.heap.exceptions.EmptyHeapException;
import TADs.heap.exceptions.HeapOverflowException;

public interface Heap<T extends Comparable<T>, V> {
    void add(T key, V value) throws HeapOverflowException;
    Object[] remove() throws HeapOverflowException, EmptyHeapException;
    int size();
    void print();
    Object[] top() throws HeapOverflowException, EmptyHeapException;
}
