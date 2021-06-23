package reportes;

public class SortNode<K extends Comparable<K>,V> {
    private K key;
    private V value;

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public SortNode(K key, V value) {
        this.key = key;
        this.value = value;
    }
}
