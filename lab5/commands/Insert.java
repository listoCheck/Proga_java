package lab5.commands;

import lab5.file.WriteFile;

public class Insert extends Command implements Comandable{
    int key = 0;
    public Insert(int key){
        super("insert");
        this.key = key;
    }

    @Override
    public void execute(String EnteredCommand) {
        if (checkCommand(EnteredCommand)) {
            WriteFile.WRITE_FILE.addNew(key, false);
        } else if(this.key == 0){
            System.out.println("Команда не найдена. Введите \"help\" для справки");
        }
    }
}
