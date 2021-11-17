package controller;

import exception.EmptyADTException;
import model.ADT.Stack.StackInterface;
import model.ProgramState;
import model.statement.StatementInterface;
import repository.RepositoryInterface;

public class Controller implements ControllerInterface{

    public RepositoryInterface repository;

    public Controller(RepositoryInterface repository){
        this.repository = repository;
    }

    @Override
    public ProgramState oneStepExecution(ProgramState state) throws Exception {
        StackInterface<StatementInterface> stack = state.getExecutionStack();
        if(stack.isEmpty()) throw new EmptyADTException("ProgramState stack is empty!");
        StatementInterface currentStatement = stack.pop();
        return currentStatement.execute(state);
    }

    @Override
    public ProgramState fullProgramExecution() throws Exception {
        ProgramState currentProgramState = repository.getCurrentProgramState();
        this.repository.clearFile();
        StackInterface<StatementInterface> stack = currentProgramState.getExecutionStack();
        this.repository.logPrgStateExec();
        while(stack.size()>0){
            this.oneStepExecution(currentProgramState);
            this.repository.logPrgStateExec();
        }
        return currentProgramState;
    }

    @Override
    public void addProgramState(ProgramState newProgramState) {
        repository.addProgramState(newProgramState);
    }
}
