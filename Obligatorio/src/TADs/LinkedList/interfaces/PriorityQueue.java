package TADs.LinkedList.interfaces;

public interface PriorityQueue <Type> extends Queue<Type> {
    void enqueueWithPriority(Type element, int prioridad);
}
