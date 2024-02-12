package lab5.file;

import java.util.ArrayList;

public interface Functional {
    void delDragon();

    void delDragon(ArrayList<Integer> key);
    void delDragon(int key);

    void addNew(int key, boolean update);

    void addNew(int key, String value);

    void printDragons();

    void printDragons(int id);

    void dragonsWhoNeedToDel(int id, boolean up);

    void dragonsWhoNeedToDel(int id);

    void minByName();

    void sortByCharacter();

    void writeXmlFile();

}
