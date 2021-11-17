package model.expression;

import com.sun.jdi.Value;
import model.ADT.DictionaryInterface;
import model.ADT.HeapInterface;
import model.ADT.MyHeap;
import model.value.ValueInterface;

public class ValueExpression implements ExpressionInterface{
    private ValueInterface value;

    public ValueExpression(ValueInterface value) {
        this.value = value;
    }

    @Override
    public ValueInterface evaluate(DictionaryInterface<String, ValueInterface> table, HeapInterface<Integer, ValueInterface> heap) throws Exception {
        return value;
    }

    public String toString(){
        return value.toString();
    }

}
