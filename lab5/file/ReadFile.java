package lab5.file;

import lab5.ifmo.Dragon;

import java.io.FileNotFoundException;
import java.util.*;
import java.io.File;

/**
 * класс, который читает данные из xml файла
 */
public class ReadFile{
    public LinkedHashMap<Integer, String> dragons = new LinkedHashMap<>();
    public LinkedHashMap<Integer, String> dragons2 = new LinkedHashMap<>();
    HandleFile hf = new HandleFile();


    /**
     * метод, который читает xml файл и парсит его при помощи System.scanner
     * @return dragon
     */
    public LinkedHashMap<Integer, String> readXml(){
        try {
            System.out.println("Читаю");
            String info = "";
            String newChars;
            File file = new File("C:\\Users\\admin\\IdeaProjects\\lab2sem\\src\\lab5\\file\\lab5.xml");
            System.out.println(file);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                //System.out.println(line.contains("\t</Dragon_"));
                if (line.contains("\t</Dragon") && !info.isEmpty()){
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
        Set<Integer> keys = dragons.keySet();
        Dragon[] drg = new Dragon[keys.size()];
        int size = 0;
        for (Integer key : keys){
            drg[size] = hf.createObject("," + dragons.get(key), key);
            size++;
        }
        Arrays.sort(drg);
        for (Dragon d : drg){
            dragons2.put(d.getId(), dragons.get(d.getId()));
        }
        return dragons2;
    }
}
