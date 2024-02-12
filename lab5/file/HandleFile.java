package lab5.file;

import lab5.ifmo.*;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Random;
import java.util.Set;

public class HandleFile {
    private int id;
    private String name;
    private Coordinates coordinates;
    private java.time.ZonedDateTime creationDate;
    private int age;
    private Color color;
    private DragonType type;
    private DragonCharacter character;
    private DragonCave cave;
    ZonedDateTime currentTime = ZonedDateTime.now();
    // метод для очистки полей класса Dragon
    public void clearFields() {
        Random r = new Random();
        this.id = r.nextInt(1, 100);
        this.name = "";
        this.coordinates = null;
        this.creationDate = null;
        this.age = 0;
        this.color = null;
        this.type = null;
        this.character = null;
        this.cave = null;
    }
    // меод в который передеается хэшмапа для последущего создания объекта класса Dragon и записи в xml файл
    //public Dragon hashMapId(LinkedHashMap<Integer, String> mapa) {
    //    Set<Integer> keys = mapa.keySet();
    //    for (int key : keys) {
    //        clearFields();
    //        createObject(mapa.get(key));
    //    }
    //    return null;
    //}
    // метод создающий объект класса Dragon для последующей его записи в xml файл методом writeXmlFile в классе WriteFile
    public Dragon createObject(String data, Integer key) {
        clearFields();
        String[] info = data.split(",");
//        for (String i : info){
//            System.out.println(i);
//        }
        this.id = key;
        this.name = info[1];
        this.coordinates = new Coordinates((int) Double.parseDouble(info[2]), (int)Double.parseDouble(info[3]));
        this.creationDate = currentTime;
        this.age = Integer.parseInt(info[5]);
        this.color = Color.valueOf(info[6]);
        this.type = DragonType.valueOf(info[7]);
        this.character = DragonCharacter.valueOf(info[8]);
        this.cave = new DragonCave(new DragonCave(Long.parseLong(info[9])).getDepth());
        Dragon dragon = new Dragon(id, name, coordinates, creationDate, age, color, type, character, cave);
        return dragon;
    }
//    public void clearFile() throws IOException, SAXException, ParserConfigurationException {
//        File xmlFile = new File("C:\\Users\\admin\\IdeaProjects\\lab2sem\\src\\lab5\\file\\lab5.xml");
//        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
//        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
//        Document doc = dBuilder.parse(xmlFile);
//
//        NodeList nList = doc.getElementsByTagName("Dragon");
//        for (int i = 0; i < nList.getLength(); i++) {
//            Node node = nList.item(i);
//            node.getParentNode().removeChild(node);
//        }
//    }
}
