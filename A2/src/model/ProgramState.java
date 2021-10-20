package model;

import com.sun.jdi.Value;
import model.ADT.DictionaryInterface;
import model.ADT.ListInterface;
import model.ADT.StackInterface;
import model.statement.StatementInterface;
import model.value.ValueInterface;

import javax.swing.plaf.nimbus.State;

public class ProgramState {
    private StackInterface<StatementInterface> executionStack;
    private DictionaryInterface<String, ValueInterface> symbolTable;
    private ListInterface<ValueInterface> output;
//    private StatementInterface originalProgram;


    public ProgramState(StackInterface<StatementInterface> executionStack, DictionaryInterface<String, ValueInterface> symbolTable, ListInterface<ValueInterface> output) {
        this.executionStack = executionStack;
        this.symbolTable = symbolTable;
        this.output = output;
//        this.originalProgram = originalProgram.deepCopy();
//        this.executionStack.push(originalProgram);
    }

    @Override
    public String toString(){
        return executionStack.toString() + "\n" + symbolTable.toString() + "\n" + output.toString() + "\n";
    }

    public StackInterface<StatementInterface> getExecutionStack(){
        return executionStack;
    }

    public DictionaryInterface<String, ValueInterface> getSymbolTable(){
        return symbolTable;
    }

    public ListInterface<ValueInterface> getOutput(){
        return output;
    }

    public void setExecutionStack(StackInterface<StatementInterface> stack){
        executionStack = stack;
    }

    public void setSymbolTable(DictionaryInterface<String, ValueInterface> table){
        symbolTable = table;
    }

    public void setOutput(ListInterface<ValueInterface> list){
        output = list;
    }

}
