package animalSystem.animals;

public class Animal {
    private String type;
    private String name;
    private String birthDate;
    private String[] commands;

    public Animal(String type, String name, String birthDate, String[] commands) {
        this.type = type;
        this.name = name;
        this.birthDate = birthDate;
        this.commands = commands;
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

    public String[] getCommands() {
        return commands;
    }

    // Метод для получения списка команд в виде строки
    public String getCommandsString() {
        return String.join(", ", commands);
    }
}
