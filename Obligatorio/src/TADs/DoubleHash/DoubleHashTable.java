package TADs.DoubleHash;
import TADs.ClosedHash.exceptions.*;

public interface DoubleHashTable<RK,CK,TV> {
    void put(RK rowKey, CK columnKey, TV tableValue) throws KeyAlreadyExistsException, KeyNotExistsException;
    boolean contains(RK rowKey, CK columnKey);
    TV get(RK rowKey, CK columnKey) throws KeyNotExistsException;
    void remove(RK rowKey, CK columnKey) throws KeyNotExistsException;
    int size();
}
