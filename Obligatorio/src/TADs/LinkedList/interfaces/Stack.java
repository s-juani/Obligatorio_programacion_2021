package TADs.LinkedList.interfaces;

import TADs.LinkedList.exceptions.*;

public interface Stack<Type> {
    Type pop() throws EmptyStackException, EmptyStackException;
    Type top() throws EmptyStackException;
    void push(Type element) throws OverflowStackException;
    int size();
    void makeEmpty();
    void printStack();
}
