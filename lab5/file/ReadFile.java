package lab5.file;

import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.io.File;

public class ReadFile {
    public LinkedHashMap<Integer, String> dragons = new LinkedHashMap<>();
    public LinkedHashMap<Integer, String> readXml(){
        try {
            String info = "";
            String newChars;
            File file = new File("C:\\Users\\admin\\IdeaProjects\\lab2sem\\src\\lab5\\file\\lab5.xml");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                //System.out.println(line.contains("\t</Dragon_"));
                if (line.contains("\t</Dragon_") && !info.isEmpty()){
                    //System.out.println(info);
                    String dragon = info.split(",")[0];
                    dragons.put(Integer.parseInt(dragon), info.substring(dragon.length() + 1));
                    System.out.println("прочитал");
                    info = "";
                }
                newChars = "";
                for (int ch = 1; ch <= line.length() - 1; ch++){
                    if (line.charAt(ch) == '>' && ch != line.length() - 1){
                        ch += 1;
                        while (line.charAt(ch) != '<' && line.length() - 1 != ch){
                            newChars += line.charAt(ch);
                            ch += 1;
                        }
                    }
                }
                if (!newChars.isEmpty()){
                    info += newChars + ",";
                    //System.out.println(newChars);
                }
            }
            scanner.close();
        }catch (FileNotFoundException e){
            System.out.println("Файл не найден: " + e.getMessage());
        }catch (IndexOutOfBoundsException e){
            System.out.println("Проблема с индексом");
        }
        return dragons;
    }
    //public void toHandle(){
    //    HandleFile importFile = new HandleFile();
    //    importFile.hashMapId(readXml());
    //}
}
