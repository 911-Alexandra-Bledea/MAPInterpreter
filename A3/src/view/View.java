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
import model.value.ValueInterface;

import javax.management.ValueExp;
import javax.swing.plaf.ComponentUI;
import java.util.Scanner;

public class View {

    private ControllerInterface controller;

    public View(){
        controller = new Controller();
    }

    public void start(){
        int choice = -999999;
        boolean done = false;
        Scanner reader = new Scanner(System.in);

        while(choice!=0){
            System.out.println("0. Exit");
            System.out.println("1. Input example 1");
            System.out.println("2. Input example 2");
            System.out.println("3. Input example 3");

            choice = reader.nextInt();

            if(choice == 0){
                System.out.println("Program had ended! Goodbye!");
                break;
            }

            if(choice == 1){
                StatementInterface programExample1= new CompoundStatement(new VariableDeclarationStatement("v",new IntType()),
                        new CompoundStatement(new AssignmentStatement("v",new ValueExpression(new IntValue(2))), new PrintStatement(new VariableExpression("v"))));

                addProgramState(programExample1);
                executeProgramState();

            }

            else if(choice == 2){
                StatementInterface programExample2 = new CompoundStatement(new VariableDeclarationStatement("a",new IntType()),
                        new CompoundStatement(new VariableDeclarationStatement("b",new IntType()), new CompoundStatement(new AssignmentStatement("a", new ArithmeticExpression(
                                new ValueExpression(new IntValue(2)), new ArithmeticExpression(new ValueExpression(new IntValue(3)), new ValueExpression(new IntValue(0)), '/'), '+')),
                                        new CompoundStatement(new AssignmentStatement("b", new ArithmeticExpression(new VariableExpression("a"), new ValueExpression(new IntValue(1)), '+')),
                                                new PrintStatement(new VariableExpression("b"))))));

                addProgramState(programExample2);
                executeProgramState();

            }

            else if(choice == 3){
                StatementInterface programExample3 = new CompoundStatement(
                        new VariableDeclarationStatement("a", new BoolType()),
                        new CompoundStatement(new VariableDeclarationStatement("v", new IntType()), new CompoundStatement(new AssignmentStatement("a", new ValueExpression(new BoolValue(true))),
                                        new CompoundStatement(new IfStatement(new VariableExpression("a"), new AssignmentStatement("v", new ValueExpression(new IntValue(2))),
                                                        new AssignmentStatement("v", new ValueExpression(new IntValue(3)))), new PrintStatement(new VariableExpression("v"))))));

                addProgramState(programExample3);
                executeProgramState();
            }
            else {
                System.out.println("Invalid choice!\n");
            }
        }

        reader.close();
    }

    public void executeProgramState(){
        try{
            ProgramState finishedProgram = this.controller.fullProgramExecution();
            System.out.println("Final Output: " + finishedProgram.getOutput()+'\n');
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void addProgramState(StatementInterface programExample2) {
        StackInterface<StatementInterface> stack = new MyStack<StatementInterface>();
        DictionaryInterface<String, ValueInterface> symbolTable = new MyDictionary<String, ValueInterface>();
        ListInterface<ValueInterface> output = new MyList<ValueInterface>();
        ProgramState crtProgramState = new ProgramState(stack, symbolTable, output, programExample2);

        this.controller.addProgramState(crtProgramState);
    }
}