package lab5.commands;

import lab5.file.WriteFile;

/**
 * класс для сортировки по параметру type
 */
public class PrintFieldDescendingType extends Command{
    /**
     * Конструктор класса PrintFileldDescendingType.
     * Устанавливает название команды.
     */
    public PrintFieldDescendingType(){
        super("print_field_descending_type");
    }
    /**
     * Метод для обработки команды и вызова метода команды
     * @param EnteredCommand
     */
    @Override
    public void execute(String EnteredCommand) {
        if (checkCommand(EnteredCommand)) {
            WriteFile.WRITE_FILE.sortByType();
        } else {
            System.out.println("Команда не найдена. Введите \"help\" для справки");
        }
    }

}
