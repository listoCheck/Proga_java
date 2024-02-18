package lab5;
import lab5.commands.Console;
import lab5.file.WriteFile;


/**
 * Класс мейн)
 */
public class Main {
    /**
     * Метод, который запускает код
     * @param args
     */
    public static void main(String[] args) {
        WriteFile.WRITE_FILE.WriteFileInMain();
        Console c = new Console();
        System.out.println("команда help - вывести справку по доступным командам");
        c.start(false, "");
    }
}