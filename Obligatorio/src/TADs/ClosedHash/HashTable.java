package TADs.ClosedHash;
import TADs.ClosedHash.exceptions.*;

public interface HashTable<K,V> {
    void put(K key, V value) throws KeyAlreadyExistsException;
    boolean contains(K key);
    V get(K key) throws KeyNotExistsException;
    void remove(K key) throws KeyNotExistsException;
    int size();
    int getPosition(K key) throws KeyNotExistsException;
    int tableSize();

}

