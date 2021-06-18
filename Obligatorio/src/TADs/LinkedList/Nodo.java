package TADs.LinkedList;

public class Nodo<T> {
    public T value;

    private Nodo<T> next;

    public Nodo<T> getNext(){
        return next;
    }
    public T getValue(){
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
    public void setNext(Nodo<T> next) {
        this.next = next;
    }

    public Nodo(T value){
        this.value=value;
        next=null;
    }
}
