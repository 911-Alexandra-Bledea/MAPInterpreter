package repository;

import model.IPerson;

public class Repository implements IRepository {

    private IPerson[] people;
    private int currCapacity;
    private int currPos;

    public Repository(int capacity) {
        this.people = new IPerson[capacity];
        this.currPos = 0;
        this.currCapacity = capacity;
    }

    private void resize(){
        IPerson[] newPeople = new IPerson[currCapacity*2];
        System.arraycopy(this.people, 0, newPeople, 0, this.people.length);

        this.people = newPeople;

        this.currCapacity = this.currCapacity * 2;
    }

    @Override
    public void add(IPerson newPerson) {
        if (this.currPos == this.currCapacity)
            resize();
        this.people[this.currPos++] = newPerson;
    }

    @Override
    public void remove(int index) {
        for (int i = index; i < this.people.length - 1; i++) {
            this.people[i] = this.people[i + 1];
        }
        this.currPos--;
    }

    @Override
    public IPerson[] getRepository() {
        return this.people;
    }

    @Override
    public int getCurrPos() {
        return this.currPos;
    }

    @Override
    public int getCurrCapacity(){return this.currCapacity;}
}
