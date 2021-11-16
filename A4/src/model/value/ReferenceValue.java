package model.value;

import model.type.ReferenceType;
import model.type.TypeInterface;

public class ReferenceValue implements ValueInterface{

    private final int headAddress;
    public static final int DEFAULT_HEAD_ADDRESS = 0;
    private final TypeInterface location;

    public ReferenceValue(TypeInterface location){
        this.headAddress = DEFAULT_HEAD_ADDRESS;
        this.location = location;
    }

    public ReferenceValue(int heapAddress, TypeInterface location){
        this.headAddress = heapAddress;
        this.location = location;
    }

    public int getHeapAddress(){
        return this.headAddress;
    }

    public TypeInterface getLocation(){
        return this.location;
    }

    @Override
    public TypeInterface getType() {
        return new ReferenceType(this.location);
    }
}
