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
        if(((BoolValue) evaluatedExpression).getValue()){
            ///First we push on the stack the current statement, in our case exactly the while statement
                executionStack.push(this);
                ///Afterwards we push to the stack the whileBody in order to execute it before the while statement executes again
                executionStack.push(this.whileBody);
        }

        return null;
    }

    @Override
    public StatementInterface deepCopy() {
        return new WhileStatement(this.whileCondition, this.whileBody);
    }

    @Override
    public DictionaryInterface<String, TypeInterface> typeCheck(DictionaryInterface<String, TypeInterface> typeEnv) throws Exception {
       TypeInterface typeExpression = this.whileCondition.typeCheck(typeEnv);
       if(typeExpression.equals(new BoolType())){
           this.whileBody.typeCheck(typeEnv.copy());
           return typeEnv;
       }
       else throw new InvalidTypeException("WhileStatement: Conditional expression is not boolean!\n");
    }

    @Override
    public String toString(){
        return "while("+whileCondition.toString()+"){ "+whileBody.toString()+" }";
    }

}
