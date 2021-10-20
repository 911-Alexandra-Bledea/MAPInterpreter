package model.value;

import model.type.BoolType;
import model.type.TypeInterface;

public class BoolValue implements ValueInterface{

    private boolean value;

    public BoolValue(boolean value) {
        this.value = value;
    }

    public boolean getValue(){
        return value;
    }

    public String toString(){
        return String.valueOf(value);
    }

    @Override
    public TypeInterface getType() {
        return new BoolType();
    }
}
