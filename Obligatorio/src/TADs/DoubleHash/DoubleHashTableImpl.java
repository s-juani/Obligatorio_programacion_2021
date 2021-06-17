package TADs.DoubleHash;
import TADs.ClosedHash.exceptions.*;
import TADs.ClosedHash.*;

public class DoubleHashTableImpl<RK,RV,CK,CV,TV> implements DoubleHashTable<RK,CK,TV> {

    private TV[][] table;
    private ClosedHashTable<RK, RV> row;
    private ClosedHashTable<CK, CV> column;
    private int count;

    public DoubleHashTableImpl(ClosedHashTable<RK, RV> row, ClosedHashTable<CK, CV> column) {
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

    public int size() {
        return count;
    }

    private int[] getPositions(RK rowKey, CK columnKey) throws KeyNotExistsException {
        return new int[]{row.getPosition(rowKey), column.getPosition(columnKey)};
    }

}
