package animalSystem;

import animalSystem.animals.pets.Cat;
import animalSystem.animals.pets.Dog;
import animalSystem.animals.pets.Hamster;
import animalSystem.animals.pack.Horse;
import animalSystem.animals.pack.Camel;
import animalSystem.animals.pack.Donkey;
import animalSystem.animals.Animal;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AnimalRegistry {
    private List<Animal> animals;
    private Scanner scanner;

    public AnimalRegistry() {
        this.animals = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    // Метод 1: Добавление нового животного
    public void addAnimal() {
        System.out.println("Выберите тип животного:");
        System.out.println("1. Собака");
        System.out.println("2. Кошка");
        System.out.println("3. Хомяк");
        System.out.println("4. Лошадь");
        System.out.println("5. Верблюд");
        System.out.println("6. Осел");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Введите имя: ");
        String name = scanner.nextLine();
        System.out.print("Введите дату рождения (гггг-мм-дд): ");
        String birthDate = scanner.nextLine();
        System.out.print("Введите команды через запятую: ");
        String[] commands = scanner.nextLine().split(",");

        Animal animal = null;

        switch (choice) {
            case 1:
                animal = new Dog(name, birthDate, commands);
                break;
            case 2:
                animal = new Cat(name, birthDate, commands);
                break;
            case 3:
                animal = new Hamster(name, birthDate, commands);
                break;
            case 4:
                animal = new Horse(name, birthDate, commands);
                break;
            case 5:
                animal = new Camel(name, birthDate, commands);
                break;
            case 6:
                animal = new Donkey(name, birthDate, commands);
                break;
            default:
                System.out.println("Неверный выбор типа животного.");
                return;
        }

        if (animal != null) {
            animals.add(animal);
            System.out.println("Животное добавлено в реестр!");
        }
    }

    // Метод 2: Показать список команд животного
    public void showAnimalCommands() {
        System.out.print("Введите имя животного: ");
        String name = scanner.nextLine();
        Animal foundAnimal = findAnimalByName(name);

        if (foundAnimal != null) {
            System.out.println("Команды животного " + foundAnimal.getName() + ": " + foundAnimal.getCommandsString());
        } else {
            System.out.println("Животное не найдено.");
        }
    }

    // Метод 3: Обучить новое животное команде
    public void teachNewCommand() {
        System.out.print("Введите имя животного: ");
        String name = scanner.nextLine();
        Animal foundAnimal = findAnimalByName(name);

        if (foundAnimal != null) {
            System.out.print("Введите новую команду: ");
            String newCommand = scanner.nextLine();
            foundAnimal.addCommand(newCommand);
            System.out.println("Животное обучено новой команде!");
        } else {
            System.out.println("Животное не найдено.");
        }
    }

    // Метод 4: Список животных по дате рождения
    public void listAnimalsByBirthDate() {
        animals.stream()
                .sorted((a1, a2) -> a1.getBirthDate().compareTo(a2.getBirthDate()))
                .forEach(animal -> System.out.println(animal.getName() + " (" + animal.getBirthDate() + ")"));
    }

    // Вспомогательный метод для поиска животного по имени
    private Animal findAnimalByName(String name) {
        for (Animal animal : animals) {
            if (animal.getName().equalsIgnoreCase(name)) {
                return animal;
            }
        }
        return null;
    }
}
