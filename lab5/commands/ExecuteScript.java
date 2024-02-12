package lab5.commands;

public class ExecuteScript extends Command implements Comandable{
    private String file_path;
    public ExecuteScript(String file_path){
        super("execute_script");
        this.file_path = file_path;
    }
    public void execute(String EnteredCommand) {
        if (checkCommand(EnteredCommand)) {
            System.out.println("Найден путь: C:\\Users\\admin\\IdeaProjects\\lab2sem\\src\\lab5\\file\\script.txt");
            new Console().start(true, "C:\\Users\\admin\\IdeaProjects\\lab2sem\\src\\lab5\\file\\script.txt");
        } else {
            System.out.println("Команда не найдена. Введите \"help\" для справки");
        }
    }
}
