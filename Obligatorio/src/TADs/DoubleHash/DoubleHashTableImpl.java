package TADs.DoubleHash;
import TADs.ClosedHash.exceptions.*;
import TADs.ClosedHash.*;

public class DoubleHashTableImpl<RK,RV,CK,CV,TV> implements DoubleHashTable<RK,CK,TV> {

    private TV[][] table;
    private HashTable<RK, RV> row;
    private HashTable<CK, CV> column;
    private int count;

    public DoubleHashTableImpl(HashTable<RK, RV> row, HashTable<CK, CV> column) {
        this.table = (TV[][]) new Object[row.tableSize()][column.tableSize()];
        this.row = row;
        this.column = column;
        this.count = 0;
    }

    public void put(RK rowKey, CK columnKey, TV tableValue) throws KeyAlreadyExistsException, KeyNotExistsException {
        int rowPosition = row.getPosition(rowKey);
        int columnPosition = column.getPosition(columnKey);
        if (table[rowPosition][columnPosition] != null) throw new KeyAlreadyExistsException();
        else {
            table[rowPosition][columnPosition] = tableValue;
            count++;
        }
    }

    public boolean contains(RK rowKey, CK columnKey) {
        try {
            int rowPosition = row.getPosition(rowKey);
            int columnPosition = column.getPosition(columnKey);
            return table[rowPosition][columnPosition] != null;
        } catch (KeyNotExistsException e) {
            return false;
        }
    }

    public TV get(RK rowKey, CK columnKey) throws KeyNotExistsException {
        int rowPosition = row.getPosition(rowKey);
        int columnPosition = column.getPosition(columnKey);
        TV getValue = table[rowPosition][columnPosition];
        if (getValue != null) return getValue;
        else throw new KeyNotExistsException();
    }

    public void remove(RK rowKey, CK columnKey) throws KeyNotExistsException {
        int rowPosition = row.getPosition(rowKey);
        int columnPosition = column.getPosition(columnKey);
        if (table[rowPosition][columnPosition] != null) {
            table[rowPosition][columnPosition] = null;
            count--;
        }
    }

    public int rowSize(RK rowKey) throws KeyNotExistsException {
        int rowPosition = row.getPosition(rowKey);
        int size = 0;
        for (int i=0; i<row.tableSize(); i++){
            if (table[rowPosition][i]!=null) size ++;
        }
        return size;
    }

    public int columnSize(CK columnKey) throws KeyNotExistsException {
        int columnPosition = column.getPosition(columnKey);
        int size = 0;
        for (int i=0; i<row.tableSize(); i++){
            if (table[i][columnPosition]!=null) size ++;
        }
        return size;
    }

    public int size() {
        return count;
    }

    private int[] getPositions(RK rowKey, CK columnKey) throws KeyNotExistsException {
        return new int[]{row.getPosition(rowKey), column.getPosition(columnKey)};
    }

}
