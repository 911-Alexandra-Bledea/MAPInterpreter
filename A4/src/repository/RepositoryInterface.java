package repository;

import exception.RepositoryException;
import model.ProgramState;

public interface RepositoryInterface {
    ProgramState getCurrentProgramState() throws Exception;
    void addProgramState(ProgramState newProgramState);
    void logPrgStateExec() throws Exception;
    void clearFile() throws Exception;
}
