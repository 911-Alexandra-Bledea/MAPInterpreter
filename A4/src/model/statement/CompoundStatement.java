package model.statement;

import model.ADT.StackInterface;
import model.ProgramState;

public class CompoundStatement implements StatementInterface{
    private StatementInterface firstStatement;
    private StatementInterface secondStatement;

    public CompoundStatement(StatementInterface firstStatement, StatementInterface secondStatement) {
        this.firstStatement = firstStatement;
        this.secondStatement = secondStatement;
    }

    @Override
    public String toString(){
        return "("+firstStatement.toString()+";"+secondStatement.toString()+")";
    }

    @Override
    public StatementInterface deepCopy() {
        return new CompoundStatement(firstStatement, secondStatement);
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        StackInterface<StatementInterface> stack = state.getExecutionStack();
        stack.push(secondStatement);
        stack.push(firstStatement);
        return state;
    }
}
