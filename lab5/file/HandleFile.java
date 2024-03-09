package lab5.file;

import lab5.ifmo.*;
import java.time.ZonedDateTime;
import java.util.regex.Pattern;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Класс для обработки файла (для создания полей класса dragon и создания экземпляра)
 */
public class HandleFile {
    ZonedDateTime currentTime = ZonedDateTime.now();
    /**
     * метод создающий и возвращающий объект класса дракон
     * @param data
     * @param key
     * @return dragon
     */
    public Dragon createObject(String data, Integer key) {
        Stream<String> stream = Stream.of(data);
        Dragon dragon =  stream
                .map(s -> s.split(","))
                .map(info -> {
                    int id = key;
                    String name = info[1];
                    Coordinates coordinates = new Coordinates((int) Double.parseDouble(info[2]), (int) Double.parseDouble(info[3]));
                    ZonedDateTime creationDate = currentTime;
                    int age = Integer.parseInt(info[5]);
                    Color color = Color.valueOf(info[6]);
                    DragonType type = DragonType.valueOf(info[7]);
                    DragonCharacter character = DragonCharacter.valueOf(info[8]);
                    DragonCave cave = new DragonCave(new DragonCave(Long.parseLong(info[9])).getDepth());
                    return new Dragon(id, name, coordinates, creationDate, age, color, type, character, cave);
                })
                .findFirst()
                .orElse(null);
        return dragon;
    }

}
