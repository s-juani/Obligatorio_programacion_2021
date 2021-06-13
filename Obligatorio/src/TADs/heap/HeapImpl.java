package TADs.heap;

import TADs.heap.exceptions.EmptyHeapException;
import TADs.heap.exceptions.HeapOverflowException;

public class HeapImpl<T extends Comparable<T>,V> implements Heap<T,V> {
    private HeapNode<T,V>[] array;
    private int count;
    private String tipo;    //max o min

    public HeapImpl(int size, String tipo) {
        array = (HeapNode<T,V>[]) new Object[size];
        count = 0;
        this.tipo = tipo;
    }
    public HeapImpl(String tipo) {
        array = (HeapNode<T,V>[]) new Object[15];
        count=0;
        this.tipo=tipo;
    }

    public void add(T key, V value) throws HeapOverflowException {
        if (array.length>count) {
            array[count] = new HeapNode<>(key, value);
            switch (tipo) {
                case "max":
                    addReorderMax(count);
                    break;
                case "min":
                    addReorderMin(count);
                    break;
            }
            count++;
        }
        else throw new HeapOverflowException();
    }
    public Object[] remove() throws EmptyHeapException, HeapOverflowException {
        if (count!=0) {
            HeapNode<T,V> elementToRemove = array[0];
            array[0] = array[count-1];
            switch (tipo) {
                case "max":
                    removeReorderMax(0);
                    break;
                case "min":
                    removeReorderMin(0);
                    break;
            }
            count--;
            return new Object[]{elementToRemove.getKey(),elementToRemove.getValue()};
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
            HeapNode<T,V> father = array[fatherPosition(position)];
            if (father.getKey().compareTo(array[position].getKey()) < 0) {
                array[fatherPosition(position)] = array[position];
                array[position] = father;
                addReorderMax(fatherPosition(position));
            }
        }
    }
    private void addReorderMin(int position) throws HeapOverflowException {
        if (position!=0) {
            HeapNode<T,V> father = array[fatherPosition(position)];
            if (father.getKey().compareTo(array[position].getKey()) > 0) {
                array[fatherPosition(position)] = array[position];
                array[position] = father;
                addReorderMin(fatherPosition(position));
            }
        }
    }
    private void removeReorderMax(int position) throws HeapOverflowException {
        if (leftPosition(position) < count-1) {
            HeapNode<T,V> left = array[leftPosition(position)];
            if (left.getKey().compareTo(array[position].getKey()) > 0) {
                array[leftPosition(position)] = array[position];
                array[position] = left;
                removeReorderMax(leftPosition(position));
            }
        } if (rightPosition(position) < count-1) {
            HeapNode<T,V> right = array[rightPosition(position)];
            if (right.getKey().compareTo(array[position].getKey()) > 0) {
                array[rightPosition(position)] = array[position];
                array[position] = right;
                removeReorderMax(rightPosition(position));
            }
        }
    }
    private void removeReorderMin(int position) throws HeapOverflowException {
        if (leftPosition(position) < count-1) {
            HeapNode<T,V> left = array[leftPosition(position)];
            if (left.getKey().compareTo(array[position].getKey()) < 0) {
                array[leftPosition(position)] = array[position];
                array[position] = left;
                removeReorderMin(leftPosition(position));
            }
        } if (rightPosition(position) < count-1) {
            HeapNode<T,V> right = array[rightPosition(position)];
            if (right.getKey().compareTo(array[position].getKey()) < 0) {
                array[rightPosition(position)] = array[position];
                array[position] = right;
                removeReorderMin(rightPosition(position));
            }
        }
    }

}
