package controller;

import model.ProgramState;

import java.util.List;

public interface ControllerInterface {
    ProgramState oneStepExecution(ProgramState state) throws Exception;
    ProgramState fullProgramExecution() throws Exception;
    void addProgramState(ProgramState newProgramState);
    List<ProgramState> removeCompletedProgram(List<ProgramState> inProgramList);
}
