package model.value;

import model.type.IntType;
import model.type.ReferenceType;
import model.type.TypeInterface;

public class ReferenceValue implements ValueInterface{

    private final int headAddress;
    public static final int DEFAULT_HEAD_ADDRESS = 0;
    private final TypeInterface innerReferencedType;

    public ReferenceValue(TypeInterface innerReferencedType){
        this.headAddress = DEFAULT_HEAD_ADDRESS;
        this.innerReferencedType = innerReferencedType;
    }

    public ReferenceValue(int heapAddress, TypeInterface innerReferencedType){
        this.headAddress = heapAddress;
        this.innerReferencedType = innerReferencedType;
    }

    public int getHeapAddress(){
        return this.headAddress;
    }

    @Override
    public TypeInterface getType() {
        ///Returneaza mereu referenceType (returneaza tipul pointerului care e mereu referenceType)
        return new ReferenceType(this.innerReferencedType);
    }
}


