package lab5.commands;

import lab5.file.WriteFile;

public class RemoveGreater extends Command implements Comandable{
    private int id;
    public RemoveGreater(int id){
        super("remove_greater");
        this.id = id;
    }

    @Override
    public void execute(String EnteredCommand) {
        if (checkCommand(EnteredCommand)) {
            WriteFile.WRITE_FILE.dragonsWhoNeedToDel(id, true);
        } else {
            System.out.println("Команда не найдена. Введите \"help\" для справки");
        }
    }
}
