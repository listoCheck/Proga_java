package lab5.commands;

import lab5.file.WriteFile;

public class RemoveLower extends Command implements Comandable {
    private int id;

    public RemoveLower(int id) {
        super("remove_lower");
        this.id = id;
    }

    @Override
    public void execute(String EnteredCommand) {
        if (checkCommand(EnteredCommand)) {
            WriteFile.WRITE_FILE.dragonsWhoNeedToDel(id, false);
        } else {
            System.out.println("Команда не найдена. Введите \"help\" для справки");
        }
    }
}