package Models;

public enum HomeAnimalType {
    Cat,
    Dog,
    Hamster;

    public static HomeAnimalType getType (int typeId){
        switch (typeId){
            case 1:
                return HomeAnimalType.Cat;
            case 2:
                return HomeAnimalType.Dog;
            case 3:
                return HomeAnimalType.Hamster;
            default:
                return null;
        }
    }
}
