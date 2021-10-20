package controller;

import model.IPerson;
import model.IndustrySpecialist;
import model.Student;
import model.Teacher;
import repository.IRepository;

public class Controller {

    private IRepository repository;

    public Controller(IRepository repository){
        this.repository = repository;
    }

    public IPerson[] filterPeople(){
        IPerson[] filteredPeople = new IPerson[this.repository.getRepository().length];
        int numberOfElements = 0;
        for(int i = 0; i < this.repository.getCurrPos();i++){
            if(this.repository.getRepository()[i].getState()){
                filteredPeople[numberOfElements++]=this.repository.getRepository()[i];
            }
        }

        IPerson[] result = new IPerson[numberOfElements];
        System.arraycopy(filteredPeople,0,result,0,numberOfElements);

        return result;
    }

    public void addStudent(String name, String occupation, int age, boolean state){
        IPerson student = new Student(name, occupation, age, state);
        this.repository.add(student);

    }

    public void addTeacher(String name, String occupation, String school, int age, boolean state)
    {
        IPerson teacher = new Teacher(name, occupation, school, age, state);
        this.repository.add(teacher);
    }

    public void addIndustrySpecialist(String name, String occupation, String company, int age, boolean state){
        IPerson industrySpecialist = new IndustrySpecialist(name, occupation, company, age, state);
        this.repository.add(industrySpecialist);
    }

    public IRepository getRepository(){
        return this.repository;
    }
}
