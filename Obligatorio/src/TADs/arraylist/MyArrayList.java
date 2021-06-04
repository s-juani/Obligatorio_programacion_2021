package TADs.arraylist;

public interface MyArrayList<K> {
    public void add(K value);

    public void delete(K value);

    public K get(int position);

    public int size();

}
