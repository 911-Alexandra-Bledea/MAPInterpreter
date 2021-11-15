package view;

import controller.Controller;
import controller.ControllerInterface;
import model.ADT.*;
import model.ProgramState;
import model.expression.ArithmeticExpression;
import model.expression.ExpressionInterface;
import model.expression.ValueExpression;
import model.expression.VariableExpression;
import model.statement.*;
import model.type.BoolType;
import model.type.IntType;
import model.type.StringType;
import model.type.TypeInterface;
import model.value.BoolValue;
import model.value.IntValue;
import model.value.StringValue;
import model.value.ValueInterface;
import repository.Repository;
import repository.RepositoryInterface;

import javax.swing.plaf.nimbus.State;
import java.awt.color.CMMException;
import java.io.BufferedReader;


public class View {

    public static void main(String[] args){

        String FOLDER_PATH = "C:\\Users\\night\\Desktop\\Facultate An 2\\Semestrul 1\\Advanced Programming Methods\\MAPInterpreter\\A2";

        /// EXAMPLE 1
        StatementInterface programExample1 = new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                new CompoundStatement(new AssignmentStatement("v", new ValueExpression(new IntValue(2))), new PrintStatement(new VariableExpression("v"))));
        ProgramState currentProgramState1 = new ProgramState(new MyStack<StatementInterface>(), new MyDictionary<String, ValueInterface>(),
                new MyList<ValueInterface>(), new MyDictionary<StringValue, BufferedReader>(), programExample1);
        RepositoryInterface repo1 = new Repository(FOLDER_PATH + "\\log1.in");
        ControllerInterface controller1 = new Controller(repo1);

        controller1.addProgramState(currentProgramState1);

        /// EXAMPLE 2
        StatementInterface programExample2 = new CompoundStatement(new VariableDeclarationStatement("a", new IntType()),
                new CompoundStatement(new VariableDeclarationStatement("b", new IntType()), new CompoundStatement(new AssignmentStatement("a", new ArithmeticExpression(
                        new ValueExpression(new IntValue(2)), new ArithmeticExpression(new ValueExpression(new IntValue(3)), new ValueExpression(new IntValue(5)), '*'), '+')),
                        new CompoundStatement(new AssignmentStatement("b", new ArithmeticExpression(new VariableExpression("a"), new ValueExpression(new IntValue(1)), '+')),
                                new PrintStatement(new VariableExpression("b"))))));
        ProgramState currentProgramState2 = new ProgramState(new MyStack<StatementInterface>(), new MyDictionary<String, ValueInterface>(),
                new MyList<ValueInterface>(), new MyDictionary<StringValue, BufferedReader>(), programExample2);
        RepositoryInterface repo2 = new Repository(FOLDER_PATH + "\\log2.in");
        ControllerInterface controller2 = new Controller(repo2);

        controller2.addProgramState(currentProgramState2);

        /// EXAMPLE 3
        StatementInterface programExample3 = new CompoundStatement(
                new VariableDeclarationStatement("a", new BoolType()),
                new CompoundStatement(new VariableDeclarationStatement("v", new IntType()), new CompoundStatement(new AssignmentStatement("a", new ValueExpression(new BoolValue(true))),
                        new CompoundStatement(new IfStatement(new VariableExpression("a"), new AssignmentStatement("v", new ValueExpression(new IntValue(2))),
                                new AssignmentStatement("v", new ValueExpression(new IntValue(3)))), new PrintStatement(new VariableExpression("v"))))));
        ProgramState currentProgramState3 = new ProgramState(new MyStack<StatementInterface>(), new MyDictionary<String, ValueInterface>(),
                new MyList<ValueInterface>(), new MyDictionary<StringValue, BufferedReader>(), programExample3);
        RepositoryInterface repo3 = new Repository(FOLDER_PATH + "\\log3.in");
        ControllerInterface controller3 = new Controller(repo3);

        controller3.addProgramState(currentProgramState3);

        /// EXAMPLE 4
        StatementInterface stringDeclaration = new VariableDeclarationStatement("varf", new StringType());
        StatementInterface assignment = new AssignmentStatement("varf", new ValueExpression(new StringValue("test.in")));
        StatementInterface open = new OpenReadFileStatement(new VariableExpression("varf"));
        StatementInterface intDeclaration = new VariableDeclarationStatement("varc", new IntType());
        StatementInterface readFile = new ReadFileStatement(new VariableExpression("varf"), "varc");
        StatementInterface print = new PrintStatement(new VariableExpression("varc"));
        StatementInterface close = new CloseReadFile(new VariableExpression("varf"));

        StatementInterface programExample4 = new CompoundStatement(stringDeclaration, new CompoundStatement(assignment, new CompoundStatement(open,
                new CompoundStatement(intDeclaration, new CompoundStatement(readFile, new CompoundStatement(print,
                        new CompoundStatement(readFile, new CompoundStatement(print, close))))))));

        ProgramState currentProgramState4 = new ProgramState(new MyStack<StatementInterface>(), new MyDictionary<String, ValueInterface>(),
                new MyList<ValueInterface>(), new MyDictionary<StringValue, BufferedReader>(), programExample4);
        RepositoryInterface repo4 = new Repository(FOLDER_PATH + "\\test.in");
        ControllerInterface controller4 = new Controller(repo4);

        controller4.addProgramState(currentProgramState4);

        TextMenu textMenu = new TextMenu();

        try {
            textMenu.addCommand(new ExitCommand("0", "Exit program"));
            textMenu.addCommand(new RunExampleCommand("1", " int v; v=2; Print(v)", controller1));
            textMenu.addCommand(new RunExampleCommand("2", "int a; int b; a=2+3*5; b=a+1; Print(b)", controller2));
            textMenu.addCommand(new RunExampleCommand("4", "string varf; " +
                    " varf=\"test.in\"; " +
                    " openRFile(varf); " +
                    " int varc; " +
                    " readFile(varf,varc) ;print(varc); " +
                    " readFile(varf,varc); print(varc) " +
                    " closeRFile(varf) ", controller4));
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


/*
        StatementInterface programExample4 = new CompoundStatement(
                new VariableDeclarationStatement("varf", new StringType()),
                new CompoundStatement(new AssignmentStatement("varf",
                        new ValueExpression(new StringValue("test.in"))),
                        new CompoundStatement(new OpenReadFileStatement(new VariableExpression("varf")),
                                new CompoundStatement(new VariableDeclarationStatement("varc", new IntType()),
                                        new CompoundStatement(new ReadFileStatement(new VariableExpression("varf"), "vrac"),
                                                new CompoundStatement(new PrintStatement(new VariableExpression("vrac")),
                                                        new CompoundStatement(new ReadFileStatement(new VariableExpression("varf"), "vrac"),
                                                                new CompoundStatement(new PrintStatement(new VariableExpression("vrac")), new CloseReadFile(new VariableExpression("varf"))))))))));


 */