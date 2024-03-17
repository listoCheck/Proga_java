package lab5.Server.file;

import lab5.Server.Server;
import lab5.Server.ifmo.*;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.*;

/**
 * Класс, в котором обрабатывается большинство команд, и записываются данные в файл
 */
public class WriteFile implements Functional{
    public ArrayList<String> pathes = new ArrayList<>();
    public LinkedHashMap<Integer, String> dragons;
    String[] content = {"name: ", "coordinate_x: ", "coordinate_y: ", "creationdate: ", "age: ", "color: ", "type: ", "character: ", "cave: ", ""};
    //String[] type_of_content = {"Введите имя дракона", "Координата х, где находится драков", "Координата у, где находится драков", "Вводится автоматически", "Возраст дракона, больший нуля", "Цвет дракона из предложенных: RED, YELLOW, BROWN", "Тип дракона из предложенных: WATER, UNDERGROUND, AIR, FIRE", "Какой Ваш дракон: CUNNING, EVIL, CHAOTIC_EVIL, FICKLE", "Глубина шахты, в которой обитает дракон, большая, либо равная нулю"};
    //Scanner sc = new Scanner(System.in);
    public final static WriteFile WRITE_FILE = new WriteFile();
    //ZonedDateTime currentTime = ZonedDateTime.now();
    /**
     * метод для чтения данных из xml файла
     */
    public void writeFileInMain() {
        ReadFile rf = new ReadFile();
        dragons = rf.readXml();
        //System.out.println(dragons);
    }
    /**
     * метда для команды clear
     * @param key
     */
    public void delDragon(ArrayList<Integer> key) {
        for (Integer i : key){
            dragons.remove(i);
        }
    }

    /**
     * Метод удаляющий объекта дракона по ключу
     * @param key
     */
    public void delDragon(int key) {
        dragons.remove(key);
    }

    @Override
    public void addNew(int key, boolean update) {

    }
    public void insert(String string){
        Set<Integer> keys = dragons.keySet();
        if (string.contains("insert") && !keys.contains(Integer.parseInt(string.split(" ")[1]))){
            dragons.put(Integer.parseInt(string.split(" ")[1]), string.split(":::")[1]);
            Server.out_to_client += dragons.get(Integer.parseInt(string.split(" ")[1]));
        }else if(string.contains("update") && keys.contains(Integer.parseInt(string.split(" ")[1]))){
            dragons.remove(Integer.parseInt(string.split(" ")[1]));
            dragons.put(Integer.parseInt(string.split(" ")[1]), string.split(":::")[1]);
            Server.out_to_client += dragons.get(Integer.parseInt(string.split(" ")[1]));
        }else{
            Server.out_to_client += "Ошибка с индексом, дракон не создан";
        }
    }

    /**
     * метод для удаления всех драконов
     */
    public void delDragon() {
        dragons.clear();
    }
    /*
    /**
     * метод для команды insert
     * @param key
     * @param update

    public void addNew(int key, boolean update) {
        String values = "";
        for (int i = 0; i <= 8; i++) {
            if (i == 3) {
                values += currentTime + ",";
            } else {
                System.out.print("(" + type_of_content[i] + ") " + content[i]);
                String value = sc.nextLine();
                while (true){
                    if (new ComandCheck().check(i, value)) {
                        values += value + ",";
                        //System.out.println(values);
                        break;
                    }else{
                        value = sc.nextLine();
                    }
                }
            }
        }
        //writeXmlFile(values);
        //System.out.println(values);
        if (update) {
            this.dragons.remove(key);
            this.dragons.put(key, values);
        }else {
            this.dragons.put(key, values);
        }
        System.out.println("Добавлен новый дракон " + key + " " + values.substring(0, values.length()-1));
    }
*/
    /**
     * метод для содания нового экземплярая дракона через txt файл
     * @param key
     * @param value
     */
    public void addNew(int key, String value) {
        this.dragons.put(key, value);
        System.out.println(value);
        Server.out_to_client +=(value);
    }

    // методы для команды show и min_by_name

    /**
     * метод для команды show
     */
    public void printDragons() {
        Set<Integer> keys = dragons.keySet();
        for (Integer key : keys) {
            Server.out_to_client += ("id: " + key);
            int number_in_content = 0;
            //System.out.println(dragons);
            for (String dragon_info : this.dragons.get(key).split(",")) {
                Server.out_to_client += (", " + content[number_in_content] + dragon_info);
                //System.out.print(dragon_info);
                number_in_content++;
            }
            //System.out.println();
            Server.out_to_client += "::";
        }
    }

    /**
     * метод для команды min_by_name
     * @param id
     */

    public void printDragons(int id) {
        int number_in_content = 0;
        for (String dragon_info : this.dragons.get(id).split(",")) {
            Server.out_to_client += (content[number_in_content] + dragon_info + " ");
            number_in_content++;
        }
        Server.out_to_client += "::";

    }


    //методы для реализации последних команд, которые нужны для удаления объектов из коллекции

    /**
     * метод для удаления предществующих и последствующих объектов
     * @param id
     * @param up
     */
    public void dragonsWhoNeedToDel(int id, boolean up) {
        int l = dragons.get(id).length();
        //System.out.println("remove");
        Set<Integer> keys = dragons.keySet();
        ArrayList<String> dragonstr = new ArrayList<>();
        for (Integer key : keys){
            dragonstr.add(dragons.get(key));
        }
        Comparator<String> lengthComparator = Comparator.comparingInt(String::length);
        dragonstr.sort(lengthComparator);

        if (up) {
            System.out.println(l);
            dragonstr.removeIf(element -> element.length() > l);
            //System.out.println("remove1");
        }else {
            dragonstr.removeIf(element -> element.length() < l);
        }
        for (String i : dragonstr){
            System.out.println(i + " " + i.length());
        }
    }

    /**
     * метод для удаления объектов меньших, чем данный
     * @param id
     */
    public void dragonsWhoNeedToDel(int id) {
        dragons.entrySet().removeIf(entry -> entry.getKey() < id);
    }

    /**
     * метод для нахождения минимального имени
     */
    public void minByName() {
        int id = 0, lenName = 10000;
        Set<Integer> keys = dragons.keySet();
        for (Integer key : keys) {
            //System.out.println(dragons.get(key).split(",")[0].length());
            if (dragons.get(key).split(",")[0].length() < lenName) {
                //System.out.println(dragons.get(key).split(",")[0] + " " +dragons.get(key).split(",")[0].length() + " " + lenName);
                id = key;
                lenName = dragons.get(key).split(",")[0].length();
            }
        }
        printDragons(id);
    }

    // следующие 2 метода нужны для сортировки по какому-либо признаку (type и character)

    /**
     * метод для сортировки по type
     */
    public void sortByType() {
        LinkedHashMap<String, Integer> types = new LinkedHashMap<>();
        types.put("WATER", 0);
        types.put("UNDERGROUND", 0);
        types.put("AIR", 0);
        types.put("FIRE", 0);
        Set<Integer> keys = dragons.keySet();
        for (Integer key : keys) {
            String type = dragons.get(key).split(",")[6];
            //System.out.println(types.get(type) + type);
            types.put(type, types.get(type) + 1);
        }
        //System.out.println(types);
        ArrayList<String> sorted = bubbleSort(types);
        //System.out.println(sorted);
        for (String type : sorted) {
            for (Integer key : keys) {
                if (dragons.get(key).split(",")[6].equals(type)) {
                    printDragons(key);
                }
            }
        }
    }

    /**
     * метод для сортировки по character
     */
    public void sortByCharacter() {
        LinkedHashMap<String, Integer> characters = new LinkedHashMap<>();
        characters.put("CUNNING", 0);
        characters.put("EVIL", 0);
        characters.put("CHAOTIC_EVIL", 0);
        characters.put("FICKLE", 0);
        Set<Integer> keys = dragons.keySet();
        for (Integer key : keys) {
            String type = dragons.get(key).split(",")[7];
            characters.put(type, characters.get(type) + 1);
        }
        ArrayList<String> sorted = bubbleSort(characters);
        for (String type : sorted) {
            for (Integer key : keys) {
                if (dragons.get(key).split(",")[7].equals(type)) {
                    printDragons(key);
                }
            }
        }
    }
    // метод для сортировки типов

    /**
     * метод для сортировки по типу или персонажу, от большего к меньшему
     * @param trash
     * @return sorted
     */
    public ArrayList<String> bubbleSort(LinkedHashMap<String, Integer> trash) {
        Set<String> keys = trash.keySet();
        ArrayList<String> sort = new ArrayList<>(keys);
        int x = sort.size();
        while (x != 0)
            for (int i = 1; i < sort.size() - 1; i++) {
                int first = trash.get(sort.get(i-1));
                int second = trash.get(sort.get(i));
                if (first > second) {
                    x--;
                }else{
                    String f = sort.get(i-1);
                    String s = sort.get(i);
                    sort.set(i-1, s);
                    sort.set(i, f);
                }
            }
        return sort;
    }

    /**
     * метод для записи в xml файл
     */
    public void writeXmlFile() {

        try (FileWriter writer = new FileWriter("C:\\Users\\admin\\IdeaProjects\\lab2sem\\src\\lab5\\Server\\file\\lab5.xml")) {
            writer.write("<Dragons>\n");
            Set<Integer> keys = dragons.keySet();
            for (Integer key : keys) {
                Dragon dragon = new HandleFile().createObject("," + dragons.get(key), key);
                writer.write("\t<Dragon>\n");
                writer.write("\t\t<id>" + dragon.getId() + "</id>\n");
                writer.write("\t\t<name>" + dragon.getName() + "</name>\n");
                writer.write("\t\t<coordinates>\n");
                writer.write("\t\t\t<x>" + dragon.getCoordinates().getX() + "</x>\n");
                writer.write("\t\t\t<y>" + dragon.getCoordinates().getY() + "</y>\n");
                writer.write("\t\t</coordinates>\n");
                writer.write("\t\t<creationdate>" + dragon.getCreationDate() + "</creationdate>\n");
                writer.write("\t\t<age>" + dragon.getAge() + "</age>\n");
                writer.write("\t\t<color>" + dragon.getColor() + "</color>\n");
                writer.write("\t\t<type>" + dragon.getType() + "</type>\n");
                writer.write("\t\t<character>" + dragon.getCharacter() + "</character>\n");
                writer.write("\t\t<cave>" + dragon.getCave().getDepth() + "</cave>\n");
                writer.write("\t</Dragon>\n");
                Server.out_to_client += ("Данные успешно записаны в файл") + "::";
            }
            writer.write("</Dragons>\n");
        } catch (FileNotFoundException e) {
            Server.out_to_client += ("Файл не найден: " + e.getMessage());
        } catch (IOException e) {
            Server.out_to_client += ("Ошибка при записи в файл: " + e.getMessage());
        }
    }

}