package Models;

public class HomeAnimalCreator extends Creator {
    @Override
    protected HomeAnimal createNewHomeAnimal (HomeAnimalType type) {

        switch (type) {
            case Cat:
                return new Cat();
            case Dog:
                return new Dog();
            case Hamster:
                return new Hamster();
        }
        return null;
    }
}
