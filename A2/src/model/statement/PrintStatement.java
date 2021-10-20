package model.statement;

import com.sun.jdi.Value;
import model.ADT.DictionaryInterface;
import model.ADT.ListInterface;
import model.ProgramState;
import model.expression.ExpressionInterface;
import model.value.ValueInterface;

public class PrintStatement implements StatementInterface{
    private ExpressionInterface expression;

    @Override
    public String toString(){
        return "print("+expression.toString()+")";
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        ListInterface<ValueInterface> output = state.getOutput();
        DictionaryInterface<String, ValueInterface> symbolTable = state.getSymbolTable();
        output.add(expression.evaluate(symbolTable));
        return state;
    }
}
