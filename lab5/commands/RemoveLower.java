package lab5.commands;

import lab5.file.WriteFile;

/**
 * метод для команды remove lower
 */
public class RemoveLower extends Command {
    private int id;
    /**
     * Конструктор класса RemoveLower.
     * Устанавливает название команды.
     */
    public RemoveLower(int id) {
        super("remove_lower");
        this.id = id;
    }
    /**
     * Метод для обработки команды и вызова метода команды
     * @param EnteredCommand
     */
    @Override
    public void execute(String EnteredCommand) {
        if (checkCommand(EnteredCommand)) {
            WriteFile.WRITE_FILE.dragonsWhoNeedToDel(id, false);
        } else {
            System.out.println("Команда не найдена. Введите \"help\" для справки");
        }
    }
}