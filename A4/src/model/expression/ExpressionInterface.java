package model.expression;

import model.ADT.Dictionary.DictionaryInterface;
import model.ADT.Heap.HeapInterface;
import model.value.ValueInterface;

public interface ExpressionInterface {
     ValueInterface evaluate(DictionaryInterface<String, ValueInterface> table,
                             HeapInterface<Integer, ValueInterface> heap) throws Exception;
     String toString();
}
