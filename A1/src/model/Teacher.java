package model;

public class Teacher implements IPerson {

    private String name;
    private String occupation;
    private String school;
    private int age;
    protected boolean state;

    public Teacher(String name, String occupation, String school, int age, boolean state){
        this.name = name;
        this.occupation = occupation;
        this.school = school;
        this.age = age;
        this.state = state;
    }

    public String getSchool(){
        return this.school;
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
            return "Name: " + this.name + " | Occupation: " + this.occupation + " | School: " + this.school + " | Age: " + this.age + " | " + "not presented";

        }
        else {
            return "Name: " + this.name + " | Occupation: " + this.occupation + " | School: " + this.school + " | Age: " + this.age + " | " + "presented";
        }
    }
}
