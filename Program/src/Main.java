import Controller.HomeAnimalController;
import Models.HomeAnimal;
import Services.IDb;
import Services.HomeAnimalDB;
import InterfaceTerminal.Menu;

public class Main {
    public static void main(String[] args) throws Exception {

        IDb <HomeAnimal> myFarm = new HomeAnimalDB();
        HomeAnimalController controller = new HomeAnimalController(myFarm);
        new Menu (controller).start();
    }
}