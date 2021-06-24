package reportes.sort;

public class SortNode<K extends Comparable<K>,V> {
    private K key;
    private V value;

    public SortNode(K key) {
        this.key=key;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public void setValue(V value){this.value=value;}

    public V getValue() {
        return value;
    }

    public SortNode(K key, V value) {
        this.key = key;
        this.value = value;
    }
    public boolean equals(Object o){
        if (o instanceof SortNode) {
            SortNode<K,V> s = (SortNode<K,V>) o;
            return key.equals(s.getKey());
        }
        return false;
    }
}
