package view;

import controller.Controller;
import exceptions.InvalidIndexException;
import exceptions.InvalidInputException;
import model.IPerson;

import java.security.InvalidParameterException;
import java.util.Objects;
import java.util.Scanner;

public class Console {

    private Controller controller;

    public Console(Controller controller){
        this.controller = controller;
    }

    public void filterPeople() {
        IPerson[] filteredPeople = this.controller.filterPeople();
        for (IPerson filteredPerson : filteredPeople) {
            System.out.println(filteredPerson.toString());
        }
    }

    public void addPerson() throws InvalidInputException {
        System.out.println("What do you want to add? Type 1 for Student, 2 for Teacher or 3 for IndustrySpecialist!");
        Scanner read = new Scanner(System.in);
        String text = read.nextLine();
        if(Objects.equals(text, "1")){
            this.addStudent();
        }
        else if(Objects.equals(text, "2")){
                this.addTeacher();
        }
        else if(Objects.equals(text, "3")){
                this.addIndustrySpecialist();
        }
        else
        {
            throw new InvalidInputException();
        }
    }

    public void addStudent() throws InvalidInputException {

        Scanner read = new Scanner(System.in);
        System.out.println("Name: ");
        String name = read.nextLine();
        String occupation = "Student";
        System.out.println("Age: ");
        boolean state;
        String age = read.nextLine();
        int ageInt;
        if(age.matches("[0-9]+") && age.length() >= 1){
            ageInt = Integer.parseInt(age);
        }
        else
            throw new InvalidInputException();
        System.out.println("Type 1 for presented or 2 for not presented!");
        String type = read.nextLine();
        if(Objects.equals(type, "1"))
        {
            state = true;
        }
        else if(Objects.equals(type, "2"))
        {
            state = false;
        }
        else{
            throw new InvalidInputException();
        }
        controller.addStudent(name, occupation, ageInt, state);
        System.out.println("Student added with success!");
    }

    public void addTeacher() throws InvalidInputException {

        Scanner read = new Scanner(System.in);
        System.out.println("Name: ");
        String name = read.nextLine();
        String occupation = "Teacher";
        System.out.println("School: ");
        String school = read.nextLine();
        System.out.println("Age: ");
        boolean state;
        String age = read.nextLine();
        int ageInt;
        if(age.matches("[0-9]+") && age.length() >= 1){
            ageInt = Integer.parseInt(age);
        }
        else
            throw new InvalidInputException();
        System.out.println("Type 1 for presented or 2 for not presented!");
        String type = read.nextLine();
        if(Objects.equals(type, "1"))
        {
            state = true;
        }
        else if(Objects.equals(type, "2"))
        {
            state = false;
        }
        else{
            throw new InvalidInputException();
        }
            controller.addTeacher(name, occupation, school, ageInt, state);
            System.out.println("Teacher added with success!");

    }

    public void addIndustrySpecialist() throws InvalidInputException {

        Scanner read = new Scanner(System.in);
        System.out.println("Name: ");
        String name = read.nextLine();
        String occupation = "IndustrySpecialist";
        System.out.println("Company: ");
        String company = read.nextLine();
        System.out.println("Age: ");
        boolean state;
        String age = read.nextLine();
        int ageInt;
        if(age.matches("[0-9]+") && age.length() >= 1){
            ageInt = Integer.parseInt(age);
        }
        else
            throw new InvalidInputException();
        System.out.println("Type 1 for presented or 2 for not presented!");
        String type = read.nextLine();
        if(Objects.equals(type, "1"))
        {
            state = true;
        }
        else if(Objects.equals(type, "2"))
        {
            state = false;
        }
        else{
            throw new InvalidInputException();
        }
        controller.addIndustrySpecialist(name, occupation, company, ageInt, state);
        System.out.println("IndustrySpecialist added with success!");
    }

    public void displayPeople(){
        for(int i = 0; i < controller.getRepository().getCurrPos(); i++){
            System.out.println(controller.getRepository().getRepository()[i].toString());
        }
    }

    public void deletePerson() throws InvalidIndexException {

        Scanner read = new Scanner(System.in);

        System.out.println("Type in the index of the person you want to delete: ");
        int index;
        String indexS;
        indexS = read.nextLine();
        if(indexS.matches("[0-9]+") && indexS.length() >= 1) {
            index = Integer.parseInt(indexS);
            if(index < 0 || index > this.controller.getRepository().getCurrPos() - 1)
                throw new InvalidIndexException();
            this.controller.getRepository().remove(index);
        }
        else
            throw new InvalidIndexException();
        System.out.println("Person deleted successfully!");
    }

    public void Menu(){
        System.out.println("");
        System.out.println("Type 1 for adding a person!");
        System.out.println("Type 2 for displaying the people!");
        System.out.println("Type 3 for deleting a person!");
        System.out.println("Type 4 for displaying all the people who presented the project!");
        System.out.println("Type E for exiting the application!");
        System.out.println("Type H for help!");
        System.out.println("");
    }

    public void start(){
        boolean Done = false;
        Scanner read = new Scanner(System.in);

        Menu();

        while(!Done){
            try{
                System.out.println("Type in the command: ");
                String command;
                command = read.nextLine();
                if(!Objects.equals(command, "H") && !Objects.equals(command, "1") && !Objects.equals(command, "2") && !Objects.equals(command, "3") && !Objects.equals(command, "4") && !Objects.equals(command, "E")){
                    throw new InvalidInputException();
                }
                else {
                   switch (command.charAt(0)) {
                        case '1' -> addPerson();
                        case '2' -> displayPeople();
                        case '3' -> deletePerson();
                        case '4' -> filterPeople();
                        case 'E' -> Done = true;
                        case 'H' -> Menu();
                    }
                }
            }
            catch (InvalidInputException | InvalidIndexException e){
                System.out.println(e.getMessage());
            }
        }
    }
}


