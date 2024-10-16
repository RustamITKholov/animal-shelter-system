package animalSystem;

import java.util.Scanner;
import animalSystem.animals.Animal;

public class Main {
    public static void main(String[] args) {
        AnimalRegistry registry = new AnimalRegistry();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n=== Реестр домашних животных ===");
            System.out.println("1. Добавить новое животное");
            System.out.println("2. Показать команды животного");
            System.out.println("3. Обучить животное новой команде");
            System.out.println("4. Показать список животных по дате рождения");
            System.out.println("5. Показать общее количество животных");
            System.out.println("6. Выход");
            System.out.print("Выберите опцию: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    registry.addAnimal();
                    break;
                case 2:
                    registry.showAnimalCommands();
                    break;
                case 3:
                    registry.teachNewCommand();
                    break;
                case 4:
                    registry.listAnimalsByBirthDate();
                    break;
                case 5:
                    System.out.println("Общее количество животных: " + Animal.getAnimalCount());
                    break;
                case 6:
                    running = false;
                    System.out.println("Выход из программы...");
                    break;
                default:
                    System.out.println("Неверный выбор. Пожалуйста, попробуйте снова.");
            }
        }

        scanner.close();
    }
}