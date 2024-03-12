package lab5.Server.commands;

import lab5.Server.file.WriteFile;

/**
 * класс для команды insert
 */
public class Insert extends Command{
    int key;
    /**
     * Конструктор класса Insert.
     * Устанавливает название команды.
     */
    public Insert(){
        super("insert");
    }
    /**
     * Метод для обработки команды и вызова метода команды
     * @param EnteredCommand
     */
    public void execute(String EnteredCommand, int key) {
        if (checkCommand(EnteredCommand)) {
            WriteFile.WRITE_FILE.addNew(key, false);
        } else if(this.key == 0){
            System.out.println("Команда не найдена. Введите \"help\" для справки");
        }
    }
}
