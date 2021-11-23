package controller;

import com.sun.jdi.Value;
import exception.EmptyADTException;
import exception.RepositoryException;
import model.ADT.Dictionary.DictionaryInterface;
import model.ADT.Heap.HeapInterface;
import model.ADT.Stack.StackInterface;
import model.ProgramState;
import model.statement.StatementInterface;
import model.value.ReferenceValue;
import model.value.ValueInterface;
import repository.RepositoryInterface;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Controller implements ControllerInterface{

    private RepositoryInterface repository;
    private ExecutorService executor;

    public Controller(RepositoryInterface repository){
        this.repository = repository;
    }

    private List<Integer> getAddressesFromSymTable(Collection<ValueInterface> symTableValues){
        ///We get a list with all the addreses of the referenceValues from the symbol table
        return  symTableValues.stream()
                .filter(v->v instanceof ReferenceValue)
                .map(v->{ReferenceValue v1 = (ReferenceValue) v; return v1.getHeapAddress();})
                .collect(Collectors.toList());
    }

    private List<Integer> getAddressesFromHeap(Map<Integer, ValueInterface> heap){
        ///We get a list with all the addreses of the referanceValues from the heap
        return heap.values().stream()
                .filter(v->v instanceof ReferenceValue)
                .map(v->{ReferenceValue v1 = (ReferenceValue) v; return v1.getHeapAddress();})
                .collect(Collectors.toList());
    }

    private Map<Integer, ValueInterface> safeGarbageCollector(List<Integer> symTableAddresses,
                                                              List<Integer> heapReferencedAddressed,
                                                              ProgramState currProgram){
        ///We get the heap and we transform it into a Set in order to apply stream on it
        ///We filter the heap in the following way. We keep all the elements which have their addresss in the symTable or in the heap
        ///Otherwise we don't include them

        HeapInterface<Integer,ValueInterface> heap = currProgram.getHeap();
        return heap.getContent().entrySet().stream()
                .filter(elem-> symTableAddresses.contains(elem.getKey()) || heapReferencedAddressed.contains(elem.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }


    @Override
    public void fullProgramExecution() throws Exception {

        executor = Executors.newFixedThreadPool(2);
        List<ProgramState> programs = removeCompletedProgram(this.repository.getProgramList()); //nu are niciun sens
        while(programs.size() > 0){
            Collection<ValueInterface> addresses = programs.stream().
                    flatMap(program -> program.getSymbolTable().getContent().values().stream())
                    .collect(Collectors.toList());

            //Collection<ValueInterface> heapAddresses = programs.get(0).getHeap().getContent().values();

            programs.get(0).getHeap().setContent(this.safeGarbageCollector(this.getAddressesFromSymTable(addresses),
                    this.getAddressesFromHeap(programs.get(0).getHeap().getContent()), programs.get(0)));

            this.oneStepForAllPrograms(programs);
            programs = removeCompletedProgram(this.repository.getProgramList());
        }
        executor.shutdown();
        this.repository.setProgramList(programs);
    }

    @Override
    public void addProgramState(ProgramState newProgramState) {
        repository.addProgramState(newProgramState);
    }

    @Override
    public List<ProgramState> removeCompletedProgram(List<ProgramState> inProgramList) {
        return inProgramList.stream()
                .filter(ProgramState::isNotCompleted)
                .collect(Collectors.toList());
    }

    @Override
    public void oneStepForAllPrograms(List<ProgramState> programStatesList) throws InterruptedException {
        programStatesList.forEach(p -> {

            try {
                this.repository.logPrgStateExec(p);
            } catch (RepositoryException e) {
                e.printStackTrace();
            }

        });

        /// We create a list with the oneStepExecution function for each programState
        List<Callable<ProgramState>> callList = programStatesList.stream()
                .map((ProgramState p) -> (Callable<ProgramState>) (p::oneStepExecution))
                .collect(Collectors.toList());

        /// It executes the oneStepExecution function for each programState from the list above
        /// And it takes the return values and place them in a list
        List<ProgramState> newProgramsList = executor.invokeAll(callList).stream()
                .map(future ->
                {
                    try {
                        return future.get();
                    } catch (ExecutionException | InterruptedException ex) {
                        System.out.println(ex.toString());
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        programStatesList.addAll(newProgramsList);

        programStatesList.forEach(p -> {
            try {
                this.repository.logPrgStateExec(p);
            } catch (RepositoryException e) {
                e.printStackTrace();
                ;
            }
        });

        this.repository.setProgramList(programStatesList);
    }
}
//
//    @Override
//    public ProgramState oneStepExecution(ProgramState state) throws Exception {
//        StackInterface<StatementInterface> stack = state.getExecutionStack();
//        if(stack.isEmpty()) throw new EmptyADTException("ProgramState stack is empty!");
//        StatementInterface currentStatement = stack.pop();
//        return currentStatement.execute(state);
//    }
//
//    private Map<Integer, ValueInterface> unsafeGarbageCollector(List<Integer> symTableAddresses, Map<Integer, ValueInterface> heap){
//        return heap.entrySet().stream()
//                .filter(e->symTableAddresses.contains(e.getKey()))
//                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
//    }