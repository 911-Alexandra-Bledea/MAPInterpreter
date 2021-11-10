package model.ADT;

import java.util.Stack;

public class MyStack<TElem> implements StackInterface<TElem>{

    private Stack<TElem> stack;

    public MyStack(){
        stack = new Stack<TElem>();
    }

    @Override
    public String toString(){
        String representation = "";
//        for(TElem current: this.stack){
//            representation += current.toString() + "; ";
//        }
        representation = stack.toString();
        return representation;
    }

    @Override
    public TElem pop() {
        return stack.pop();
    }

    @Override
    public int size() {
        return stack.size();
    }

    @Override
    public void clear() {
        stack.clear();
    }

    @Override
    public void push(TElem newElem) {
        stack.push(newElem);
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }
}
