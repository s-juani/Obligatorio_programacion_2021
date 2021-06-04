package TADs.arraylist;


public class MyArrayListImpl<K> implements MyArrayList<K> {
    private K[] list;
    private int size = 0;

    public MyArrayListImpl(int size) {
        list = (K[]) new Object[size];
    }

    @Override
    public void add(K value){
        if (size == list.length) {
            K[] temp = (K[]) new Object[(list.length)*2];
            for (int i = 0; i < list.length; i++) {
                temp[i] = list[i];
            }
            list = temp;
        }
        list[size] = value;
        size++;
    }

    @Override
    public void delete(K value){
        for (int i = 0; i < size; i++){
            if (list[i].equals(value)){
                list[i] = null;
            }
        }
    }

    @Override
    public K get(int position){
        return list[position];
    }

    @Override
    public int size(){
        return size;
    }
}
