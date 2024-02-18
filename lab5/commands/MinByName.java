package lab5.commands;

import lab5.file.WriteFile;

/**
 * класс для команды min_by_name
 */
public class MinByName extends Command{
    /**
     * Конструктор класса Min_by_name.
     * Устанавливает название команды.
     */
    public MinByName(){
        super("min_by_name");
    }
    /**
     * Метод для обработки команды и вызова метода команды
     * @param EnteredCommand
     */
    @Override
    public void execute(String EnteredCommand) {
        if (checkCommand(EnteredCommand)) {
            WriteFile.WRITE_FILE.minByName();
        } else {
            System.out.println("Команда не найдена. Введите \"help\" для справки");
        }
    }
}
