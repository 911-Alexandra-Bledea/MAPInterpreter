package model;

public class Student implements IPerson{

    private String name;
    private String occupation;
    private int age;
    protected boolean state;

    public Student(String name, String occupation, int age, boolean state){
        this.name = name;
        this.occupation = occupation;
        this.age = age;
        this.state = state;
    }

    public String getOccupation(){
        return this.occupation;
    }

    public String getName(){
        return this.name;
    }

    public Integer getAge(){
        return this.age;
    }

    @Override
    public void setState(boolean presented) {
        this.state = presented;
    }

    @Override
    public boolean getState() {
        return this.state;
    }

    @Override
    public String toString(){
        if(!this.state)
        {
            return "Name: " + this.name + " | Occupation: " + this.occupation + " | Age: " + this.age + " | " + "not presented";

        }
        else {
            return "Name: " + this.name + " | Occupation: " + this.occupation + " | Age: " + this.age + " | " + "presented";
        }
    }
}
