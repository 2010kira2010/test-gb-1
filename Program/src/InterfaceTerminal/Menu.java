package InterfaceTerminal;

import Exceptions.UncorrectDataException;

import java.util.Scanner;

import Models.HomeAnimalType;
import Controller.*;

public class Menu {
    HomeAnimalController homeAnimalController;

    public Menu(HomeAnimalController controller) {
        this.homeAnimalController = controller;
    }

    public void start() {

        System.out.print("\033[H\033[J");
        try (Scanner in = new Scanner(System.in, "ibm866"); Counter count = new Counter()) {

            boolean flag = true;
            int id;
            while (flag) {

                System.out.println(
                        "\n1 - Список всех животных\n2 - Завести новое животное\n3 - Изменить данные о животном\n4 - Что умеет животное\n5 - Дрессировка\n6 - Удалить запись\n0 - Выход");
                String key = in.next();
                switch (key) {
                    case "1":
                        homeAnimalController.getAllHomeAnimal();
                        break;
                    case "2":
                        HomeAnimalType type = menuChoice(in);
                        if (type != null) {
                            try {
                                homeAnimalController.createHomeAnimal(type);
                                count.add();
                                System.out.println("ОК");
                            } catch (UncorrectDataException e) {
                                System.out.println(e.getMessage());
                            }
                        }
                        break;
                    case "3":
                        while (true) {
                            id = menuChoiceHomeAnimal(in);
                            if (id != 0)
                                try {
                                    homeAnimalController.updateHomeAnimal(id);
                                } catch (UncorrectDataException e) {
                                    System.out.println(e.getMessage());
                                }
                            else
                                break;
                        }
                        break;
                    case "4":
                        while (true) {
                            id = menuChoiceHomeAnimal(in);
                            if (id != 0)
                                homeAnimalController.getCommands(id);
                            else
                                break;
                        }
                        break;
                    case "5":
                        id = menuChoiceHomeAnimal(in);
                        if (id != 0)
                            menuTrainHomeAnimal(id, in);
                        break;
                    case "6":
                        id = menuChoiceHomeAnimal(in);
                        if (id != 0)
                            homeAnimalController.delete(id);
                        break;
                    case "0":
                        flag = false;
                        break;
                    default:
                        System.out.println("такого варианта нет");
                        break;
                }
            }
        }
    }

    private HomeAnimalType menuChoice(Scanner in) {
        System.out.println("Какое животное добавить:\n1 - Кошка\n2 - Собака\n3 - Хомяк\n0 - Возврат в основное меню");

        while (true) {
            String key = in.next();
            switch (key) {
                case "1":
                    return HomeAnimalType.Cat;
                case "2":
                    return HomeAnimalType.Dog;
                case "3":
                    return HomeAnimalType.Hamster;
                case "0":
                    return null;
                default:
                    System.out.println("Такого варианта нет, введите число от 0 до 3");
                    break;
            }
        }
    }

    private int menuChoiceHomeAnimal(Scanner in) {
        System.out.println("\nВведите номер животного, 0 для возврата в основное меню: ");
        while (true) {
            int id = in.nextInt();
            in.nextLine();
            if (id == 0)
                return id;
            if (homeAnimalController.getById(id) == null) {
                System.out.println("Животного с таким номером нет, попробуйте еще раз, 0 для возврата в основное меню:");
            } else
                return id;

        }
    }

    private void menuTrainHomeAnimal(int homeAnimalId, Scanner in) {
        Scanner sc = in;
        while (true) {
            System.out.println("Введите новую команду, 0 для возврата в основное меню: ");
            String command = sc.nextLine();
            if (command.length() == 1 && command.equals("0"))
                return;
            if (homeAnimalController.trainHomeAnimal(homeAnimalId, command))
                System.out.println("получилось!");
        }
    }
}
