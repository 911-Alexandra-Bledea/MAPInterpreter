package model.ADT;

import java.util.Collection;
import java.util.Map;

public class MyHeap<TKey, TValue> extends MyDictionary<TKey, TValue>{

    int firstAvailablePosition = 1;

    public MyHeap(){
        super();
    }

    public void setFirstAvailablePosition(){
        // normally I would search for available positions that have previously been occupied and are now free due to the GC
        // but for now I'll just add them one after the other
        this.firstAvailablePosition = this.firstAvailablePosition + 1;
    }

    public int getFirstAvailablePosition(){
        int positionCopy = this.firstAvailablePosition;
        this.setFirstAvailablePosition();
        return positionCopy;
    }

}
