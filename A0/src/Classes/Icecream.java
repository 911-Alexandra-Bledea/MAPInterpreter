package Classes;

import java.security.InvalidParameterException;

public class Icecream {
    protected String flavour;

    private static int variable = 10;

    public Icecream(String flavour){
        this.flavour = flavour;
    }

    public String getFlavour(){return flavour;}

    public String toString(){return flavour + " ice cream";};

    public void setFlavour(String newFlavour){
        if(newFlavour.length() == 0){
            throw new InvalidParameterException("Invalid flavour!");
        }
        flavour = newFlavour;
    }

    public static int getVariable(){return variable;}


}
