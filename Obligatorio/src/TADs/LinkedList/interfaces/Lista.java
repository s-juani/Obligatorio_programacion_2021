package TADs.LinkedList.interfaces;

import TADs.LinkedList.Nodo;

public interface Lista<T> {

    void add(T value);
    void remove(int posicion);
    void removeValue(T value);
    T getFirst();
    T get(int posicion);
    int size();
    boolean find(T value);
    void addFirst(T value);
    void printList();
    void intercambiarElementos(int posicion1, int posicion2);
    void iteratorReset();
    T iteratorNext();

}