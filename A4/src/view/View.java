package view;

import controller.Controller;
import controller.ControllerInterface;
import model.ADT.Dictionary.MyDictionary;
import model.ADT.Heap.MyHeap;
import model.ADT.List.MyList;
import model.ADT.Stack.MyStack;
import model.ProgramState;
import model.expression.*;
import model.statement.*;
import model.statement.FilePack.CloseReadFile;
import model.statement.FilePack.OpenReadFileStatement;
import model.statement.FilePack.ReadFileStatement;
import model.statement.HeapPack.HeapAllocationStatement;
import model.statement.HeapPack.HeapWritingStatement;
import model.type.*;
import model.value.*;
import repository.Repository;
import repository.RepositoryInterface;

import javax.swing.plaf.nimbus.State;
import java.io.BufferedReader;


public class View {

    public static void main(String[] args){

        String FOLDER_PATH = "C:\\Users\\night\\Desktop\\Facultate An 2\\Semestrul 1\\Advanced Programming Methods\\MAPInterpreter\\A4";




        /// EXAMPLE 1
        /// int v; v=2;Print(v)
        StatementInterface declare_v = new VariableDeclarationStatement("v", new IntType());
        StatementInterface assign_v = new AssignmentStatement("v", new ValueExpression(new IntValue(2)));
        StatementInterface print_v = new PrintStatement(new VariableExpression("v"));

        StatementInterface programExample1 = new CompoundStatement(declare_v, new CompoundStatement(assign_v, print_v));

        ProgramState currentProgramState1 = new ProgramState(new MyStack<StatementInterface>(), new MyDictionary<String, ValueInterface>(),
                new MyList<ValueInterface>(), new MyDictionary<StringValue, BufferedReader>(), new MyHeap<>(), programExample1);
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
                new MyList<ValueInterface>(), new MyDictionary<StringValue, BufferedReader>(), new MyHeap<>(), programExample2);
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
                new MyList<ValueInterface>(), new MyDictionary<StringValue, BufferedReader>(), new MyHeap<>(), programExample3);
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
                new MyList<ValueInterface>(), new MyDictionary<StringValue, BufferedReader>(), new MyHeap<>(), programExample4);
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
                new MyList<ValueInterface>(), new MyDictionary<StringValue, BufferedReader>(), new MyHeap<>(), programExample5);
        RepositoryInterface repo5 = new Repository(FOLDER_PATH + "\\log5.in");
        ControllerInterface controller5 = new Controller(repo5);

        controller5.addProgramState(currentProgramState5);





        /// EXAMPLE 6
        ///Ref int v;new(v,20);Ref Ref int a; new(a,v);print(v);print(a)

        StatementInterface declare_v6 = new VariableDeclarationStatement("v", new ReferenceType(new IntType()));
        StatementInterface alloc_v6 = new HeapAllocationStatement("v", new ValueExpression(new IntValue(20)));
        StatementInterface declare_a6 = new VariableDeclarationStatement("a", new ReferenceType(new ReferenceType(new IntType())));
        StatementInterface alloc_a6 = new HeapAllocationStatement("a", new VariableExpression("v"));
        StatementInterface print_v6 = new PrintStatement(new VariableExpression("v"));
        StatementInterface print_a6 = new PrintStatement(new VariableExpression("a"));

        StatementInterface programExample6 = new CompoundStatement(declare_v6, new CompoundStatement(alloc_v6,
                new CompoundStatement(declare_a6, new CompoundStatement(alloc_a6, new CompoundStatement(print_v6, print_a6)))));

        ProgramState currentProgramState6 = new ProgramState(new MyStack<>(), new MyDictionary<String, ValueInterface>(),
                new MyList<ValueInterface>(), new MyDictionary<StringValue, BufferedReader>(), new MyHeap<>(), programExample6);
        RepositoryInterface repo6 = new Repository(FOLDER_PATH + "\\log6.in");
        ControllerInterface controller6 = new Controller(repo6);

        controller6.addProgramState(currentProgramState6);




        ///EXAMPLE 7
        ///Ref int v;new(v,20);Ref Ref int a; new(a,v);print(rH(v));print(rH(rH(a))+5)

        StatementInterface declare_v7 = new VariableDeclarationStatement("v", new ReferenceType(new IntType()));
        StatementInterface alloc_v7 = new HeapAllocationStatement("v", new ValueExpression(new IntValue(20)));
        StatementInterface declare_a7 = new VariableDeclarationStatement("a", new ReferenceType(new ReferenceType(new IntType())));
        StatementInterface alloc_a7 = new HeapAllocationStatement("a", new VariableExpression("v"));
        ExpressionInterface read_v7 = new HeapReadingExpression(new VariableExpression("v"));
        StatementInterface print_v7 = new PrintStatement(read_v7);
        ExpressionInterface read_a7 = new HeapReadingExpression(new HeapReadingExpression(new VariableExpression("a")));
        ExpressionInterface add7 = new ArithmeticExpression(read_a7, new ValueExpression(new IntValue(5)), '+');
        StatementInterface print_a7 = new PrintStatement(add7);

        StatementInterface programExample7 = new CompoundStatement(declare_v7, new CompoundStatement(alloc_v7, new CompoundStatement(declare_a7,
                new CompoundStatement(alloc_a7, new CompoundStatement(print_v7, print_a7)))));

        ProgramState currentProgramState7 = new ProgramState(new MyStack<>(), new MyDictionary<String, ValueInterface>(),
                new MyList<ValueInterface>(), new MyDictionary<StringValue, BufferedReader>(), new MyHeap<>(), programExample7);
        RepositoryInterface repo7 = new Repository(FOLDER_PATH + "\\log7.in");
        ControllerInterface controller7 = new Controller(repo7);

        controller7.addProgramState(currentProgramState7);





        ///EXAMPLE 8
        ///Ref int v;new(v,20);print(rH(v)); wH(v,30);print(rH(v)+5);

        StatementInterface declare_v8 = new VariableDeclarationStatement("v", new ReferenceType(new IntType()));
        StatementInterface alloc_v8 = new HeapAllocationStatement("v", new ValueExpression(new IntValue(20)));
        ExpressionInterface read_v8 = new HeapReadingExpression(new VariableExpression("v"));
        StatementInterface print_v8 = new PrintStatement(read_v8);
        StatementInterface write_v8 = new HeapWritingStatement("v", new ValueExpression(new IntValue(30)));
        ExpressionInterface read_v8_2 = new HeapReadingExpression(new VariableExpression("v"));
        ExpressionInterface add8 = new ArithmeticExpression(read_v8_2, new ValueExpression(new IntValue(5)), '+');
        StatementInterface print_v8_2 = new PrintStatement(add8);

        StatementInterface programExample8 = new CompoundStatement(declare_v8, new CompoundStatement(alloc_v8, new CompoundStatement(print_v8,
                new CompoundStatement(write_v8, print_v8_2))));

        ProgramState currentProgramState8 = new ProgramState(new MyStack<>(), new MyDictionary<String, ValueInterface>(),
                new MyList<ValueInterface>(), new MyDictionary<StringValue, BufferedReader>(), new MyHeap<>(), programExample8);
        RepositoryInterface repo8 = new Repository(FOLDER_PATH + "\\log8.in");
        ControllerInterface controller8 = new Controller(repo8);

        controller8.addProgramState(currentProgramState8);





        ///EXAMPLE 9
        ///Ref int v;new(v,20);Ref Ref int a; new(a,v); new(v,30);print(rH(rH(a)))
        StatementInterface declare_v9 = new VariableDeclarationStatement("v", new ReferenceType(new IntType()));
        StatementInterface alloc_v9_1 = new HeapAllocationStatement("v", new ValueExpression(new IntValue(20)));
        StatementInterface declare_a9 = new VariableDeclarationStatement("a", new ReferenceType(new ReferenceType(new IntType())));
        StatementInterface alloc_a9 = new HeapAllocationStatement("a", new VariableExpression("v"));
        StatementInterface alloc_v9_2 = new HeapAllocationStatement("v", new ValueExpression(new IntValue(30)));
        ExpressionInterface read_a_1 = new HeapReadingExpression(new VariableExpression("a"));
        ExpressionInterface read_a_2 = new HeapReadingExpression(read_a_1);
        StatementInterface print_a9 = new PrintStatement(read_a_2);

        StatementInterface programExample9 = new CompoundStatement(declare_v9, new CompoundStatement(alloc_v9_1, new CompoundStatement(declare_a9,
                new CompoundStatement(alloc_a9, new CompoundStatement(alloc_v9_2, print_a9)))));

        ProgramState currentProgramState9 = new ProgramState(new MyStack<>(), new MyDictionary<String, ValueInterface>(),
                new MyList<ValueInterface>(), new MyDictionary<StringValue, BufferedReader>(), new MyHeap<>(), programExample9);
        RepositoryInterface repo9 = new Repository(FOLDER_PATH + "\\log9.in");
        ControllerInterface controller9 = new Controller(repo9);

        controller9.addProgramState(currentProgramState9);




        ///EXAMPLE 10
        ///int v; v=4; (while (v>0) print(v);v=v-1);print(v)
        StatementInterface declare_v10 = new VariableDeclarationStatement("v", new IntType());
        StatementInterface assign_v10_1 = new AssignmentStatement("v", new ValueExpression(new IntValue(4)));
        ExpressionInterface rel_expr10 = new RelationalExpression(new VariableExpression("v"), new ValueExpression(new IntValue(0)), ">");
        StatementInterface print_v10_1 = new PrintStatement(new VariableExpression("v"));
        ExpressionInterface arithmetic_v10 = new ArithmeticExpression(new VariableExpression("v"), new ValueExpression(new IntValue(1)), '-');
        StatementInterface assign_v10_2 = new AssignmentStatement("v", arithmetic_v10);
        StatementInterface compoundStatement_v10 = new CompoundStatement(print_v10_1, assign_v10_2);
        StatementInterface whileStatement_v10 = new WhileStatement(rel_expr10, compoundStatement_v10);
        StatementInterface print_v10_2 = new PrintStatement(new VariableExpression("v"));

        StatementInterface programExample10 = new CompoundStatement(declare_v10, new CompoundStatement(assign_v10_1, new CompoundStatement(whileStatement_v10, print_v10_2)));

        ProgramState currentProgramState10 = new ProgramState(new MyStack<>(), new MyDictionary<String, ValueInterface>(),
                new MyList<ValueInterface>(), new MyDictionary<StringValue, BufferedReader>(), new MyHeap<>(), programExample10);
        RepositoryInterface repo10 = new Repository(FOLDER_PATH + "\\log10.in");
        ControllerInterface controller10 = new Controller(repo10);

        controller10.addProgramState(currentProgramState10);




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
            textMenu.addCommand(new RunExampleCommand("6", "Ref int v;new(v,20);Ref Ref int a; new(a,v);print(v);print(a)", controller6));
            textMenu.addCommand(new RunExampleCommand("7", "Ref int v;new(v,20);Ref Ref int a; new(a,v);print(rH(v));print(rH(rH(a))+5)", controller7));
            textMenu.addCommand(new RunExampleCommand("8", "Ref int v;new(v,20);print(rH(v)); wH(v,30);print(rH(v)+5);", controller8));
            textMenu.addCommand(new RunExampleCommand("9", "Ref int v;new(v,20);Ref Ref int a; new(a,v); new(v,30);print(rH(rH(a)))", controller9));
            textMenu.addCommand(new RunExampleCommand("10", "int v; v=4; (while (v>0) print(v);v=v-1);print(v)", controller10));
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