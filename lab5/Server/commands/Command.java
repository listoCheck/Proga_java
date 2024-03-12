package lab5.Server.commands;

/**
* Класс, от которого будут наследоваться другие команды
 */

public abstract class Command {
    private String name;
    /**
     * Конструктор класса Command.
     * Устанавливает название команды.
     */
    public Command(String name){
        this.name = name;
    }



    /**
     * Метод для проверки корректности ввода команды
     * @param EnteredCommand
     * @return boolean
     */
    public boolean checkCommand(String EnteredCommand) {
        return EnteredCommand.equals(name);
    }

    /**
     * Метод для обработки команды и вызова метода команды
     * @param EnteredCommand
     */
    public void execute(String EnteredCommand) {}
    /**
     * Перегруженный метод для обработки команды и вызова метода команды
     * @param EnteredCommand
     */
    public void execute(String EnteredCommand, int id) {}
    /**
     * Перегруженный метод для обработки команды и вызова метода команды
     * @param EnteredCommand
     */
    public void execute(String EnteredCommand, String newpath) {}
}
