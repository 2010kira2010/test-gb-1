package Models;

import java.time.LocalDate;

public abstract class Creator {
    protected abstract HomeAnimal createNewHomeAnimal(HomeAnimalType type);

    public HomeAnimal createHomeAnimal(HomeAnimalType type, String name, LocalDate date) {

        HomeAnimal homeAnimal = createNewHomeAnimal(type);
        homeAnimal.setName(name);
        homeAnimal.setBirthday(date);
        return homeAnimal;
    }
}
