package TADs.ClosedHash;
import TADs.ClosedHash.exceptions.*;
import TADs.LinkedList.ListaEnlazada;
import TADs.LinkedList.interfaces.Lista;

public class ClosedHashTable<K,V> implements HashTable<K,V> {

    private HashTableNode<K,V>[] table;

    private int count;
    private static final int DEFAULT_INITIAL_SIZE = 11;
    private static final float DEFAULT_LOAD_FACTOR = 0.5f;
    private final float loadFactor;
    private final String resolucionColision;
    private int it;

    public V iteratorNext() {
        do {
            it++;
            if (!(it < table.length)) {
                return null;
            }
        } while (table[it] == null);
        return table[it].getValue();
    }

    public void iteratorReset() {
        it = -1;
    }

    public ClosedHashTable() {
        this(DEFAULT_INITIAL_SIZE,DEFAULT_LOAD_FACTOR);
    }
    public ClosedHashTable(int initialSize, float loadFactor) {
        table = new HashTableNode[initialSize];
        this.loadFactor = loadFactor;
        count = 0;
        resolucionColision = "lineal";
        it = -1;
    }
    public ClosedHashTable(int initialSize, float loadFactor, String resolucionColision) {
        table = new HashTableNode[initialSize];
        this.loadFactor = loadFactor;
        count = 0;
        this.resolucionColision = resolucionColision;
    }

    public void put(K key, V value) throws KeyAlreadyExistsException {
        if (contains(key)) {
            //System.out.println(key.toString());
            throw new KeyAlreadyExistsException();
        }
        else {
            if (count < table.length * loadFactor) {
                HashTableNode<K, V> newNode = new HashTableNode<>(key,value);
                int position = hashFunction(key.hashCode());
                if (position>=0) insertInPosition(0,position, newNode);
                else insertInPosition(0,-position, newNode);
            } else {
                rehash();
                put(key, value);
            }
        }
    }
    public boolean contains(K key) {
        return getNode(key)!=null;
    }
    public V get(K key) throws KeyNotExistsException {
        HashTableNode<K, V> getNode = getNode(key);
        if (getNode != null) return getNode.getValue();
        else throw new KeyNotExistsException();
    }
    public void remove(K key) throws KeyNotExistsException {
        HashTableNode<K, V> deleteNode = getNode(key);
        if (deleteNode != null) {
            deleteNode.setDeleted(true);
            count--;
        } else throw new KeyNotExistsException();
    }
    public int size() {
        return count;
    }
    public int getPosition(K key) throws KeyNotExistsException {
        int position = hashFunction(key.hashCode());
        if (position>=0) return searchNodePosition(0,position,key);
        else return searchNodePosition(0,-position,key);
    }
    public int tableSize() {
        return table.length;
    }

    private HashTableNode<K, V> getNode(K key) {
        if (count != 0) {
            int position = hashFunction(key.hashCode());
            if (position>=0) return searchNode(0,position,key);
            else return searchNode(0,-position,key);

        }
        return null;
    }
    private HashTableNode<K, V> searchNode(int attempt, int position, K key) {
        HashTableNode<K, V> tempNode = table[position];
        if (tempNode==null) return null;
        else {
            if (tempNode.getKey().equals(key)) {
                if (!tempNode.isDeleted()) return tempNode;
                else return null;
            }
            else {
                attempt++;
                position = nextPosition(attempt,position);
                return searchNode(attempt,position,key);
            }
        }
    }
    private int searchNodePosition(int attempt, int position, K key) throws KeyNotExistsException{
        HashTableNode<K, V> tempNode;
        if (position>=0) tempNode = table[position];
        else tempNode = table[-position];

        if (tempNode==null) throw new KeyNotExistsException();
        else {
            if (tempNode.getKey().equals(key)) {
                if (!tempNode.isDeleted()) return position;
                else throw new KeyNotExistsException();
            }
            else {
                attempt++;
                position = nextPosition(attempt,position);
                return searchNodePosition(attempt,position,key);
            }
        }
    }
    private void rehash() throws KeyAlreadyExistsException {
        ClosedHashTable<K, V> newTable = new ClosedHashTable<>(table.length<<1+1,loadFactor);
        for (HashTableNode<K, V> node : table) {
            if (node!=null) newTable.put(node.getKey(),node.getValue());
        }
        this.table = newTable.table;
    }
    private int hashFunction(int hashCode) {
        return hashCode % table.length;
    }
    private void insertInPosition(int attempt, int position, HashTableNode<K, V> newNode)    {
        HashTableNode<K,V> tempNode = table[position];
        if (tempNode == null || tempNode.isDeleted()) {
            table[position] = newNode;
            count++;
        } else {
            attempt++;
            int nextPosition = nextPosition(attempt, position);
            insertInPosition(attempt,nextPosition,newNode);
        }
    }
    private int nextPosition(int attempt, int position){
        if (resolucionColision.equals("lineal")) return (position + attempt) % table.length;
        if (resolucionColision.equals("cuadratica")) return (position + attempt*attempt) % table.length;
        return (position + attempt) % table.length;
    }

    @Override
    public Lista<V> values(){
        Lista<V> toReturn = new ListaEnlazada<>();
        for (int i=0; i<this.size();i++){
            if (table[i] != null){
                toReturn.add(table[i].getValue());
            }
        }
        return toReturn;
    }
}

