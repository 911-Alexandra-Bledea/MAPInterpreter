package model.statement;

import model.ProgramState;

public class EmptyStatement implements StatementInterface{

    public EmptyStatement(){

    }

    @Override
    public String toString(){
        return "Empty statement!";
    }

    @Override
    public StatementInterface deepCopy() {
        return new EmptyStatement();
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        return null;
    }
}
