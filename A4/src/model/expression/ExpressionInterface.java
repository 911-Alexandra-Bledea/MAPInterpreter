package model.expression;

import model.ADT.DictionaryInterface;
import model.ADT.HeapInterface;
import model.ADT.MyHeap;
import model.value.ValueInterface;

public interface ExpressionInterface {
     ValueInterface evaluate(DictionaryInterface<String, ValueInterface> table,
                             HeapInterface<Integer, ValueInterface> heap) throws Exception;
     String toString();
}
