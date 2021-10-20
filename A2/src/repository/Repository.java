package repository;

import model.ProgramState;

import java.util.ArrayDeque;

public class Repository implements RepositoryInterface{
    private ArrayDeque<ProgramState> programStatesQueue;

    public Repository(){
        programStatesQueue = new ArrayDeque<ProgramState>();
    }

    @Override
    public ProgramState getCurrentProgramState() throws Exception {
        return programStatesQueue.pop();
    }

    @Override
    public void addProgramState(ProgramState newProgramState) {
        programStatesQueue.add(newProgramState);
    }
}
