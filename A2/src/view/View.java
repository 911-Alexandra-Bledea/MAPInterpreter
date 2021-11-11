package view;

import controller.Controller;
import controller.ControllerInterface;
import model.ADT.*;
import model.ProgramState;
import model.expression.ArithmeticExpression;
import model.expression.ValueExpression;
import model.expression.VariableExpression;
import model.statement.*;
import model.type.BoolType;
import model.type.IntType;
import model.value.BoolValue;
import model.value.IntValue;
import model.value.StringValue;
import model.value.ValueInterface;
import repository.Repository;
import repository.RepositoryInterface;

import java.io.BufferedReader;


public class View {

    private final String FOLDER_PATH = "C:\\Users\\night\\Desktop\\Facultate An 2\\Semestrul 1\\Advanced Programming Methods\\MAPInterpreter\\A2";

    public ControllerInterface getFirstExample() {
        StatementInterface programExample1 = new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                new CompoundStatement(new AssignmentStatement("v", new ValueExpression(new IntValue(2))), new PrintStatement(new VariableExpression("v"))));

        StackInterface<StatementInterface> executionStack = new MyStack<StatementInterface>();
        DictionaryInterface<String, ValueInterface> symTable = new MyDictionary<String, ValueInterface>();
        ListInterface<ValueInterface> output = new MyList<ValueInterface>();
        DictionaryInterface<StringValue, BufferedReader> fileTable = new MyDictionary<StringValue, BufferedReader>();
        ProgramState currentProgramState = new ProgramState(executionStack, symTable, output, fileTable, programExample1);
        RepositoryInterface repo = new Repository(this.FOLDER_PATH + "\\log1.in");
        ControllerInterface controller = new Controller(repo);

        controller.addProgramState(currentProgramState);
        return controller;
    }

    public ControllerInterface getSecondExample() {
        StatementInterface programExample2 = new CompoundStatement(new VariableDeclarationStatement("a", new IntType()),
                new CompoundStatement(new VariableDeclarationStatement("b", new IntType()), new CompoundStatement(new AssignmentStatement("a", new ArithmeticExpression(
                        new ValueExpression(new IntValue(2)), new ArithmeticExpression(new ValueExpression(new IntValue(3)), new ValueExpression(new IntValue(0)), '/'), '+')),
                        new CompoundStatement(new AssignmentStatement("b", new ArithmeticExpression(new VariableExpression("a"), new ValueExpression(new IntValue(1)), '+')),
                                new PrintStatement(new VariableExpression("b"))))));

        StackInterface<StatementInterface> executionStack = new MyStack<StatementInterface>();
        DictionaryInterface<String, ValueInterface> symTable = new MyDictionary<String, ValueInterface>();
        ListInterface<ValueInterface> output = new MyList<ValueInterface>();
        DictionaryInterface<StringValue, BufferedReader> fileTable = new MyDictionary<StringValue, BufferedReader>();
        ProgramState currentProgramState = new ProgramState(executionStack, symTable, output, fileTable, programExample2);
        RepositoryInterface repo = new Repository(this.FOLDER_PATH + "\\log3.in");
        ControllerInterface controller = new Controller(repo);

        controller.addProgramState(currentProgramState);
        return controller;
    }

    public ControllerInterface getThirdExample() {
        StatementInterface programExample3 = new CompoundStatement(
                new VariableDeclarationStatement("a", new BoolType()),
                new CompoundStatement(new VariableDeclarationStatement("v", new IntType()), new CompoundStatement(new AssignmentStatement("a", new ValueExpression(new BoolValue(true))),
                        new CompoundStatement(new IfStatement(new VariableExpression("a"), new AssignmentStatement("v", new ValueExpression(new IntValue(2))),
                                new AssignmentStatement("v", new ValueExpression(new IntValue(3)))), new PrintStatement(new VariableExpression("v"))))));

        StackInterface<StatementInterface> executionStack = new MyStack<StatementInterface>();
        DictionaryInterface<String, ValueInterface> symTable = new MyDictionary<String, ValueInterface>();
        ListInterface<ValueInterface> output = new MyList<ValueInterface>();
        DictionaryInterface<StringValue, BufferedReader> fileTable = new MyDictionary<StringValue, BufferedReader>();
        ProgramState currentProgramState = new ProgramState(executionStack, symTable, output, fileTable, programExample3);
        RepositoryInterface repo = new Repository(this.FOLDER_PATH + "\\log3.in");
        ControllerInterface controller = new Controller(repo);

        controller.addProgramState(currentProgramState);
        return controller;
    }

    public ControllerInterface getFourthExample() {
        return null;
    }

    public void start() {

        TextMenu textMenu = new TextMenu();

        try {
            textMenu.addCommand(new ExitCommand("0", "Exit program"));
            textMenu.addCommand(new RunExampleCommand("1", " int v; v=2; Print(v)", this.getFirstExample()));
            textMenu.addCommand(new RunExampleCommand("2", "int a; int b; a=2+3*5; b=a+1; Print(b)", this.getSecondExample()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        textMenu.show();
    }
}

/*
    public void executeProgramState(){
        try{
            ProgramState finishedProgram = this.controller.fullProgramExecution();
            System.out.println("Final Output: " + finishedProgram.getOutput()+'\n');
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
 */

/*
    public ControllerInterface addProgramState(StatementInterface state) {
        StackInterface<StatementInterface> executionStack = new MyStack<StatementInterface>();
        DictionaryInterface<String, ValueInterface> symTable = new MyDictionary<String, ValueInterface>();
        ListInterface<ValueInterface> output = new MyList<ValueInterface>();
        DictionaryInterface<StringValue, BufferedReader> fileTable = new MyDictionary<StringValue, BufferedReader>();
        ProgramState currentProgramState = new ProgramState(executionStack, symTable, output, fileTable, state);
        RepositoryInterface repo = new Repository(this.FOLDER_PATH + "\\log1.in");
        ControllerInterface controller = new Controller(repo);

        controller.addProgramState(currentProgramState);
        return controller;

    }
 */