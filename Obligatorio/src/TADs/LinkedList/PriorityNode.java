package TADs.LinkedList;

public class PriorityNode<T> extends Nodo<T> {

    public int priority;

    public PriorityNode(T value, int prioridad) {
        super(value);
        priority=prioridad;
    }

    public int getPriority() {
        return priority;
    }
    public void setPriority(int prioridad){
        priority=prioridad;
    }
}
