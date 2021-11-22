package controller;

import model.ProgramState;

public interface ControllerInterface {
    ProgramState oneStepExecution(ProgramState state) throws Exception;
    ProgramState fullProgramExecution() throws Exception;
    void addProgramState(ProgramState newProgramState);
}
