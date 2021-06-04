package TADs.arraylist;

public interface MyArrayList<K> {
    public void add(K value);

    public void delete(K value);

    public K get(int position);

    public int size();

    public void setElement(int position, K Value);

    public int getLenght();
}
