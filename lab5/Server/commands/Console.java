package lab5.Server.commands;

import lab5.Server.Server;
import lab5.Server.file.WriteFile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.LinkedHashMap;
import java.util.Set;

/**
 * Класс, который получает строку из консоли и через объект класса запускает код команды
 */
public class Console {
    ZonedDateTime currentTime = ZonedDateTime.now();
    LinkedHashMap<String, Command> commands = new LinkedHashMap<>();
    String path;

    public Console() {
        commands.put("help", new Help());
        commands.put("show", new Show());
        //commands.put("exit", new Exit());
        //commands.put("insert", new Insert());
        //commands.put("update", new Update());
        commands.put("remove", new Remove());
        commands.put("save", new Save());
        commands.put("clear", new Clear());
        commands.put("execute_script", new ExecuteScript());
        commands.put("remove_greater", new RemoveGreater());
        commands.put("remove_lower", new RemoveLower());
        commands.put("remove_lower_key", new RemoveLowerKey());
        commands.put("min_by_name", new MinByName());
        commands.put("print_field_descending_type", new PrintFieldDescendingType());
        commands.put("print_field_descending_character", new PrintFieldDescendingCharacter());
    }

    /**
     * Метод, в котором находится бесконечный цикл с ожиданием ввода новой строки
     *
     * @param script
     * @param path
     */
    public void start(String line, boolean script, String path) {
        Set<String> keys = commands.keySet();
        //System.out.println(path);
        WriteFile.WRITE_FILE.pathes.add(path);
        if (!script) {
            if (line.contains(":::")){
                WriteFile.WRITE_FILE.insert(line);
            }
            if (keys.contains(line.split(" ")[0])) {
                commands_ref(line);
            } else {
                Server.SERVER.out_to_client += ("Команда не найдна");
            }
        } else {
            try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
                String line1;
                String values = "";
                while ((line1 = reader.readLine()) != null) {
                    //System.out.println("afafafafafafaf " + line.split(" ")[1]);
                    if (line1.contains("insert")) {
                        for (int i = 0; i <= 8; i++) {
                            if (i == 3) {
                                values += currentTime + ",";
                            } else {
                                values += reader.readLine() + ",";
                            }
                        }
                        WriteFile.WRITE_FILE.addNew(Integer.parseInt(line1.substring(7)), values);
                    } else if (line1.contains("execute_script") && WriteFile.WRITE_FILE.pathes.contains(line1.split(" ")[1])) {
                        System.out.println("будет рекурсия, так нельзя");
                    } else {
                        System.out.println(line1);
                        commands_ref(line1);
                    }
                }
            } catch (IOException e) {
                System.out.println("Ошибка при чтении файла: " + e.getMessage());
            }
        }
    }

    /**
     * метод, который сравнивает введенную строку со всеми командами
     *
     * @param line
     */
    void commands_ref(String line) {
        Set<Integer> keys = WriteFile.WRITE_FILE.dragons.keySet();
        //System.out.println(line);
        if (line.contains(" ") && !line.contains("execute")) {
            try {
                //System.out.println((keys.contains(Integer.parseInt(line.split(" ")[1])) && line.contains("insert") || Integer.parseInt(line.split(" ")[1]) < 0));
                if (keys.contains(Integer.parseInt(line.split(" ")[1])) && line.contains("insert") || Integer.parseInt(line.split(" ")[1]) < 0) {
                    System.out.println("Проблема с индексом1");
                } else if (!keys.contains(Integer.parseInt(line.split(" ")[1])) && line.contains("update")) {
                    System.out.println("Проблема с индексом");
                } else {
                    //key = Integer.parseInt(line.split(" ")[1]);
                    //System.out.println("hello");
                    System.out.println(Integer.parseInt(line.split(" ")[1]));
                    commands.get(line.split(" ")[0]).execute(line.split(" ")[0], Integer.parseInt(line.split(" ")[1]));
                }
            } catch (NumberFormatException e) {
                System.out.println("под id подразумевается число большее нуля");
            }

        } else if (commands.containsKey(line)) {
            commands.get(line).execute(line);
        } else if (line.contains("execute_script")) {
            path = line.split(" ")[1];
            commands.get(line.split(" ")[0]).execute(line.split(" ")[0], path);
        } else {
            System.out.println("Проблема с командой, возможная рекурсия");
        }


        /*
        if (line.contains("help")) {
            new Help().execute(line);
        } else if (line.contains("show")) {
            new Show().execute(line);
        } else if (line.contains("exit")) {
            new Exit().execute(line);
        } else if (line.contains("insert ")) {
            try{
                new Insert(Integer.parseInt(line.substring(7))).execute(line.substring(0, 6));
            }catch (NumberFormatException e){
                System.out.println("под id подразумевается число большее нуля");
            }
        } else if (line.contains("update")) {
            try{
                new Update(Integer.parseInt(line.substring(7))).execute(line.substring(0, 6));
            }catch (NumberFormatException e){
                System.out.println("под id подразумевается число большее нуля");
            }
        } else if (line.contains("remove ")) {
            try{
                new Remove(Integer.parseInt(line.substring(7))).execute(line.substring(0, 6));
            }catch (NumberFormatException e){
                System.out.println("под id подразумевается число большее нуля");
            }
        } else if (line.contains("clear")) {
            new Clear().execute(line);
        } else if (line.contains("save")) {
            new Save().execute(line);
        } else if (line.contains("execute_script")) {
            new ExecuteScript(line.substring(15)).execute(line.substring(0, 14));
        }else if (line.contains("remove_greater")) {
            try{
                new RemoveGreater(Integer.parseInt(line.substring(15))).execute(line.substring(0, 14));
            }catch (NumberFormatException e){
                System.out.println("под id подразумевается число большее нуля");
            }
        }else if (line.contains("remove_lower ")) {
            try{
                new RemoveLower(Integer.parseInt(line.substring(13))).execute(line.substring(0, 12));
            }catch (NumberFormatException e){
                System.out.println("под id подразумевается число большее нуля");
            }
        }else if (line.contains("remove_lower_key")) {
            try{
                new RemoveLowerKey(Integer.parseInt(line.substring(17))).execute(line.substring(0, 16));
            }catch (NumberFormatException e){
                System.out.println("под id подразумевается число большее нуля");
            }
        } else if (line.equals("min_by_name")) {
            new MinByName().execute(line);
        } else if (line.equals("print_field_descending_type")) {
            new PrintFieldDescendingType().execute(line);
        } else if (line.equals("print_field_descending_character")) {
            new PrintFieldDescendingCharacter().execute(line);
        } else {
            System.out.println("Команда не найдена");
        }
    }
    // 6bc+4ab+2ac-2ab-4bc-2ac=2ab+2bc

   */
    }
}

