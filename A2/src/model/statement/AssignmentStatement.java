package model.statement;

import exception.InvalidTypeException;
import exception.UndefinedVariableException;
import model.ADT.DictionaryInterface;
import model.ProgramState;
import model.expression.ExpressionInterface;
import model.type.TypeInterface;
import model.value.ValueInterface;

import java.lang.reflect.Type;

public class AssignmentStatement implements StatementInterface{

    private String id;
    private ExpressionInterface expression;

    public AssignmentStatement(String id, ExpressionInterface expression) {
        this.id = id;
        this.expression = expression;
    }

    @Override
    public String toString(){
        return id+"="+expression.toString();
    }

    @Override
    public StatementInterface deepCopy() {
        return new AssignmentStatement(id, expression);
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        DictionaryInterface<String, ValueInterface> symbolTable = state.getSymbolTable();
        if(symbolTable.containsKey(id)){
            ValueInterface newExpressionValue = expression.evaluate(symbolTable);
            TypeInterface newExpressionType = newExpressionValue.getType();
            TypeInterface typeID = symbolTable.getValue(id).getType();
            if(newExpressionType.equals(typeID)){
                symbolTable.update(id, newExpressionValue);
            }
            else
            {
                throw new InvalidTypeException("Type of " + id + " doesn't match the expression's type!");
            }
        }
        else{
            throw new UndefinedVariableException("Variable "+id+"is not defined!");
        }
        return state;
    }
}
