import Classes.Icecream;
import Classes.IcecreamWithTopping;

import javax.swing.*;
import java.security.InvalidParameterException;

public class Main {

    public static void main(String[] args){

        Icecream ic1 = new Icecream(args[0]);
        Icecream ic2 = new Icecream(args[1]);

        System.out.println("Ice cream 1 is : ");
        System.out.println(ic1.toString());

        String iceCream2String = ic2.toString();

        System.out.println("Ice cream 2 is : ");
        System.out.println(iceCream2String);

        IcecreamWithTopping ict1 = new IcecreamWithTopping(ic1.getFlavour(), args[2]);

        System.out.println("Ice cream 1 with topping is : ");
        System.out.println(ict1.toString());


        System.out.println("Static field is : ");
        System.out.println(Icecream.getVariable());


        try{
            ict1.setTopping("A");
        }
        catch (InvalidParameterException ex)
        {
            System.out.println(ex.getMessage());
        }

        try{
            ic1.setFlavour("");
        }
        catch (InvalidParameterException ex1){
            System.out.println(ex1.getMessage());
        }

    }
}
