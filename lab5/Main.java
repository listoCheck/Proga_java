package lab5;
import lab5.commands.Console;

public class Main {
    public static void main(String[] args) {
        Console c = new Console();
        System.out.println("команда help - вывести справку по доступным командам");
        c.start(false, "");
    }
}