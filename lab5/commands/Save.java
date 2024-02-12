package lab5.commands;

import lab5.file.WriteFile;

public class Save extends Command implements Comandable{
    public Save(){
        super("save");
    }
    public void execute(String EnteredCommand) {
        if (checkCommand(EnteredCommand)) {
            WriteFile.WRITE_FILE.writeXmlFile();
        } else {
            System.out.println("Команда не найдена. Введите \"help\" для справки");
        }
    }
}
