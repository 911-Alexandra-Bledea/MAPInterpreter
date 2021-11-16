package model.expression;

import model.ADT.DictionaryInterface;
import model.value.ValueInterface;

public class ValueExpression implements ExpressionInterface{
    private ValueInterface value;

    public ValueExpression(ValueInterface value) {
        this.value = value;
    }

    @Override
    public ValueInterface evaluate(DictionaryInterface<String, ValueInterface> table) throws Exception {
        return value;
    }

    public String toString(){
        return value.toString();
    }

}
