package Controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import Models.*;
import Services.IDb;
import Services.HomeAnimalDB;
import InterfaceTerminal.*;

public class HomeAnimalController {
    private IDb<HomeAnimal> homeAnimalDB;
    private Creator homeAnimalCreator;
    private final IView<HomeAnimal> view;
    private Validator validator;

    public HomeAnimalController(IDb<HomeAnimal> homeAnimalDB) {
        this.homeAnimalDB = homeAnimalDB;
        this.homeAnimalCreator = new HomeAnimalCreator();
        this.view = new View();
        this.validator = new Validator();
    }

    public void createHomeAnimal(HomeAnimalType type) {

        String[] data = new String[] { view.getName(), view.getBirthday() };
        validator.validate(data);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate birthday = LocalDate.parse(data[1], formatter);
        try {
            int res = homeAnimalDB.create(homeAnimalCreator.createHomeAnimal(type, data[0], birthday));
            view.showMessage(String.format("%d Добавлено", res));
        } catch (RuntimeException e) {
            view.showMessage(e.getMessage());
        }

    }

    public void updateHomeAnimal(int id) {

        HomeAnimal homeAnimal = getById(id);
        String[] data = new String[] { view.getName(), view.getBirthday() };

        validator.validate(data);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate birthday = LocalDate.parse(data[1], formatter);
        homeAnimal.setName(data[0]);
        homeAnimal.setBirthday(birthday);
        try {
            int res = homeAnimalDB.update(homeAnimal);
            view.showMessage(String.format("%d Отредактировано \n", res));
        } catch (RuntimeException e) {
            view.showMessage(e.getMessage());
        }

    }

    public void getAllHomeAnimal() {
        try {
            view.printAll(homeAnimalDB.getAll(), HomeAnimal.class);
        } catch (RuntimeException e) {
            view.showMessage(e.getMessage());
        }
    }

    public boolean trainHomeAnimal(int id, String command) {
        try {

            if (((HomeAnimalDB) homeAnimalDB).getCommandsById(id, 1).contains(command))
                view.showMessage("Такая команда уже есть");
            else {
                if (!((HomeAnimalDB) homeAnimalDB).getCommandsById(id, 2).contains(command))
                    view.showMessage("Такая команда не будет выполнена");
                else {
                    ((HomeAnimalDB) homeAnimalDB).train(id, command);
                    view.showMessage("Команда добавлена\n");
                    return true;
                }
            }
        } catch (RuntimeException e) {
            view.showMessage(e.getMessage());
        }
        return false;
    }

    public HomeAnimal getById(int id) {
        try {
            return homeAnimalDB.getById(id);
        } catch (RuntimeException e) {
            view.showMessage(e.getMessage());
        }
        return null;
    }

    public void delete(int id) {
        try {
            homeAnimalDB.delete(id);
            view.showMessage("Удалено");
        } catch (RuntimeException e) {
            view.showMessage(e.getMessage());
        }
    }

    public void getCommands(int id) {
        try {
            view.printAll(((HomeAnimalDB) homeAnimalDB).getCommandsById(id, 1), String.class);
        } catch (RuntimeException e) {
            view.showMessage(e.getMessage());
        }
    }
}
