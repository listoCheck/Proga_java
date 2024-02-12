package lab5.commands;


import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class Help extends Command implements Comandable{
    public Help(){
        super("help");
    }
    @Override
    public void execute(String EnteredCommand) {
        if (checkCommand(EnteredCommand)) {
            Map<String, String> commands = new LinkedHashMap<>();
            commands.put("info", "вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)");
            commands.put("show", "вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
            commands.put("insert id", "добавить новый элемент с заданным ключом");
            commands.put("update id", "обновить значение элемента коллекции, id которого равен заданному");
            commands.put("remove id", "удалить элемент из коллекции по его ключу");
            commands.put("clear", "очистить коллекцию");
            commands.put("save", "сохранить коллекцию в файл");
            commands.put("execute_script file_name", "считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме");
            commands.put("exit", "завершить программу (без сохранения в файл)");
            commands.put("remove_greater id", "удалить из коллекции все элементы, превышающие заданный");
            commands.put("remove_lower id", "удалить из коллекции все элементы, меньшие, чем заданный");
            commands.put("remove_lower_key id", "удалить из коллекции все элементы, ключ которых меньше, чем заданный");
            commands.put("min_by_name", "вывести любой объект из коллекции, значение поля name которого является минимальным");
            commands.put("print_field_descending_type", "вывести значения поля type всех элементов в порядке убывания");
            commands.put("print_field_descending_character", "вывести значения поля character всех элементов в порядке убывания");
            Set<Map.Entry<String, String>> pairs = commands.entrySet();
            for (Map.Entry<String, String> i : pairs) {
                System.out.println(i.getKey() + ": " + i.getValue());
            }
        } else {
            System.out.println("Команда не найдена. Введите \"help\" для справки");
        }
    }

}
