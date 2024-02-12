package lab5.commands;

import lab5.file.WriteFile;

public class Remove extends Command implements Comandable{
    private int key;
    public Remove(Integer key){
        super("remove");
        this.key = key;
    }
    public void execute(String EnteredCommand) {
        if (checkCommand(EnteredCommand)) {
            WriteFile.WRITE_FILE.delDragon(key);
        } else {
            System.out.println("Команда не найдена. Введите \"help\" для справки");
        }
    }
}
