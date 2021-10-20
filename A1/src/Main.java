import controller.Controller;
import repository.Repository;
import view.Console;

public class Main {
    public static void main(String[] args) {
        Repository repo = new Repository(10);
        Controller controller = new Controller(repo);
        Console console = new Console(controller);
        console.start();
    }
}
