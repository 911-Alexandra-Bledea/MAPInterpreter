package repository;
import model.*;

public interface IRepository {

    void add(IPerson newPerson);
    void remove(int index);
    IPerson[] getRepository();
    int getCurrPos();
    int getCurrCapacity();

}
