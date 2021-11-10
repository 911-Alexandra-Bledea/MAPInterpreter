package model.value;

import model.type.IntType;
import model.type.TypeInterface;

public class IntValue implements ValueInterface{

    private int value;

    public IntValue(int value) {
        this.value = value;
    }

    public int getValue(){ return value;}

    public String toString(){
        return String.valueOf(value);
    }

    @Override
    public TypeInterface getType() {
        return new IntType();
    }
}
