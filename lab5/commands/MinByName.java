package lab5.commands;

import lab5.file.WriteFile;

public class MinByName extends Command implements Comandable{
    public MinByName(){
        super("min_by_name");
    }
    @Override
    public void execute(String EnteredCommand) {
        if (checkCommand(EnteredCommand)) {
            WriteFile.WRITE_FILE.minByName();
        } else {
            System.out.println("Команда не найдена. Введите \"help\" для справки");
        }
    }
}
