package Models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class HomeAnimal {
    protected int homeAnimalId;
    protected String name;
    protected LocalDate birthday;

    public void setHomeAnimalId(int petId) {
        this.homeAnimalId = homeAnimalId;
    }

    public int getHomeAnimalId() {
        return homeAnimalId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setBirthday(LocalDate date) {
        this.birthday = date;
    }

    public LocalDate getBirthdayDate(){
        return birthday;
    }

    public String getBirthday() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return formatter.format(birthday);
    }

    @Override
    public String toString() {
        return String.format("%d. %s: Имя животного: %s, Дата рождения: %s ", getHomeAnimalId(), getClass().getSimpleName(), name, getBirthday());
    }
}
