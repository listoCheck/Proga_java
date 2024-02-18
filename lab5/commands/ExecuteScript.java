package lab5.commands;

/**
 * класс, который обрабатывет скрипт из файлы
 */
public class ExecuteScript extends Command{
    private String file_path;
    /**
     * Конструктор класса ExecuteScript.
     * Устанавливает название команды.
     */
    public ExecuteScript(String file_path){
        super("execute_script");
        this.file_path = file_path;
    }
    /**
     * Метод для обработки команды и вызова метода команды
     * @param EnteredCommand
     */
    public void execute(String EnteredCommand) {
        if (checkCommand(EnteredCommand)) {
            System.out.println("Найден путь: " + file_path);
            new Console().start(true, file_path);
        } else {
            System.out.println("Команда не найдена. Введите \"help\" для справки");
        }
    }
}
