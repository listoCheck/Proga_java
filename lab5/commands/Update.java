package lab5.commands;

import lab5.file.WriteFile;

public class Update extends Command implements Comandable{
    int key = 0;
    public Update(int key){
        super("update");
        this.key = key;
    }
    @Override
    public void execute(String EnteredCommand) {
        if (checkCommand(EnteredCommand)) {
            if (checkCommand(EnteredCommand)) {
                WriteFile.WRITE_FILE.addNew(key, true);
            } else if(this.key == 0){
                System.out.println("Команда не найдена. Введите \"help\" для справки");
            }
        } else {
            System.out.println("Команда не найдена. Введите \"help\" для справки");
        }
    }
}
