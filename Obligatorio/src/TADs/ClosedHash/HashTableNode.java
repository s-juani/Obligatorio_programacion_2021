package TADs.ClosedHash;

public class HashTableNode<K,V> {
    private K key;
    private V value;
    private boolean deleted;

    public HashTableNode(K key, V value) {
        this.key = key;
        this.value = value;
        this.deleted = false;
    }
    public HashTableNode(){
        key=null;
        value=null;
        deleted=false;
    }

    public K getKey() {
        return key;
    }
    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }
    public void setValue(V value) {
        this.value = value;
    }

    public boolean isDeleted() {
        return deleted;
    }
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}

