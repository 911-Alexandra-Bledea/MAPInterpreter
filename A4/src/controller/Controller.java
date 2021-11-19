package controller;

import com.sun.jdi.Value;
import exception.EmptyADTException;
import model.ADT.Dictionary.DictionaryInterface;
import model.ADT.Heap.HeapInterface;
import model.ADT.Stack.StackInterface;
import model.ProgramState;
import model.statement.StatementInterface;
import model.value.ReferenceValue;
import model.value.ValueInterface;
import repository.RepositoryInterface;

import java.util.Collection;
import java.util.Dictionary;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    private Map<Integer, ValueInterface> unsafeGarbageCollector(List<Integer> symTableAddresses, Map<Integer, ValueInterface> heap){
        return heap.entrySet().stream()
                .filter(e->symTableAddresses.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private List<Integer> getAddressesFromSymTable(Collection<ValueInterface> symTableValues){
        ///We get a list with all the addreses of the referenceValues from the symbol table
        return  symTableValues.stream()
                .filter(v->v instanceof ReferenceValue)
                .map(v->{ReferenceValue v1 = (ReferenceValue) v; return v1.getHeapAddress();})
                .collect(Collectors.toList());
    }

    private List<Integer> getAddressesFromHeap(Collection<ValueInterface> heap){
        ///We get a list with all the addreses of the referanceValues from the heap
        return heap.stream()
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
        return heap.getAllPairs().entrySet().stream()
                .filter(elem-> symTableAddresses.contains(elem.getKey()) || heapReferencedAddressed.contains(elem.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }


    @Override
    public ProgramState fullProgramExecution() throws Exception {
        ProgramState currentProgramState = repository.getCurrentProgramState();
        this.repository.clearFile();
        StackInterface<StatementInterface> stack = currentProgramState.getExecutionStack();
        HeapInterface<Integer, ValueInterface> heap = currentProgramState.getHeap();
        DictionaryInterface<String ,ValueInterface> symTable = currentProgramState.getSymbolTable();
        this.repository.logPrgStateExec();
        while(stack.size()>0){
            this.oneStepExecution(currentProgramState);
            ///We set the new content of the heap
            List<Integer> symTableAddresses = this.getAddressesFromSymTable(symTable.getContent().values());
            List<Integer> heapReferencedAddresses = this.getAddressesFromHeap(heap.getContent().values());
            heap.setContent(this.safeGarbageCollector(symTableAddresses, heapReferencedAddresses, currentProgramState));
            this.repository.logPrgStateExec();
        }
        return currentProgramState;
    }

    @Override
    public void addProgramState(ProgramState newProgramState) {
        repository.addProgramState(newProgramState);
    }
}
