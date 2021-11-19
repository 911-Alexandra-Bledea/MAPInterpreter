package model.statement;

import exception.InvalidOperatorException;
import exception.InvalidTypeException;
import model.ADT.Dictionary.DictionaryInterface;
import model.ADT.Heap.HeapInterface;
import model.ADT.Stack.StackInterface;
import model.ProgramState;
import model.expression.ExpressionInterface;
import model.type.BoolType;
import model.value.BoolValue;
import model.value.ValueInterface;

import javax.swing.plaf.nimbus.State;

public class WhileStatement implements  StatementInterface{

    private final ExpressionInterface whileCondition;
    private final StatementInterface whileBody;

    public WhileStatement(ExpressionInterface whileCondition, StatementInterface whileBody){
        this.whileCondition = whileCondition;
        this.whileBody = whileBody;
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        DictionaryInterface<String, ValueInterface> symTable = state.getSymbolTable();
        StackInterface<StatementInterface> executionStack = state.getExecutionStack();
        HeapInterface<Integer, ValueInterface> heap = state.getHeap();

        ValueInterface evaluatedExpression = this.whileCondition.evaluate(symTable, heap);
        if(!evaluatedExpression.getType().equals(new BoolType())){
            throw new InvalidTypeException("The type of the evaluatedExpression: " + evaluatedExpression + " is not a BoolType!\n");
        }

        if(!(evaluatedExpression instanceof BoolValue)){
            throw new InvalidOperatorException("The evaluatedExpression: " + evaluatedExpression + " is not a BoolValue!\n");
        }

        if(((BoolValue) evaluatedExpression).getValue()){
            ///First we push on the stack the current statement, in our case exactly the while statement
                executionStack.push(this);
                ///Afterwards we push to the stack the whileBody in order to execute it before the while statement executes again
                executionStack.push(this.whileBody);
        }

        return state;
    }

    @Override
    public StatementInterface deepCopy() {
        return new WhileStatement(this.whileCondition, this.whileBody);
    }

    @Override
    public String toString(){
        return "while("+whileCondition.toString()+"){ "+whileBody.toString()+" }";
    }

}
