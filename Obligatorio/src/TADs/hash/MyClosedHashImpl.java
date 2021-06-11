package TADs.hash;

/**
 * La implementacion de rehash() no esta completa, a la hora de aumentar el tamaño solamente lo duplica, cuando
 * lo optimo seria hayar el numero primo mas cercano al doble del tamaño actual.
 * Si el tamaño inicial se mantiene durante la vida util del hash no hay problemas con la implementacion.
 *      Santiago - 03/06 - 16:25
 */


public class MyClosedHashImpl<K,V> implements MyHash<K,V> {

    private static final int DEFAULT_SIZE = 10;
    private double loadFactor;
    private int maxSize;

    private ClosedHashNode[] hashTable;
    private int size;

    public MyClosedHashImpl() {
        hashTable = new ClosedHashNode[DEFAULT_SIZE];
        this.loadFactor = (float) 0.8;
        this.maxSize = (int) (DEFAULT_SIZE * loadFactor);
    }

    public MyClosedHashImpl(int tableHashSize, double loadFactor) {
        hashTable = new ClosedHashNode[tableHashSize];
        this.loadFactor = loadFactor;
        this.maxSize = (int) (tableHashSize * loadFactor);
    }

    @Override
    public void put(K key, V value) {
        int tries = 0;
        int hashCode = getHashLinear(key, tries);
        ClosedHashNode nodeToAdd = new ClosedHashNode(key, value);
        boolean inProcess = true;
        while (inProcess) {

            if (hashTable[hashCode] == null || hashTable[hashCode].isDeleted()) {
                hashTable[hashCode] = nodeToAdd;
                incrementSize();
                inProcess = false;
            } else {
                hashCode = getHashLinear(key, tries++);
            }
        }
    }

    @Override
    public V get(K key) {
        int tries = 0;
        int hashCode = getHashLinear(key, tries);
        V objective = null;

        boolean inProcess = true;
        while (inProcess) {

            // La casilla apunta a null
            if (hashTable[hashCode] == null) {
                inProcess = false;
            }

            // Hay un elemento en la casilla y es el que busco
            else if (key.equals(hashTable[hashCode].getKey()) && !hashTable[hashCode].isDeleted()) {
                objective = (V) hashTable[hashCode].getValue();
                inProcess = false;
            }

            // Hay un elemento en la casilla y no es el que busco
            else {
                hashCode = getHashLinear(key, tries++);
            }
        }
        return objective;
    }

    @Override
    public boolean contains(K key) {
        return get(key) != null;
    }

    @Override
    public void delete(K key) {
        int tries = 0;
        int hashCode = getHashLinear(key, tries);

        boolean inProcess = true;
        while (inProcess) {

            // La casilla apunta a null
            if (hashTable[hashCode] == null) {
                inProcess = false;
            }

            // Hay un elemento en la casilla y es el que busco
            else if (key.equals(hashTable[hashCode].getKey()) && !hashTable[hashCode].isDeleted()) {
                hashTable[hashCode].setDeleted(true);
                size--;
                inProcess = false;
            }

            // Hay un elemento en la casilla y no es el que busco
            else {
                hashCode = getHashLinear(key, tries++);
            }
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void print() {
        for (int i = 0; i < hashTable.length; i++) {

            if (hashTable[i] != null) {
                System.out.println(hashTable[i].getKey() + " " + hashTable[i].getValue() + " " + hashTable[i].isDeleted());
            } else {
                System.out.println(hashTable[i]);
            }
        }
    }

    private int getHashLinear(K key, int tries) {
        int temp = (key.hashCode() % hashTable.length) + tries;

        if (temp >= hashTable.length) {
            temp -= hashTable.length;
        }
        if (temp < 0) temp *= -1;
        return temp;
    }

    private void incrementSize() {
        size++;
        if (size > maxSize) {
            rehash();
        }
    }

    private void rehash() {

        ClosedHashNode[] temp = new ClosedHashNode[hashTable.length * 2];

        for (int i = 0; i < hashTable.length; i++) {
            if (hashTable[i] != null) {
                temp[i] = hashTable[i];
            }
        }
        hashTable = temp;
    }   //FIXME: Implement nearest prime number
}
