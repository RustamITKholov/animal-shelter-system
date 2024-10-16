package animalSystem.animals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Animal {
    private static int animalCount = 0; // Статический счётчик животных

    private String type;
    private String name;
    private String birthDate;
    private List<String> commands;

    public Animal(String type, String name, String birthDate, String[] commands) {
        this.type = type;
        this.name = name;
        this.birthDate = birthDate;
        this.commands = new ArrayList<>(Arrays.asList(commands));
        animalCount++; // Увеличиваем счётчик при создании нового животного
    }

    // Метод для получения общего количества животных
    public static int getAnimalCount() {
        return animalCount;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public List<String> getCommands() {
        return commands;
    }

    // Метод для получения списка команд в виде строки
    public String getCommandsString() {
        return String.join(", ", commands);
    }

    // Метод для добавления новой команды
    public void addCommand(String newCommand) {
        if (!commands.contains(newCommand)) {
            commands.add(newCommand);
        }
    }
}
