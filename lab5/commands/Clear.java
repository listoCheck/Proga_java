package lab5.commands;

import lab5.file.WriteFile;

public class Clear extends Command implements Comandable{
    public Clear(){
        super("clear");
    }
    public void execute(String EnteredCommand) {
        if (checkCommand(EnteredCommand)) {
            WriteFile.WRITE_FILE.delDragon();
        } else {
            System.out.println("Команда не найдена. Введите \"help\" для справки");
        }
    }
}
