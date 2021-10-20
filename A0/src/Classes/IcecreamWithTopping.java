package Classes;

import java.security.InvalidParameterException;

public class IcecreamWithTopping extends Icecream {

    protected String topping;

    public IcecreamWithTopping(String flavour, String topping) {
        super(flavour);
        this.topping = topping;
    }

    public String getTopping(){return topping;}

    public void setTopping(String newTopping){
        if(newTopping.length() < 3)
            throw new InvalidParameterException("Invalid topping!");
        topping = newTopping;
    }

    @Override
    public String toString(){return flavour + " ice cream with " + topping + " topping";}

}
