package model.statement;

import model.ProgramState;

public class EmptyStatement implements StatementInterface{

    @Override
    public String toString(){
        return "Empty statement!";
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        return state;
    }
}
