package model.type;

import model.value.ReferenceValue;
import model.value.ValueInterface;

public class ReferenceType implements TypeInterface{

    TypeInterface inner;

    public ReferenceType(TypeInterface inner){
        this.inner = inner;
    }

    public TypeInterface getInner(){
        return this.inner;
    }

    public boolean equals(Object another){
        if(another instanceof ReferenceType)
            return this.inner.equals(((ReferenceType) another).getInner());
        else
            return false;
    }

    public String toString(){
        return "Ref("+this.inner.toString()+")";
    }

    @Override
    public ValueInterface defaultValue() {
        return new ReferenceValue(this.inner);
    }
}
