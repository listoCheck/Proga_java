package lab5.commands;

import lab5.file.ReadFile;
import lab5.file.WriteFile;

/**
 * класс для команды show
 */
public class Show extends Command{
    /**
     * Конструктор класса Show.
     * Устанавливает название команды.
     */
    public Show(){
        super("show");
    }
    /**
     * Метод для обработки команды и вызова метода команды
     * @param EnteredCommand
     */
    @Override
    public void execute(String EnteredCommand) {
        if (checkCommand(EnteredCommand)) {
            WriteFile.WRITE_FILE.printDragons();
        } else {
            System.out.println("Команда не найдена. Введите \"help\" для справки");
        }
    }
    @Override
    public void execute(String EnteredCommand, int id) {

    }
}
