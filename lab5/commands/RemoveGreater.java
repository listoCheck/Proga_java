package lab5.commands;

import lab5.file.WriteFile;

/**
 * класс для команды remove greater
 */
public class RemoveGreater extends Command {
    private int id;
    /**
     * Конструктор класса RemoveGreater.
     * Устанавливает название команды.
     */
    public RemoveGreater(int id){
        super("remove_greater");
        this.id = id;
    }
    /**
     * Метод для обработки команды и вызова метода команды
     * @param EnteredCommand
     */
    @Override
    public void execute(String EnteredCommand) {
        if (checkCommand(EnteredCommand)) {
            WriteFile.WRITE_FILE.dragonsWhoNeedToDel(id, true);
        } else {
            System.out.println("Команда не найдена. Введите \"help\" для справки");
        }
    }
}
