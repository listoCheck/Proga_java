package lab5.commands;

import lab5.file.WriteFile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.Scanner;

public class Console {
    ZonedDateTime currentTime = ZonedDateTime.now();

    public void start(boolean script, String path) {
        Scanner sc = new Scanner(System.in);
        if (!script) {
            while (true) {
                String line = sc.nextLine();
                commands(line);
            }
        } else{
            try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
                String line;
                String values = "";
                while ((line = reader.readLine()) != null) {
                    if (line.length() > 6 && line.substring(0, 6).equals("insert")){
                        for (int i = 0; i <= 8; i++) {
                            if (i == 3) {
                                values += currentTime + ",";
                            } else {
                                values += reader.readLine() + ",";
                            }
                        }
                        WriteFile.WRITE_FILE.addNew(Integer.parseInt(line.substring(7)), values);
                    }else{
                        System.out.println(line);
                        commands(line);
                    }
                }
            } catch (IOException e) {
                System.out.println("Ошибка при чтении файла: " + e.getMessage());
            }
        }
    }


    void commands(String line) {
        if (line.contains("help")) {
            new Help().execute(line);
        } else if (line.contains("show")) {
            new Show().execute(line);
        } else if (line.contains("exit")) {
            new Exit().execute(line);
        } else if (line.contains("insert")) {
            new Insert(Integer.parseInt(line.substring(7))).execute(line.substring(0, 6));
        } else if (line.contains("update")) {
            new Update(Integer.parseInt(line.substring(7))).execute(line.substring(0, 6));
        } else if (line.contains("remove ")) {
            new Remove(Integer.parseInt(line.substring(7))).execute(line.substring(0, 6));
        } else if (line.contains("clear")) {
            new Clear().execute(line);
        } else if (line.contains("save")) {
            new Save().execute(line);
        } else if (line.contains("execute_script")) {
            new ExecuteScript(line.substring(15)).execute(line.substring(0, 14));
        }else if (line.contains("remove_greater")) {
            new RemoveGreater(Integer.parseInt(line.substring(15))).execute(line.substring(0, 14));
        }else if (line.contains("remove_lower ")) {
            new RemoveLower(Integer.parseInt(line.substring(13))).execute(line.substring(0, 12));
        }else if (line.contains("remove_lower_key")) {
            new RemoveLowerKey(Integer.parseInt(line.substring(17))).execute(line.substring(0, 16));
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
}

