import view.View;

import java.util.ArrayDeque;

public class MainProgram {
    public static void main(String[] args){
        ArrayDeque<Integer> a = new ArrayDeque<>();
        a.add(1);
        a.add(2);
        System.out.println(a.toArray()[1].toString());
        View ui = new View();
        ui.start();
    }
}
