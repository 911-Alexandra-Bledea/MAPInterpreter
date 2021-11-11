package model.statement;

import com.sun.jdi.Value;
import exception.InvalidTypeException;
import exception.UndefinedVariableException;
import model.ADT.DictionaryInterface;
import model.ProgramState;
import model.expression.ExpressionInterface;
import model.type.StringType;
import model.value.StringValue;
import model.value.ValueInterface;

import java.io.BufferedReader;

public class CloseReadFile implements StatementInterface{


    private ExpressionInterface filePath;
    private String variableName;

    @Override
    public ProgramState execute(ProgramState state) throws Exception{

        DictionaryInterface<String, ValueInterface> symTable = state.getSymbolTable();
        DictionaryInterface<StringValue, BufferedReader> fileTable = state.getFileTable();

        ValueInterface filePathValue = filePath.evaluate(symTable);

        if(!filePathValue.getType().equals(new StringType())){
            throw new InvalidTypeException("The File Path should be a string!\n");
        }

        if(!fileTable.containsKey((StringValue) filePathValue)){
            throw new UndefinedVariableException("The file path: " + filePathValue + "is not defined in the file table!\n");
        }

        BufferedReader fileBuffer = fileTable.getValue((StringValue) filePathValue);
        fileBuffer.close();
        fileTable.remove((StringValue) filePathValue);

        return state;
    }
    public String toString(){
        return "closeRead(" + this.filePath + ");\n";
    }

}
