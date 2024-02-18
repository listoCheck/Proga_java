package lab5.file;

import lab5.commands.ComandCheck;
import lab5.commands.Console;
import lab5.ifmo.*;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.*;

/**
 * Класс, в котором обрабатывается большинство команд, и записываются данные в файл
 */
public class WriteFile implements Functional {
    public LinkedHashMap<Integer, String> dragons;
    String[] content = {"name: ", "coordinate_x: ", "coordinate_y: ", "creationdate: ", "age: ", "color: ", "type: ", "character: ", "cave: ", ""};
    String[] type_of_content = {"Введите имя дракона", "Координата х, где находится драков", "Координата у, где находится драков", "Вводится автоматически", "Возраст дракона, больший нуля", "Цвет дракона из предложенных: RED, YELLOW, BROWN", "Тип дракона из предложенных: WATER, UNDERGROUND, AIR, FIRE", "Какой Ваш дракон: CUNNING, EVIL, CHAOTIC_EVIL, FICKLE", "Глубина шахты, в которой обитает дракон, большая, либо равная нулю"};
    Scanner sc = new Scanner(System.in);
    public final static WriteFile WRITE_FILE = new WriteFile();
    ZonedDateTime currentTime = ZonedDateTime.now();
    /**
     * метод для чтения данных из xml файла
     */
    public void WriteFileInMain() {
        ReadFile rf = new ReadFile();
        this.dragons = rf.readXml();
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

    /**
     * метод для удаления всех драконов
     */
    public void delDragon() {
        dragons.clear();
    }

    /**
     * метод для команды insert
     * @param key
     * @param update
     */
    public void addNew(int key, boolean update) {
        String values = "";
        for (int i = 0; i <= 8; i++) {
            if (i == 3) {
                values += currentTime + ",";
            } else {
                System.out.print("(" + type_of_content[i] + ") " + content[i]);
                String value;
                do {
                    value = sc.nextLine();
                    if (i == 5 || i == 6 || i == 7){
                       value.toUpperCase(Locale.ROOT);
                    }
                    values += value + ",";
                    System.out.println(value);
                } while (!new ComandCheck().check(i, value));
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

    /**
     * метод для содания нового экземплярая дракона через txt файл
     * @param key
     * @param value
     */
    public void addNew(int key, String value) {
        this.dragons.put(key, value);
        System.out.println(value);
    }

    // методы для команды show и min_by_name

    /**
     * метод для команды show
     */
    public void printDragons() {
        Set<Integer> keys = dragons.keySet();
        for (Integer key : keys) {
            System.out.print("id: " + key);
            int number_in_content = 0;
            //System.out.println(dragons);
            for (String dragon_info : this.dragons.get(key).split(",")) {
                System.out.print(", " + content[number_in_content] + dragon_info);
                //System.out.print(dragon_info);
                number_in_content++;
            }
            System.out.println();
        }
    }

    /**
     * метод для команды min_by_name
     * @param id
     */

    public void printDragons(int id) {
        int number_in_content = 0;
        for (String dragon_info : this.dragons.get(id).split(",")) {
            System.out.print(content[number_in_content] + dragon_info + " ");
            number_in_content++;
        }
        System.out.println();

    }


    //методы для реализации последних команд, которые нужны для удаления объектов из коллекции

    /**
     * метод для удаления предществующих и последствующих объектов
     * @param id
     * @param up
     */
    public void dragonsWhoNeedToDel(int id, boolean up) {
        Set<Integer> keys = dragons.keySet();
        ArrayList<Integer> ids = new ArrayList<>();
        if (up) {
            for (Integer key : keys) {
                if (key == id) {
                    break;
                } else {
                    ids.add(key);
                }
            }
        } else {
            boolean flag = false;
            for (Integer key : keys) {
                if (key == id) {
                    flag = true;
                }
                if (flag && id != key) {
                    ids.add(key);
                }
            }
        }
        delDragon(ids);
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
        System.out.println(types);
        ArrayList<String> sorted = bubbleSort(types);
        System.out.println(sorted);
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
        try (FileWriter writer = new FileWriter("C:\\Users\\admin\\IdeaProjects\\lab2sem\\src\\lab5\\file\\lab5.xml")) {
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
                System.out.println("Данные успешно записаны в файл");
            }
            writer.write("</Dragons>\n");
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }
}
