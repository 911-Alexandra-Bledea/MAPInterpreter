package controller;

import exception.EmptyADTException;
import model.ADT.StackInterface;
import model.ProgramState;
import model.statement.StatementInterface;
import repository.Repository;
import repository.RepositoryInterface;

public class Controller implements ControllerInterface{

    public RepositoryInterface repository;

    public Controller(){
        repository = new Repository();
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
        StackInterface<StatementInterface> stack = currentProgramState.getExecutionStack();
        System.out.println(currentProgramState.toString());
        while(stack.size()>0){
            this.oneStepExecution(currentProgramState);
            System.out.println(currentProgramState.toString());
        }
        return currentProgramState;
    }

    @Override
    public void addProgramState(ProgramState newProgramState) {
        repository.addProgramState(newProgramState);
    }
}
