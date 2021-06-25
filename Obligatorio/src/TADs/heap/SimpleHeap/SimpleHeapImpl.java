package TADs.heap.SimpleHeap;
import TADs.heap.exceptions.*;

public class SimpleHeapImpl<T extends Comparable<T>> implements SimpleHeap<T> {
    private T[] array;
    private int count;
    private String tipo;    //max o min

    public SimpleHeapImpl(int size, String tipo) {
        array = (T[]) new Comparable[size];
        count = 0;
        this.tipo = tipo;
    }
    public SimpleHeapImpl(String tipo) {
        array = (T[]) new Comparable[15];
        count=0;
        this.tipo=tipo;
    }

    public void add(T element) throws HeapOverflowException {
        if (array.length>count) {
            array[count]=element;
            switch (tipo) {
                case "max" -> addReorderMax(count);
                case "min" -> addReorderMin(count);
            }
            count++;
        }
        else throw new HeapOverflowException();
    }
    public T remove() throws EmptyHeapException, HeapOverflowException {
        if (count!=0) {
            T element = array[0];
            array[0]=array[count-1];
            switch (tipo) {
                case "max" -> removeReorderMax(0);
                case "min" -> removeReorderMin(0);
            }
            count--;
            return element;
        } else throw new EmptyHeapException();
    }
    public int size() {
        return count;
    }
    public void print() {
        if (count!=0) {
            int k = 0;
            int i = 1;
            int L = 30;
            while (count > k){
                for (int m = 1; m < L; m++) System.out.print(" ");
                for (int j = 0; j < i && count > k; j++){
                    System.out.print(array[k].toString() + " ");
                    for (int m = 1; m < 2*L; m++) System.out.print(" ");
                    k++;
                }
                System.out.println("");
                i*=2;
                L/=2;
            }
        }
    }

    private int fatherPosition(int childPosition) throws HeapOverflowException {
        if (childPosition>0) return (childPosition-1)/2;
        else throw new HeapOverflowException();
    }
    private int leftPosition(int fatherPosition) throws HeapOverflowException {
        return 2*fatherPosition+1;
    }
    private int rightPosition(int fatherPosition) throws HeapOverflowException {
        return 2*fatherPosition+2;
    }

    private void addReorderMax(int position) throws HeapOverflowException {
        if (position!=0) {
            T father = array[fatherPosition(position)];
            if (father.compareTo(array[position]) < 0) {
                array[fatherPosition(position)] = array[position];
                array[position] = father;
                addReorderMax(fatherPosition(position));
            }
        }
    }
    private void addReorderMin(int position) throws HeapOverflowException {
        if (position!=0) {
            T father = array[fatherPosition(position)];
            if (father.compareTo(array[position]) > 0) {
                array[fatherPosition(position)] = array[position];
                array[position] = father;
                addReorderMin(fatherPosition(position));
            }
        }
    }
    private void removeReorderMax(int position) throws HeapOverflowException {
        if (leftPosition(position) < count-1) {
            T left = array[leftPosition(position)];
            if (left.compareTo(array[position]) > 0) {
                array[leftPosition(position)] = array[position];
                array[position] = left;
                removeReorderMax(leftPosition(position));
            }
        } if (rightPosition(position) < count-1) {
            T right = array[rightPosition(position)];
            if (right.compareTo(array[position]) > 0) {
                array[rightPosition(position)] = array[position];
                array[position] = right;
                removeReorderMax(rightPosition(position));
            }
        }
    }
    private void removeReorderMin(int position) throws HeapOverflowException {
        if (leftPosition(position) < count-1) {
            T left = array[leftPosition(position)];
            if (left.compareTo(array[position]) < 0) {
                array[leftPosition(position)] = array[position];
                array[position] = left;
                removeReorderMin(leftPosition(position));
            }
        } if (rightPosition(position) < count-1) {
            T right = array[rightPosition(position)];
            if (right.compareTo(array[position]) < 0) {
                array[rightPosition(position)] = array[position];
                array[position] = right;
                removeReorderMin(rightPosition(position));
            }
        }
    }

}
