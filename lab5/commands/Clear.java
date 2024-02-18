package lab5.commands;

import lab5.file.WriteFile;

/**
 * класс реализующий команду clear
 */
public class Clear extends Command {
    /**
     * Конструктор класса Clear.
     * Устанавливает название команды.
     */
    public Clear(){
        super("clear");
    }
    /**
     * Метод для обработки команды и вызова метода команды
     * @param EnteredCommand
     */
    public void execute(String EnteredCommand) {
        if (checkCommand(EnteredCommand)) {
            WriteFile.WRITE_FILE.delDragon();
        } else {
            System.out.println("Команда не найдена. Введите \"help\" для справки");
        }
    }
}
