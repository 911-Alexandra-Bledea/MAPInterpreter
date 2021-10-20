package model.expression;

import exception.DivisionByZeroException;
import exception.InvalidOperatorException;
import exception.InvalidTypeException;
import model.ADT.DictionaryInterface;
import model.type.IntType;
import model.value.IntValue;
import model.value.ValueInterface;

public class ArithmeticExpression
{

    private ExpressionInterface firstExpression;
    private ExpressionInterface secondExpression;
    private int operator;

    public ArithmeticExpression(ExpressionInterface firstExpression, ExpressionInterface secondExpression, int operator) {
        this.firstExpression = firstExpression;
        this.secondExpression = secondExpression;
        this.operator = operator;
    }

    public ValueInterface evaluate(DictionaryInterface<String, ValueInterface> table) throws Exception{
        ValueInterface firstValue, secondValue;
        firstValue = firstExpression.evaluate(table);
        if(firstValue.getType().equals(new IntType())) {
            secondValue = secondExpression.evaluate(table);
            if (secondValue.getType().equals(new IntType())) {
                int firstInt = ((IntValue) firstValue).getValue();
                int secondInt = ((IntValue) secondValue).getValue();
                if (operator == 1) {
                    return new IntValue(firstInt + secondInt);
                }
                if (operator == 2) {
                    return new IntValue(firstInt - secondInt);
                }
                if (operator == 3) {
                    return new IntValue(firstInt * secondInt);
                }
                if (operator == 4) {
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
