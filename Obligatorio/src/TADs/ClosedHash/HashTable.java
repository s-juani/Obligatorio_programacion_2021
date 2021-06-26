package TADs.ClosedHash;
import TADs.ClosedHash.exceptions.*;

import TADs.LinkedList.interfaces.Lista;

public interface HashTable<K,V> {
    void put(K key, V value) throws KeyAlreadyExistsException;
    boolean contains(K key);
    V get(K key) throws KeyNotExistsException;
    void remove(K key) throws KeyNotExistsException;
    int size();
    int getPosition(K key) throws KeyNotExistsException;
    int tableSize();
    V iteratorNext();
    void iteratorReset();
    Lista<V> values();
}

