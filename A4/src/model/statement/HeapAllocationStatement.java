package model.statement;

import com.sun.jdi.Value;
import exception.InvalidTypeException;
import exception.UndefinedVariableException;
import model.ADT.DictionaryInterface;
import model.ADT.HeapInterface;
import model.ADT.MyHeap;
import model.ProgramState;
import model.expression.ExpressionInterface;
import model.type.ReferenceType;
import model.type.TypeInterface;
import model.value.ReferenceValue;
import model.value.ValueInterface;

public class HeapAllocationStatement implements StatementInterface{

    private final ExpressionInterface expression;
    private final String variableName;

    public HeapAllocationStatement(String variableName, ExpressionInterface expression){
        this.variableName = variableName;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        DictionaryInterface<String, ValueInterface> symTable = state.getSymbolTable();
        HeapInterface<Integer, ValueInterface> heap = state.getHeap();

        if(!symTable.containsKey(this.variableName)){
            throw new UndefinedVariableException(this.variableName + " is not defined in the symbol table!\n");
        }

        if(!(symTable.getValue(this.variableName).getType() instanceof ReferenceType)){
            throw new InvalidTypeException("Associated type to: "+this.variableName + " should be of ReferenceType!\n");
        }

        ValueInterface expValue = this.expression.evaluate(symTable, heap);
        ReferenceValue refVal = ((ReferenceValue) symTable.getValue(this.variableName));
        TypeInterface innerType = ((ReferenceType) refVal.getType()).getInner();
        if(!expValue.getType().equals(innerType)){
            throw new InvalidTypeException("There exists a mismatch between the expression's type: " + expValue.getType() + " and the inner type of the reference value " + innerType);
        }

        int copyAddress = heap.getFirstAvailablePosition();
        heap.insert(copyAddress,expValue);
        symTable.update(this.variableName, new ReferenceValue(copyAddress, innerType));

        return state;
    }

    @Override
    public StatementInterface deepCopy()
    {
        return new HeapAllocationStatement(this.variableName, this.expression);
    }
}