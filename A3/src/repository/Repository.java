package repository;

import exception.EmptyADTException;
import model.ProgramState;

import java.util.*;

public class Repository implements RepositoryInterface{
    private List<ProgramState> programStatesQueue;

    public Repository(){
        programStatesQueue = new ArrayList<>();
    }

    @Override
    public ProgramState getCurrentProgramState() throws Exception {
            int size = this.programStatesQueue.size();
            if(size == 0){
                throw new EmptyADTException("Empty list!\n");
            }
            return this.programStatesQueue.get(size-1);
    }

    @Override
    public void addProgramState(ProgramState newProgramState) {
        programStatesQueue.add(newProgramState);
    }
}


//    @Override
//    public int size() {
//        return 0;
//    }
//
//    @Override
//    public boolean isEmpty() {
//        return false;
//    }
//
//    @Override
//    public boolean contains(Object o) {
//        return false;
//    }
//
//    @Override
//    public Iterator<ProgramState> iterator() {
//        return null;
//    }
//
//    @Override
//    public Object[] toArray() {
//        return new Object[0];
//    }
//
//    @Override
//    public <T> T[] toArray(T[] a) {
//        return null;
//    }
//
//    @Override
//    public boolean add(ProgramState programState) {
//        return false;
//    }
//
//    @Override
//    public boolean remove(Object o) {
//        return false;
//    }
//
//    @Override
//    public boolean containsAll(Collection<?> c) {
//        return false;
//    }
//
//    @Override
//    public boolean addAll(Collection<? extends ProgramState> c) {
//        return false;
//    }
//
//    @Override
//    public boolean addAll(int index, Collection<? extends ProgramState> c) {
//        return false;
//    }
//
//    @Override
//    public boolean removeAll(Collection<?> c) {
//        return false;
//    }
//
//    @Override
//    public boolean retainAll(Collection<?> c) {
//        return false;
//    }
//
//    @Override
//    public void clear() {
//
//    }
//
//    @Override
//    public ProgramState get(int index) {
//        return null;
//    }
//
//    @Override
//    public ProgramState set(int index, ProgramState element) {
//        return null;
//    }
//
//    @Override
//    public void add(int index, ProgramState element) {
//
//    }
//
//    @Override
//    public ProgramState remove(int index) {
//        return null;
//    }
//
//    @Override
//    public int indexOf(Object o) {
//        return 0;
//    }
//
//    @Override
//    public int lastIndexOf(Object o) {
//        return 0;
//    }
//
//    @Override
//    public ListIterator<ProgramState> listIterator() {
//        return null;
//    }
//
//    @Override
//    public ListIterator<ProgramState> listIterator(int index) {
//        return null;
//    }
//
//    @Override
//    public List<ProgramState> subList(int fromIndex, int toIndex) {
//        return null;
//    }
//};