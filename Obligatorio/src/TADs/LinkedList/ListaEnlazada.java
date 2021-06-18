package TADs.LinkedList;

import TADs.LinkedList.exceptions.*;
import TADs.LinkedList.interfaces.*;


public class ListaEnlazada<T> implements Lista<T>, Stack<T>, Queue<T>, DoubleQueue<T>, PriorityQueue<T> {

    private int count;
    private Nodo<T> head;
    private Nodo<T> tail;
    public ListaEnlazada() {
        count = 0;
    }

    public Nodo<T> getHead(){return head;}
    public int size() {
        return count;
    }
    public void add(T value){
        if (count==0) {
            head = new Nodo<>(value);
            tail = this.head;
        }
        else {
            Nodo<T> nodoNuevo = new Nodo<>(value);
            tail.setNext(nodoNuevo);
            tail = nodoNuevo;
        }
        count+=1;
    }
    public void remove(int posicion) {
        if (posicion>=0 && posicion<count) {
            if (posicion>0) {
                Nodo<T> actual = head;
                int i = 0;
                while (i < posicion-1) {
                    actual = actual.getNext();
                    i++;
                }
                actual.setNext(actual.getNext().getNext());
                if (posicion == count-1) tail = actual;
            } else {
                head = head.getNext();
            }
            count -= 1;
        }
    }
    public void removeValue(T value){
        if (count!=0) {
            Nodo<T> nodoAnterior = head;
            while (nodoAnterior.getNext() != null) {
                if (nodoAnterior.getNext().getValue().equals(value)) {
                    Nodo<T> nodoEliminar = nodoAnterior.getNext();
                    nodoAnterior.setNext(nodoEliminar.getNext());
                    if (nodoAnterior.getNext()==null) tail = nodoAnterior;
                    count -= 1;
                }
                if (nodoAnterior.getNext() != null) nodoAnterior = nodoAnterior.getNext();
            }
            if (head.getValue().equals(value)) {
                head = head.getNext();
                count -= 1;
            }
        }
    }

    public T get(int posicion) {
        T getItem = null;
        if (posicion >= 0 && posicion < this.count) {
            Nodo<T> actual = this.head;
            int i = 0;
            while (i < posicion) {
                actual = actual.getNext();
                i++;
            }
            getItem = actual.getValue();
        }
        return getItem;
    }
    public boolean find(T value) {
        boolean found = false;
        Nodo<T> nodoActual = head;
        while (nodoActual != null) {
            if (nodoActual.getValue().equals(value)){
                found = true;
                break;
            }
            nodoActual=nodoActual.getNext();
        }
        return found;
    }
    public Nodo<T> findNode(T value) {
        Nodo<T> nodoActual = new Nodo<>(null);
        if (this.find(value)) {
            nodoActual = this.head;
            while (nodoActual != null) {
                if (nodoActual.getValue().equals(value)) {
                    break;
                }
                nodoActual = nodoActual.getNext();
            }
        }
        return nodoActual;
    }
    public Nodo<T> findPrevNode(T value){
        Nodo<T> nodoActual = new Nodo<>(null);
        if (this.find(value) && !head.getValue().equals(value)) {
            nodoActual = this.head;
            while (!nodoActual.getNext().getValue().equals(value)) {
                nodoActual = nodoActual.getNext();
            }
        }
        return nodoActual;
    }

    public void addFirst(T value) {
        Nodo<T> nuevo = new Nodo<>(value);
        nuevo.setNext(head);
        head=nuevo;
        count+=1;
    }
    public void addInPosicion(T value, int posicion){
        if (posicion>=0 && posicion<=count) {
            if (posicion==count) add(value);
            else if (posicion==0) addFirst(value);
            else {
                Nodo<T> actual = head;
                int i = 0;
                while (i < posicion-1) {
                    actual = actual.getNext();
                    i++;
                }
                Nodo<T> newNodo = new Nodo<>(value);
                newNodo.setNext(actual.getNext());
                actual.setNext(newNodo);
                if (posicion == count-1) tail = actual;
                count+=1;
            }
        }
    }
    public void addNodeInPosicion(Nodo<T> nodo, int posicion){
        if (posicion>=0 && posicion<=count) {
            if (count==0) {
                head=nodo;
                tail=nodo;
                nodo.setNext(null);
            }
            else if (posicion==count){
                tail.setNext(nodo);
                nodo.setNext(null);
                tail=nodo;
            }
            else if (posicion==0){
                nodo.setNext(head);
                head=nodo;
            }
            else {
                Nodo<T> actual = head;
                int i = 0;
                while (i < posicion-1) {
                    actual = actual.getNext();
                    i++;
                }
                nodo.setNext(actual.getNext());
                actual.setNext(nodo);
                if (posicion == count-1) tail = actual;
            }
            count+=1;
        }


    }
    /*public void agregarEnOrden(Object value){
        if (this.count==0) this.head = new Nodo(value);
        else {
            Nodo nodoFinal = this.head;
            while (nodoFinal.getNext()!=null) {
                nodoFinal = nodoFinal.getNext();
            }
            Nodo nodoNuevo = new Nodo(value);
            nodoFinal.setNext(nodoNuevo);
        }
        this.count+=1;
    }  */
    public void visualizar(List P){
        for (int i=0; i<P.size(); i++){
            System.out.println(this.get(P.get(i)).toString());
        }
    }
    public void intercambiar(T value, int direccion){
        if (find(value)){
            Nodo<T> nodoIntercambio = findNode(value);
            if (direccion==-1){
                Nodo<T> nodoAnterior = findPrevNode(value);
                if (nodoAnterior.getValue()!=null){
                    nodoAnterior.setNext(nodoIntercambio.getNext());
                    nodoIntercambio.setNext(nodoAnterior);
                    Nodo<T> nodoPrevio = findPrevNode(nodoAnterior.getValue());
                    if (nodoPrevio.getValue()!=null) nodoPrevio.setNext(nodoIntercambio);
                    else head = nodoIntercambio;
                    if (nodoAnterior.getNext()==null) tail=nodoAnterior;
                }
            }
            else if (direccion==1){
                if (nodoIntercambio.getNext()!=null){
                    Nodo<T> nodoSiguiente = nodoIntercambio.getNext();
                    nodoIntercambio.setNext(nodoSiguiente.getNext());
                    nodoSiguiente.setNext(nodoIntercambio);
                    Nodo<T> nodoPrevio = findPrevNode(nodoIntercambio.getValue());
                    if (nodoPrevio.getValue()!=null) nodoPrevio.setNext(nodoSiguiente);
                    else head = nodoSiguiente;
                    if (nodoIntercambio.getNext()==null) tail=nodoIntercambio;
                }
            }
        }
    }
    public ListaEnlazada<T> interseccion(ListaEnlazada<T> lista){
        ListaEnlazada<T> interseccion = new ListaEnlazada<>();
        for (int i=0; i<this.size(); i++) {
            if (lista.find(this.get(i))) interseccion.add(this.get(i));
        }
        return interseccion;
    }
    public ListaEnlazada<T> elementosNoRepetidos(ListaEnlazada<T> lista){
        ListaEnlazada<T> resultado = new ListaEnlazada<>();
        for (int j=0; j<lista.size(); j++) {
            if (!this.find(lista.get(j))) resultado.add(lista.get(j));
        }
        for (int i=0; i<this.size(); i++) {
            if (!lista.find(this.get(i))) resultado.add(this.get(i));
        }

        return resultado;
    }

    public void makeEmpty() {
        head = null;
        tail = null;
        count = 0;
    }
    public void printList(){
        StringBuilder resultado = new StringBuilder("[ ");
        Nodo<T> nodoActual = this.head;
        while (nodoActual != null && nodoActual.getValue()!=null) {
            resultado.append(nodoActual.getValue().toString()).append("  ");
            nodoActual=nodoActual.getNext();
        }
        resultado.append("]");
        System.out.println(resultado);
    }

    //stack
    public T pop() throws EmptyStackException {
        if (count==0) throw new EmptyStackException();
        T popObject = head.getValue();
        remove(0);
        return popObject;
    }
    public T top() {
        return head.getValue();
    }
    public void push(T element) {
        addFirst(element);
    }
    public void printStack(){
        printList();
    }

    //queue
    public void enqueue(T element){
        add(element);
    }
    public T dequeue() throws EmptyQueueException {
        if (count==0) throw new EmptyQueueException();
        T dequeueObject = head.getValue();
        remove(0);
        return dequeueObject;
    }
    public void printQueue(){
        printList();
    }
    public T first() throws EmptyQueueException {
        if (count==0) throw new EmptyQueueException();
        return head.getValue();
    }

    //doublequeue
    public void enqueueFirst(T element) {
        addFirst(element);
    }
    public void enqueueLast(T element) {
        add(element);
    }
    public T dequeueFirst() throws EmptyQueueException {
        if (count==0) throw new EmptyQueueException();
        T dequeueObject = head.getValue();
        remove(0);
        return dequeueObject;
    }
    public T dequeueLast() throws EmptyQueueException {
        if (count==0) throw new EmptyQueueException();
        T dequeueObject = head.getValue();
        remove(count-1);
        return dequeueObject;
    }

    //priorityQueue
    public void enqueueWithPriority(T element, int prioridad) {
        PriorityNode<T> newNodo = new PriorityNode<>(element,prioridad);
        Nodo<T> actual=head;
        int i=0;
        while(actual instanceof PriorityNode<T> Pnode){
            if (Pnode.getPriority()<prioridad) break;
            actual=actual.getNext();
            i++;
        }
        addNodeInPosicion(newNodo,i);
    }
    public void intercambiarElementos(int posicion1, int posicion2){
        if (posicion1<count && posicion2<count && posicion1!=posicion2) {
            T element1 = get(posicion1);
            T element2 = get(posicion2);
            remove(posicion1);
            addInPosicion(element2,posicion1);
            remove(posicion2);
            addInPosicion(element1,posicion2);
        }

    }

}
