package lab5.commands;

import lab5.file.WriteFile;

public class PrintFieldDescendingType extends Command implements Comandable{
    public PrintFieldDescendingType(){
        super("print_field_descending_type");
    }
    @Override
    public void execute(String EnteredCommand) {
        if (checkCommand(EnteredCommand)) {
            WriteFile.WRITE_FILE.sortByType();
        } else {
            System.out.println("Команда не найдена. Введите \"help\" для справки");
        }
    }
}
