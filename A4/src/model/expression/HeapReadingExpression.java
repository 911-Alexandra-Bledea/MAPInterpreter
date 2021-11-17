package model.expression;

import exception.InvalidTypeException;
import exception.UndefinedVariableException;
import model.ADT.Dictionary.DictionaryInterface;
import model.ADT.Heap.HeapInterface;
import model.type.ReferenceType;
import model.value.ReferenceValue;
import model.value.ValueInterface;

public class HeapReadingExpression implements ExpressionInterface{

    private ExpressionInterface expression;

    public HeapReadingExpression(ExpressionInterface expression){
        this.expression = expression;
    }

    @Override
    public ValueInterface evaluate(DictionaryInterface<String, ValueInterface> table, HeapInterface<Integer, ValueInterface> heap) throws Exception {
        ValueInterface value = expression.evaluate(table, heap);

        if(!(value.getType() instanceof ReferenceType)){
            throw new InvalidTypeException("The evaluated expression: " + this.expression + " should be a ReferenceType\n");
        }

        int address = ((ReferenceValue) value).getHeapAddress();
        if(!heap.containsKey(address)){
            throw new UndefinedVariableException("The given key address: " + address + " is not defined in the heap\n");
        }

        return heap.getValue(address);
    }

    @Override
    public String toString(){
        return "readHeap("+expression.toString()+")";
    }

}
