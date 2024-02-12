package lab5.commands;

import lab5.file.WriteFile;

public class PrintFieldDescendingCharacter extends Command implements Comandable{
    public PrintFieldDescendingCharacter(){
        super("print_field_descending_character");
    }
    @Override
    public void execute(String EnteredCommand) {
        if (checkCommand(EnteredCommand)) {
            WriteFile.WRITE_FILE.sortByCharacter();
        } else {
            System.out.println("Команда не найдена. Введите \"help\" для справки");
        }
    }
}
