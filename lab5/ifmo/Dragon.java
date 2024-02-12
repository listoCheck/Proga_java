package lab5.ifmo;

import java.time.ZonedDateTime;
public class Dragon implements Comparable{
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private int age; //Значение поля должно быть больше 0
    private Color color; //Поле не может быть null
    private DragonType type; //Поле не может быть null
    private DragonCharacter character; //Поле может быть null
    private DragonCave cave; //Поле может быть null
    public Dragon(int id, String name, Coordinates coordinates, java.time.ZonedDateTime creationDate, int age, Color color, DragonType type, DragonCharacter character, DragonCave cave){
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.age = age;
        this.color = color;
        this.type = type;
        this.character = character;
        this.cave = cave;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public int getAge() {
        return age;
    }

    public Color getColor() {
        return color;
    }

    public DragonType getType() {
        return type;
    }

    public DragonCharacter getCharacter() {
        return character;
    }

    public DragonCave getCave() {
        return cave;
    }

    @Override
    public String toString() {
        String info = "";
        info += "Dragon\n";
        info += "\tId: " + getId() + "\n";
        info += "\tИмя: " + getName() + "\n";
        info += "\tКоординаты: " + getCoordinates() + "\n";
        info += "\tДата создания: " + getCreationDate() + "\n";
        info += "\tВозраст: " + getAge() + "\n";
        info += "\tЦвет: " + getColor() + "\n";
        info += "\tТип: " + getType() + "\n";
        info += "\tХарактер: " + getCharacter() + "\n";
        info += "\tШахта: " + getCave() + "\n";
        if (cave != null) {
            info += "\tРазмер головы: " + cave.getDepth() + "\n";
        }
        info += "\tКоординаты:\n";
        info += "\t\tx = " + coordinates.getX() + "\n";
        info += "\t\ty = " + coordinates.getY();
        return info;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
