package model.statement;


import model.ADT.Dictionary.DictionaryInterface;
import model.ProgramState;
import model.expression.ExpressionInterface;
import model.type.TypeInterface;

import java.lang.reflect.Type;

public interface StatementInterface {
    ProgramState execute(ProgramState state) throws Exception;
    String toString();
    StatementInterface deepCopy();
    DictionaryInterface<String, TypeInterface> typeCheck(DictionaryInterface<String, TypeInterface> typeEnv) throws Exception;
}
