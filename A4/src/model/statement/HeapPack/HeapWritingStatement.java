package model.statement.HeapPack;

import exception.InvalidTypeException;
import exception.UndefinedVariableException;
import model.ADT.Dictionary.DictionaryInterface;
import model.ADT.Heap.HeapInterface;
import model.ProgramState;
import model.expression.ExpressionInterface;
import model.statement.StatementInterface;
import model.type.ReferenceType;
import model.type.TypeInterface;
import model.value.ReferenceValue;
import model.value.ValueInterface;

public class HeapWritingStatement implements StatementInterface {

    String variableName;
    ExpressionInterface expression;

    public HeapWritingStatement(String variableName, ExpressionInterface expression){
        this.variableName = variableName;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        DictionaryInterface<String, ValueInterface> symTable = state.getSymbolTable();
        HeapInterface<Integer, ValueInterface> heap = state.getHeap();
        if(!symTable.containsKey(this.variableName)){
            throw new UndefinedVariableException("The key address: " + this.variableName + " is not defined in the symbolTable!\n");
        }

        ValueInterface value = symTable.getValue(this.variableName);
        if(!(value.getType() instanceof ReferenceType)){
            throw new InvalidTypeException("The value: " + value + " is not a reference type!\n");
        }

        ReferenceValue refValue = (ReferenceValue) value;
        int address = refValue.getHeapAddress();

        if(!heap.containsKey(address)){
            throw new UndefinedVariableException("The given key address: " + address + " is not defined in the heap\n");
        }

        ValueInterface expressionValue = this.expression.evaluate(symTable, heap);
        ReferenceValue refVal = ((ReferenceValue) symTable.getValue(this.variableName));
        TypeInterface innerReferencedType = ((ReferenceType)refVal.getType()).getInner();
        if(!expressionValue.getType().equals(innerReferencedType)){
            throw new InvalidTypeException("There exists a mismatch between the evaluated expression's type: " + expressionValue.getType() + " and the inner referenced type " + innerReferencedType);
        }
        heap.update(address, expressionValue);

        return state;
    }

    @Override
    public String toString(){
        String representation = "";
        representation += ("writeHeap(" + this.variableName + ", " + this.expression.toString() + ");\n");
        return representation;
    }

    @Override
    public StatementInterface deepCopy() {
        return new HeapWritingStatement(this.variableName, this.expression);
    }
}
