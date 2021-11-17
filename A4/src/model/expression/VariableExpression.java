package model.expression;

import exception.UndefinedVariableException;
import model.ADT.DictionaryInterface;
import model.ADT.HeapInterface;
import model.ADT.MyHeap;
import model.value.ValueInterface;

public class VariableExpression implements ExpressionInterface{

    private String id;

    public VariableExpression(String id) {
        this.id = id;
    }

    @Override
    public ValueInterface evaluate(DictionaryInterface<String, ValueInterface> table, HeapInterface<Integer, ValueInterface> heap) throws Exception {
        if (!table.containsKey(id)) {
            throw new UndefinedVariableException(id + "is not defined as a variable!");
        }
        return table.getValue(id);
    }

    public String toString(){
        return id;
    }

}
