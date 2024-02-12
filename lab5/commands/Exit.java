package lab5.commands;

public class Exit extends Command implements Comandable{
    public Exit(){
        super("exit");
    }
    @Override
    public void execute(String EnteredCommand) {
        if (checkCommand(EnteredCommand)) {
            System.exit(0);
        } else {
            System.out.println("Команда не найдена. Введите \"help\" для справки");
        }
    }
}
