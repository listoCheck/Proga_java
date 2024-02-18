package lab5.commands;

import lab5.file.WriteFile;

/**
 * класс для комады remove_lower_key
 */
public class RemoveLowerKey extends Command{
    private int key = 0;
    /**
     * Конструктор класса RemoveLowerKey.
     * Устанавливает название команды.
     */
    public RemoveLowerKey(Integer key){
        super("remove_lower_key");
        this.key = key;
    }
    /**
     * Метод для обработки команды и вызова метода команды
     * @param EnteredCommand
     */
    @Override
    public void execute(String EnteredCommand) {
        if (checkCommand(EnteredCommand)) {
            WriteFile.WRITE_FILE.dragonsWhoNeedToDel(key);
        } else {
            System.out.println("Команда не найдена. Введите \"help\" для справки");
        }
    }
}
