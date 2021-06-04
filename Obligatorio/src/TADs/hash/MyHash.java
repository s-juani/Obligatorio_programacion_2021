package TADs.hash;

public interface MyHash<K,V> {

    public void put(K key, V value);

    public boolean contains(K key);

    public void delete(K key);

    public int size();

    public V get(K key);

    public void print();
}
