package lab5.commands;

import lab5.file.WriteFile;

/**
 * класс для команды remove greater
 */
public class RemoveGreater extends Command {
    /**
     * Конструктор класса RemoveGreater.
     * Устанавливает название команды.
     */
    public RemoveGreater(){
        super("remove_greater");
    }
    /**
     * Метод для обработки команды и вызова метода команды
     * @param EnteredCommand
     */
    @Override
    public void execute(String EnteredCommand, int id) {
        if (checkCommand(EnteredCommand)) {
            WriteFile.WRITE_FILE.dragonsWhoNeedToDel(id, true);
        } else {
            System.out.println("Команда не найдена. Введите \"help\" для справки");
        }
    }
}
