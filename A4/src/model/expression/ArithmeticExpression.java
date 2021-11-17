package model.expression;

import exception.DivisionByZeroException;
import exception.InvalidOperatorException;
import exception.InvalidTypeException;
import model.ADT.DictionaryInterface;
import model.ADT.HeapInterface;
import model.ADT.MyHeap;
import model.type.IntType;
import model.value.IntValue;
import model.value.ValueInterface;

public class ArithmeticExpression implements ExpressionInterface
{

    private ExpressionInterface firstExpression;
    private ExpressionInterface secondExpression;
//    private int operator;
    private char operator;

//    public ArithmeticExpression(ExpressionInterface firstExpression, ExpressionInterface secondExpression, int operator) {
//        this.firstExpression = firstExpression;
//        this.secondExpression = secondExpression;
//        this.operator = operator;
//    }

    public ArithmeticExpression(ExpressionInterface firstExpression, ExpressionInterface secondExpression, char operator) {
        this.firstExpression = firstExpression;
        this.secondExpression = secondExpression;
        this.operator = operator;
    }

    @Override
    public String toString(){
        return firstExpression.toString() + operator + secondExpression.toString();
    }

    public ValueInterface evaluate(DictionaryInterface<String, ValueInterface> table, HeapInterface<Integer, ValueInterface> heap) throws Exception{
        ValueInterface firstValue, secondValue;
        firstValue = firstExpression.evaluate(table, heap);
        if(firstValue.getType().equals(new IntType())) {
            secondValue = secondExpression.evaluate(table, heap);
            if (secondValue.getType().equals(new IntType())) {
                int firstInt = ((IntValue) firstValue).getValue();
                int secondInt = ((IntValue) secondValue).getValue();
                if (operator == '+') {
                    return new IntValue(firstInt + secondInt);
                }
                if (operator == '-') {
                    return new IntValue(firstInt - secondInt);
                }
                if (operator == '*') {
                    return new IntValue(firstInt * secondInt);
                }
                if (operator == '/') {
                    if (secondInt == 0)
                        throw new DivisionByZeroException("Division by zero!");
                    else
                        return new IntValue(firstInt / secondInt);
                }
                else {
                    throw new InvalidOperatorException();
                }
            }
            else {
                throw new InvalidTypeException("Second operand is not an integer!");
            }
        }

        else{
            throw new InvalidTypeException("First operand is not an integer!");
        }
    }
}