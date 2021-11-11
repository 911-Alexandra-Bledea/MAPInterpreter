package model.statement;

import exception.ExistingVariableException;
import exception.InvalidTypeException;
import model.ADT.DictionaryInterface;
import model.ADT.StackInterface;
import model.ProgramState;
import model.expression.ExpressionInterface;
import model.type.StringType;
import model.value.StringValue;
import model.value.ValueInterface;

import java.io.BufferedReader;
import java.io.FileReader;

public class OpenReadFileStatement implements StatementInterface {

    private ExpressionInterface filePath;

    public ProgramState execute(ProgramState state) throws Exception{

        DictionaryInterface<String, ValueInterface> symTable = state.getSymbolTable();
        DictionaryInterface<StringValue, BufferedReader> fileTable = state.getFileTable();
        ValueInterface filePathValue = this.filePath.evaluate(symTable);

        if(!filePathValue.getType().equals(new StringType())){
            throw new InvalidTypeException("File path should be a string!\n");
        }
        if(fileTable.containsKey((StringValue) filePathValue)){
            throw new ExistingVariableException("The filepath is already a key in FileTable!\n");
        }

        BufferedReader fileBuffer = new BufferedReader(new FileReader(filePathValue.toString()));

        fileTable.insert((StringValue) filePathValue, fileBuffer);

        return state;
    }


    public String toString(){
        return "openRead(" + this.filePath + ");\n";
    }

}