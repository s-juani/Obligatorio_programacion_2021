package TADs.LinkedList.interfaces;

public interface PriorityQueue <Type> extends Queue<Type> {
    public void enqueueWithPriority(Type element, int prioridad);
}
