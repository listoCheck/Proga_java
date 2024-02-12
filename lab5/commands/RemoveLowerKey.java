package lab5.commands;

import lab5.file.WriteFile;

public class RemoveLowerKey extends Command implements Comandable{
    private int key = 0;
    public RemoveLowerKey(Integer key){
        super("remove_lower_key");
        this.key = key;
    }
    public void execute(String EnteredCommand) {
        if (checkCommand(EnteredCommand)) {
            WriteFile.WRITE_FILE.dragonsWhoNeedToDel(key);
        } else {
            System.out.println("Команда не найдена. Введите \"help\" для справки");
        }
    }
}
