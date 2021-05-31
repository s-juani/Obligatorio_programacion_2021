package TADs.arraylist;

public interface MyArrayList<K> {
    void add(K value);

    void delete(K value);

    K get(int position);

    int size();
}
