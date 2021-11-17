package model.ADT;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MyHeap<TKey, TValue> implements HeapInterface<TKey, TValue>{

    private Map<TKey, TValue> dictionary;
    int firstAvailablePosition = 1;

    public MyHeap(){
        this.dictionary = new HashMap<>();
    }

    @Override
    public void setFirstAvailablePosition(){
        // normally I would search for available positions that have previously been occupied and are now free due to the GC
        // but for now I'll just add them one after the other
        this.firstAvailablePosition = this.firstAvailablePosition + 1;

    }

    @Override
    public int getFirstAvailablePosition(){
        int positionCopy = this.firstAvailablePosition;
        this.setFirstAvailablePosition();
        return positionCopy;
    }

    @Override
    public int size() {
        return this.dictionary.size();
    }

    @Override
    public boolean containsKey(TKey tKey) {
        return this.dictionary.containsKey(tKey);
    }

    @Override
    public boolean containsValue(TValue tValue) {
        return this.dictionary.containsValue(tValue);
    }

    @Override
    public boolean isEmpty() {
        return this.dictionary.isEmpty();
    }

    @Override
    public void update(TKey tKey, TValue newValue) {
        this.dictionary.replace(tKey,newValue);
    }

    @Override
    public void insert(TKey tKey, TValue newValue) {
        this.dictionary.put(tKey, newValue);
    }

    @Override
    public void clear() {
        this.dictionary.clear();
    }

    @Override
    public TValue getValue(TKey tKey) {
        return this.dictionary.get(tKey);
    }

    @Override
    public TValue remove(TKey tKey) {
        return this.remove(tKey);
    }

    @Override
    public Collection<TValue> getAllValues() {
        return this.dictionary.values();
    }

    @Override
    public Collection<TKey> getAllKeys() {
        return this.dictionary.keySet();
    }

    @Override
    public Map<TKey, TValue> getAllPairs() {
        return this.dictionary;
    }
}
