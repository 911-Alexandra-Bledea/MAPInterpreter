package model.statement;

import exception.InvalidOperatorException;
import exception.InvalidTypeException;
import model.ADT.Dictionary.DictionaryInterface;
import model.ADT.Heap.HeapInterface;
import model.ADT.Stack.StackInterface;
import model.ProgramState;
import model.expression.ExpressionInterface;
import model.type.BoolType;
import model.type.TypeInterface;
import model.value.BoolValue;
import model.value.ValueInterface;

import java.lang.reflect.Type;

public class IfStatement implements StatementInterface {
    private ExpressionInterface expression;
    private StatementInterface trueStatement;
    private StatementInterface falseStatement;

    public IfStatement(ExpressionInterface expression, StatementInterface trueStatement, StatementInterface falseStatement) {
        this.expression = expression;
        this.trueStatement = trueStatement;
        this.falseStatement = falseStatement;
    }

    @Override
    public String toString() {
        return "IF(" + expression.toString() + ")THEN(" + trueStatement.toString() + ")ELSE(" + falseStatement.toString() + "))";
    }

    @Override
    public StatementInterface deepCopy() {
        return new IfStatement(expression, trueStatement, falseStatement);
    }

    @Override
    public DictionaryInterface<String, TypeInterface> typeCheck(DictionaryInterface<String, TypeInterface> typeEnv) throws Exception {
        TypeInterface typeExpression = this.expression.typeCheck(typeEnv);
        if(typeExpression.equals(new BoolType())){
            this.trueStatement.typeCheck(typeEnv.copy());
            this.falseStatement.typeCheck(typeEnv.copy());
            return typeEnv;
        }
        else throw new InvalidTypeException("The condition of IF has not the type bool!\n");
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        StackInterface<StatementInterface> stack = state.getExecutionStack();
        DictionaryInterface<String, ValueInterface> symbolTable = state.getSymbolTable();
        HeapInterface<Integer, ValueInterface> heap = state.getHeap();

        ValueInterface conditionalExpressionVal = expression.evaluate(symbolTable, heap);
        if (((BoolValue) conditionalExpressionVal).getValue()) {
            stack.push(trueStatement);
        } else {
            stack.push(falseStatement);
        }

        return null;
    }
}