package view;

import controller.Controller;
import controller.ControllerInterface;
import jdk.dynalink.support.AbstractRelinkableCallSite;
import model.ADT.*;
import model.ProgramState;
import model.expression.*;
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
        /// int v; v=2;Print(v)
        StatementInterface declare_v = new VariableDeclarationStatement("v", new IntType());
        StatementInterface assign_v = new AssignmentStatement("v", new ValueExpression(new IntValue(2)));
        StatementInterface print_v = new PrintStatement(new VariableExpression("v"));

        StatementInterface programExample1 = new CompoundStatement(declare_v, new CompoundStatement(assign_v, print_v));

        ProgramState currentProgramState1 = new ProgramState(new MyStack<StatementInterface>(), new MyDictionary<String, ValueInterface>(),
                new MyList<ValueInterface>(), new MyDictionary<StringValue, BufferedReader>(), programExample1);

        RepositoryInterface repo1 = new Repository(FOLDER_PATH + "\\log1.in");
        ControllerInterface controller1 = new Controller(repo1);

        controller1.addProgramState(currentProgramState1);



        /// EXAMPLE 2
        /// int a;int b; a=2+3*5;b=a+1;Print(b)
        StatementInterface declare_a = new VariableDeclarationStatement("a", new IntType());
        StatementInterface declare_b = new VariableDeclarationStatement("b", new IntType());
        ExpressionInterface multiply_a = new ArithmeticExpression(new ValueExpression(new IntValue(3)), new ValueExpression(new IntValue(5)), '*');
        ExpressionInterface add_a = new ArithmeticExpression(multiply_a, new ValueExpression(new IntValue(2)), '+');
        StatementInterface assign_a = new AssignmentStatement("a", add_a);
        ExpressionInterface add_b = new ArithmeticExpression(new VariableExpression("a"), new ValueExpression(new IntValue(1)), '+');
        StatementInterface assign_b = new AssignmentStatement("b", add_b);
        StatementInterface print_b = new PrintStatement(new VariableExpression("b"));

        StatementInterface programExample2 = new CompoundStatement(declare_a, new CompoundStatement(declare_b,
                new CompoundStatement(assign_a, new CompoundStatement(assign_b, print_b))));

        ProgramState currentProgramState2 = new ProgramState(new MyStack<StatementInterface>(), new MyDictionary<String, ValueInterface>(),
                new MyList<ValueInterface>(), new MyDictionary<StringValue, BufferedReader>(), programExample2);
        RepositoryInterface repo2 = new Repository(FOLDER_PATH + "\\log2.in");
        ControllerInterface controller2 = new Controller(repo2);

        controller2.addProgramState(currentProgramState2);




        /// EXAMPLE 3
        /// bool a; int v; a=true;(If a Then v=2 Else v=3);Print(v)
        StatementInterface declare_a3 = new VariableDeclarationStatement("a", new BoolType());
        StatementInterface declare_v3 = new VariableDeclarationStatement("v", new IntType());
        StatementInterface assign_a3 = new AssignmentStatement("a", new ValueExpression(new BoolValue(true)));
        StatementInterface assign_v_1 = new AssignmentStatement("v", new ValueExpression(new IntValue(2)));
        StatementInterface assign_v_2 = new AssignmentStatement("v", new ValueExpression(new IntValue(3)));
        StatementInterface if_statement3 = new IfStatement(new VariableExpression("a"), assign_v_1, assign_v_2);
        StatementInterface print_v3 = new PrintStatement(new VariableExpression("v"));

        StatementInterface programExample3 = new CompoundStatement(declare_a3, new CompoundStatement(declare_v3, new CompoundStatement(assign_a3,
                        new CompoundStatement(if_statement3, print_v3))));
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
        RepositoryInterface repo4 = new Repository(FOLDER_PATH + "\\log4.in");
        ControllerInterface controller4 = new Controller(repo4);

        controller4.addProgramState(currentProgramState4);


        /// EXAMPLE 5
        ///int a; a = 3; int b; b = 4; IF (a > b) THEN print(a) ELSE print(b)

        StatementInterface declare_a5 = new VariableDeclarationStatement("a", new IntType());
        StatementInterface assign_a5 = new AssignmentStatement("a", new ValueExpression(new IntValue(25)));
        StatementInterface declare_b5 = new VariableDeclarationStatement("b", new IntType());
        StatementInterface assign_b5 = new AssignmentStatement("b", new ValueExpression(new IntValue(30)));
        ExpressionInterface relationalExpression5 = new RelationalExpression(new VariableExpression("a"), new VariableExpression("b"), "<");
        StatementInterface print_a5 = new PrintStatement(new VariableExpression("a"));
        StatementInterface print_b5 = new PrintStatement(new VariableExpression("b"));
        StatementInterface if_statement5 = new IfStatement(relationalExpression5, print_a5, print_b5);

        StatementInterface programExample5 = new CompoundStatement(declare_a5, new CompoundStatement(assign_a5,
                new CompoundStatement(declare_b5, new CompoundStatement(assign_b5, if_statement5))));

        ProgramState currentProgramState5 = new ProgramState(new MyStack<>(), new MyDictionary<String, ValueInterface>(),
                new MyList<ValueInterface>(), new MyDictionary<StringValue, BufferedReader>(), programExample5);
        RepositoryInterface repo5 = new Repository(FOLDER_PATH + "\\log5.in");
        ControllerInterface controller5 = new Controller(repo5);

        controller5.addProgramState(currentProgramState5);

        TextMenu textMenu = new TextMenu();

        try {
            textMenu.addCommand(new ExitCommand("0", "Exit program"));
            textMenu.addCommand(new RunExampleCommand("1", " int v; v=2; Print(v)", controller1));
            textMenu.addCommand(new RunExampleCommand("2", "int a; int b; a=2+3*5; b=a+1; Print(b)", controller2));
            textMenu.addCommand(new RunExampleCommand("3", "bool a; int v; a=true;(If a Then v=2 Else v=3);Print(v)", controller3));
            textMenu.addCommand(new RunExampleCommand("4", "string varf; " +
                    " varf=\"test.in\"; " +
                    " openRFile(varf); " +
                    " int varc; " +
                    " readFile(varf,varc) ;print(varc); " +
                    " readFile(varf,varc); print(varc) " +
                    " closeRFile(varf) ", controller4));
            textMenu.addCommand(new RunExampleCommand("5", "int a; a = 25; int b; b = 30; IF (a < b) THEN print(a) ELSE print(b)", controller5));
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