package model.statement.FilePack;

import exception.InvalidTypeException;
import exception.UndefinedVariableException;
import model.ADT.Dictionary.DictionaryInterface;
import model.ADT.Heap.HeapInterface;
import model.ProgramState;
import model.expression.ExpressionInterface;
import model.statement.StatementInterface;
import model.type.IntType;
import model.type.StringType;
import model.value.IntValue;
import model.value.StringValue;
import model.value.ValueInterface;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFileStatement implements StatementInterface {

    private ExpressionInterface filePath;
    private String variableName;

    public ReadFileStatement(ExpressionInterface filePath, String variableName){
        this.filePath = filePath;
        this.variableName = variableName;
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception{
        DictionaryInterface<String, ValueInterface> symTable = state.getSymbolTable();
        DictionaryInterface<StringValue, BufferedReader> fileTable = state.getFileTable();
        HeapInterface<Integer, ValueInterface> heap = state.getHeap();

        if(!symTable.containsKey(this.variableName)){
            throw new UndefinedVariableException("The variable name is not defined in the symbol table!\n");
        }

        if(!symTable.getValue(this.variableName).getType().equals(new IntType())){
            throw new InvalidTypeException(this.variableName + "is not an int!\n");
        }

        ValueInterface filePathValue = filePath.evaluate(symTable, heap);

        if(!filePathValue.getType().equals(new StringType())){
            throw new InvalidTypeException("The file path should be a string!\n");
        }

        if(!fileTable.containsKey((StringValue) filePathValue)){
            throw new UndefinedVariableException("The file path value is not defined in file table!\n");
        }
        try {
            BufferedReader fileBuffer = fileTable.getValue((StringValue) filePathValue);
            String line = fileBuffer.readLine();
            if (line == null)
            {
                /// default value for int = 0
                symTable.update(this.variableName, new IntValue());
            }
            else
            {
                try {
                    symTable.update(this.variableName, new IntValue(Integer.parseInt(line)));
                } catch (Exception ignored) {
                    throw new Exception("Cannot read value because EOF has been reached!\n");
                }
            }
        }
        catch(IOException ex){
            throw new IOException("An error has occurred while reading!\n");
        }
        return state;

    }

    @Override
    public String toString(){
        return "readFile(" + this.filePath + ");\n";
    }

    @Override
    public StatementInterface deepCopy() {
        return new ReadFileStatement(filePath, variableName);
    }

}
