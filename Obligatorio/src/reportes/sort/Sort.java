package reportes.sort;

import TADs.arraylist.MyArrayList;
import TADs.arraylist.MyArrayListImpl;

import java.util.ArrayList;

public class Sort<K extends Comparable<K>, V> {

    public Sort() {
    }

    public static void BubbleSort(Comparable[] lista) {
        if (lista != null && lista.length > 1) {
            Comparable temp;
            int count;
            do {
                count = 0;
                for (int i = 0; i < lista.length - 1; i++) {
                    if (lista[i].compareTo(lista[i + 1]) > 0) {
                        temp = lista[i];
                        lista[i] = lista[i + 1];
                        lista[i + 1] = temp;
                        count++;
                    }
                }
            } while (count != 0);
        }
    }

    public static void TranspositionSort(Comparable[] lista) {
        if (lista != null && lista.length > 1) {
            Comparable temp;
            int count;

            do {
                count = 0;
                for (int i = 0; i < lista.length - 1; i = i + 2) {
                    if (lista[i].compareTo(lista[i + 1]) > 0) {
                        temp = lista[i];
                        lista[i] = lista[i + 1];
                        lista[i + 1] = temp;
                        count++;
                    }
                }

                for (int i = 1; i < lista.length - 1; i = i + 2) {
                    if (lista[i].compareTo(lista[i + 1]) > 0) {
                        temp = lista[i];
                        lista[i] = lista[i + 1];
                        lista[i + 1] = temp;
                        count++;
                    }
                }
            } while (count != 0);

        }
    }

    public static void SelectSort(Comparable[] lista) {
        if (lista != null && lista.length > 1) {
            Comparable temp;
            int menor;
            int p = 0;
            do {
                menor = p;
                for (int i = p; i < lista.length; i++) {
                    if (lista[i].compareTo(lista[menor]) < 0) {
                        menor = i;
                    }
                }
                temp = lista[p];
                lista[p] = lista[menor];
                lista[menor] = temp;
                p++;
            } while (p < lista.length);
        }
    }

    public static void InsertSort(Comparable[] lista) {
        if (lista != null && lista.length > 1) {
            Comparable temp;
            int p;
            for (int j = 1; j < lista.length; j++) {
                p = j;
                while (p>0 && lista[p].compareTo(lista[p-1])<0){
                    temp = lista[p];
                    lista[p] = lista[p - 1];
                    lista[p - 1] = temp;
                    p--;
                }
            }
        }
    }

    /*public static void HeapSort(Comparable[] lista) throws HeapOverflowException, EmptyHeapException {
        if (lista != null && lista.length > 1) {
            Heap<Comparable, Object> heap = new HeapImpl<>(lista.length, "min");
            for (Comparable comparable : lista) {
                heap.add(comparable,null);
            }
            for (int i=0; i< lista.length; i++){
                lista[i]=heap.remove();
            }
        }
    }*/

    public static void MergeSort(Comparable[] lista){
        if (lista != null && lista.length>1) {
                int n = lista.length / 2;
                Comparable[] leftLista = new Comparable[n];
                System.arraycopy(lista, 0, leftLista, 0, leftLista.length);
                Comparable[] rightLista = new Comparable[lista.length - n];
                System.arraycopy(lista, n, rightLista, 0, rightLista.length);
                MergeSort(leftLista);
                MergeSort(rightLista);
                int R=0;
                int L=0;
                for (int i=0; i < lista.length; i++){
                    if (L<leftLista.length && R<rightLista.length){
                        if (leftLista[L].compareTo(rightLista[R])<0){
                            lista[i] = leftLista[L];
                            L++;
                        } else {
                            lista[i] = rightLista[R];
                            R++;
                        }
                    } else if (L<leftLista.length){
                        lista[i] = leftLista[L];
                        L++;
                    } else {
                        lista[i] = rightLista[R];
                        R++;
                    }
                }
        }
    }

    public static void SimpleQuickSort(Comparable[] lista){
        if (lista != null && lista.length>1) {
            if (lista.length>2) {
                int pivot = 0;
                if (lista[pivot].compareTo(lista[1]) > 0) {
                    if (lista[pivot].compareTo(lista[lista.length - 1]) > 0) {
                        if (lista[1].compareTo(lista[lista.length - 1]) > 0) pivot = 1;
                        else pivot = lista.length - 1;
                    }
                } else {
                    if (lista[pivot].compareTo(lista[lista.length - 1]) < 0) {
                        if (lista[1].compareTo(lista[lista.length - 1]) < 0) pivot = 1;
                        else pivot = lista.length - 1;
                    }
                }

                ArrayList<Comparable> leftList = new ArrayList<>();
                ArrayList<Comparable> rightList = new ArrayList<>();
                for (int j=0; j<lista.length; j++) {
                    if (j!=pivot) {
                        if (lista[j].compareTo(lista[pivot]) < 0) leftList.add(lista[j]);
                        else rightList.add(lista[j]);
                    }
                }

                Comparable[] left = new Comparable[leftList.size()];
                int i=0;
                for (Comparable element : leftList) {
                    left[i]=element;
                    i++;
                }

                Comparable[] right = new Comparable[rightList.size()];
                i=0;
                for (Comparable element : rightList) {
                    right[i]=element;
                    i++;
                }

                SimpleQuickSort(left);
                SimpleQuickSort(right);

                i=0;
                Comparable p = lista[pivot];
                for (Comparable element : left) {
                    lista[i]=element;
                    i++;
                }
                lista[i] = p;
                i++;
                for (Comparable element : right) {
                    lista[i]=element;
                    i++;
                }
            } else {
                if (lista[0].compareTo(lista[1])>0) {
                    Comparable temp = lista[1];
                    lista[1] = lista[0];
                    lista[0] = temp;
                }
            }

        }
    }

    public void QuickSort(MyArrayList<SortNode<K, V>> lista){

        if (lista != null && lista.size()>1) {
            if (lista.size()>2) {
                int pivot = 0;
                if (lista.get(pivot).getKey().compareTo(lista.get(1).getKey()) > 0) {
                    if (lista.get(pivot).getKey().compareTo(lista.get(lista.size() - 1).getKey()) > 0) {
                        if (lista.get(1).getKey().compareTo(lista.get(lista.size() - 1).getKey()) > 0) pivot = 1;
                        else pivot = lista.size() - 1;
                    }
                } else {
                    if (lista.get(pivot).getKey().compareTo(lista.get(lista.size() - 1).getKey()) < 0) {
                        if (lista.get(1).getKey().compareTo(lista.get(lista.size() - 1).getKey()) < 0) pivot = 1;
                        else pivot = lista.size() - 1;
                    }
                }

                MyArrayList<SortNode<K,V>> leftList = new MyArrayListImpl<>();
                MyArrayList<SortNode<K,V>> rightList = new MyArrayListImpl<>();
                for (int j=0; j<lista.size(); j++) {
                    if (j!=pivot) {
                        if (lista.get(j).getKey().compareTo(lista.get(pivot).getKey()) < 0) leftList.add(lista.get(j));
                        else rightList.add(lista.get(j));
                    }
                }

                QuickSort(leftList);
                QuickSort(rightList);

                int i=0;
                SortNode<K,V> p = lista.get(pivot);
                for (int t=0; t<leftList.size(); t++) {
                    lista.setElement(i,leftList.get(t));
                    i++;
                }
                lista.setElement(i,p);
                i++;
                for (int t=0; t<rightList.size(); t++) {
                    lista.setElement(i,rightList.get(t));
                    i++;
                }
            } else {
                if (lista.get(0).getKey().compareTo(lista.get(1).getKey())>0) {
                    SortNode<K,V> temp = lista.get(1);
                    lista.setElement(1,lista.get(0));
                    lista.setElement(0,temp);
                }
            }

        }
    }
}

