package model.expression;

import model.ADT.Dictionary.DictionaryInterface;
import model.ADT.Heap.HeapInterface;
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
