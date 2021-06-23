package TADs.arraylist;


public class MyArrayListImpl<K> implements MyArrayList<K> {
    private K[] list;
    private int size = 0;

    public MyArrayListImpl() {
        list = (K[]) new Object[10];
    }

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

    @Override
    public void setElement(int position, K value){
        if (position >= 0 && position < list.length){
            list[position] = value;
        }
    }

    @Override
    public int getLenght(){
        return list.length;
    }

    @Override
    public void print(){
        for (int i=0; i<size;i++){
            System.out.println(list[i]);
        }
    }

    @Override
    public boolean contains(K value){
        for (K k : list) {
            if (k == value) return true;
        }
        return false;
    }
}
