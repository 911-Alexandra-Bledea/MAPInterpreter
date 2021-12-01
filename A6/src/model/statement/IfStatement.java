package model.statement;

import exception.InvalidTypeException;
import model.ADT.Dictionary.DictionaryInterface;
import model.ADT.Heap.HeapInterface;
import model.ADT.Stack.StackInterface;
import model.ProgramState;
import model.expression.ExpressionInterface;
import model.type.BoolType;
import model.value.BoolValue;
import model.value.ValueInterface;

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
    public ProgramState execute(ProgramState state) throws Exception {
        StackInterface<StatementInterface> stack = state.getExecutionStack();
        DictionaryInterface<String, ValueInterface> symbolTable = state.getSymbolTable();
        HeapInterface<Integer, ValueInterface> heap = state.getHeap();

        ValueInterface conditionalExpressionVal = expression.evaluate(symbolTable, heap);
        if (conditionalExpressionVal.getType().equals(new BoolType())) {
            if (((BoolValue) conditionalExpressionVal).getValue()) {
                stack.push(trueStatement);
            } else {
                stack.push(falseStatement);
            }
        } else {
            throw new InvalidTypeException("Conditional xpression is not a boolean!");
        }
        return null;
    }
}