package lab5.commands;

import lab5.file.ReadFile;
import lab5.file.WriteFile;

public class Show extends Command implements Comandable{
    public Show(){
        super("show");
    }
    @Override
    public void execute(String EnteredCommand) {
        if (checkCommand(EnteredCommand)) {
            WriteFile.WRITE_FILE.printDragons();
        } else {
            System.out.println("Команда не найдена. Введите \"help\" для справки");
        }
    }
}
