package view;

import controller.Controller;
import controller.ControllerInterface;
import model.statement.StatementInterface;

public class View {

    private ControllerInterface controller;

    public View(){
        controller = new Controller();
    }


}
