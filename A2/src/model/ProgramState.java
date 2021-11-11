package model;

import com.sun.jdi.Value;
import model.ADT.DictionaryInterface;
import model.ADT.ListInterface;
import model.ADT.StackInterface;
import model.statement.StatementInterface;
import model.value.StringValue;
import model.value.ValueInterface;

import javax.management.StringValueExp;
import javax.swing.plaf.nimbus.State;
import java.io.BufferedReader;
import java.nio.Buffer;

public class ProgramState {
    private StackInterface<StatementInterface> executionStack;
    private DictionaryInterface<String, ValueInterface> symbolTable;
    private DictionaryInterface<StringValue, BufferedReader> fileTable;
    private ListInterface<ValueInterface> output;
    private StatementInterface originalProgram;


    public ProgramState(StackInterface<StatementInterface> executionStack,
                        DictionaryInterface<String, ValueInterface> symbolTable,
                        ListInterface<ValueInterface> output,
                        DictionaryInterface<StringValue, BufferedReader> fileTable,
                        StatementInterface program) {
        this.executionStack = executionStack;
        this.symbolTable = symbolTable;
        this.output = output;
        this.fileTable = fileTable;
//        this.originalProgram = program;
//        this.executionStack.push(originalProgram);
        this.executionStack.push(program);
    }

    @Override
    public String toString(){
        String representation = "";
        representation += "\n------------------\n";
        representation += "Execution Stack: \n";
        representation += this.executionStack.toString();
        representation += "\nSymbol Table:\n";
        representation += this.symbolTable.toString();
        representation += "\nOutput Table:\n";
        representation += this.output.toString();
        representation += "\nFile Table:\n";
        representation += this.fileTable.toString();

        return representation;
    }

    public StackInterface<StatementInterface> getExecutionStack(){
        return executionStack;
    }

    public DictionaryInterface<StringValue, BufferedReader> getFileTable(){
        return this.fileTable;
    }

    public DictionaryInterface<String, ValueInterface> getSymbolTable(){
        return symbolTable;
    }

    public ListInterface<ValueInterface> getOutput(){
        return output;
    }

    public void setFileTable(DictionaryInterface<StringValue, BufferedReader> newFileTable){
        this.fileTable = newFileTable;
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
