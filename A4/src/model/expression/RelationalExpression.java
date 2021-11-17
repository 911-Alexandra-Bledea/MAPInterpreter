package model.expression;

import exception.InvalidOperatorException;
import exception.InvalidTypeException;
import model.ADT.Dictionary.DictionaryInterface;
import model.ADT.Heap.HeapInterface;
import model.type.IntType;
import model.value.BoolValue;
import model.value.IntValue;
import model.value.ValueInterface;

public class RelationalExpression implements ExpressionInterface{

    private final ExpressionInterface firstExpression;
    private final ExpressionInterface secondExpression;
    private final String operator;

    public RelationalExpression(ExpressionInterface firstExpression, ExpressionInterface secondExpression, String operator){
        this.firstExpression = firstExpression;
        this.secondExpression = secondExpression;
        this.operator = operator;
    }

    public ValueInterface evaluate(DictionaryInterface<String, ValueInterface> table, HeapInterface<Integer, ValueInterface> heap) throws Exception{
        ValueInterface firstValue, secondValue;

        firstValue = this.firstExpression.evaluate(table, heap);
        if(!firstValue.getType().equals(new IntType())){
            throw new InvalidTypeException("First operand is not an integer!\n");
        }

        secondValue = this.secondExpression.evaluate(table, heap);
        if(!secondValue.getType().equals(new IntType())){
            throw new InvalidTypeException("Second operand is not an integer!\n");
        }

        int firstInt = ((IntValue)firstValue).getValue();
        int secondInt = ((IntValue)secondValue).getValue();

        if(this.operator.equals("<")){
            return new BoolValue(firstInt < secondInt);
        }

        if(this.operator.equals("<=")){
            return new BoolValue(firstInt <= secondInt);
        }

        if(this.operator.equals(">")){
            return new BoolValue(firstInt > secondInt);
        }

        if(this.operator.equals(">=")){
            return new BoolValue(firstInt >= secondInt);
        }

        if(this.operator.equals("==")){
            return new BoolValue(firstInt == secondInt);
        }

        if(this.operator.equals("!=")){
            return new BoolValue(firstInt != secondInt);
        }
        else {

            throw new InvalidOperatorException();
        }
    }
    public String toString(){
        String representation = "";
        representation += (firstExpression.toString());
        representation += (" " + this.operator + " ");
        representation += (this.secondExpression.toString());
        return representation;
    }

}
