package model;

public class IndustrySpecialist implements IPerson {

    private String name;
    private String occupation;
    private String company;
    private int age;
    protected boolean state;

    public IndustrySpecialist(String name, String occupation, String company, int age, boolean state){
        this.name = name;
        this.occupation = occupation;
        this.company = company;
        this.age = age;
        this.state = state;
    }

    public String getName(){
        return this.name;
    }

    public String getOccupation(){
        return this.occupation;
    }

    public String getCompany(){
        return this.company;
    }

    public int getAge(){
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
            return "Name: " + this.name + " | Occupation: " + this.occupation + " | Company: " + this.company + " | Age: " + this.age + " | " + "not presented";

        }
        else {
            return "Name: " + this.name + " | Occupation: " + this.occupation + " | Company: " + this.company + " | Age: " + this.age + " | " + "presented";
        }
    }
}
