package TADs.heap;

import TADs.heap.exceptions.EmptyHeapException;
import TADs.heap.exceptions.HeapOverflowException;
import pr5.exceptions.EmptyHeapException;
import pr5.exceptions.HeapOverflowException;

public interface Heap<T extends Comparable<T>> {
    void add(T element) throws HeapOverflowException, HeapOverflowException;
    T remove() throws EmptyHeapException, HeapOverflowException, EmptyHeapException;
    int size();
    void print();
}
