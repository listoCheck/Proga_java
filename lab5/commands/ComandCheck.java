package lab5.commands;

import lab5.ifmo.Color;
import lab5.ifmo.DragonCharacter;
import lab5.ifmo.DragonType;

/**
 * класс для проверки команды на валидность
 */
public class ComandCheck {
    /**
     * Конструктор класса ComandCheck
     */
    public ComandCheck() {
    }

    /**
     * метод реализующий проверку комнады
     * @param id
     * @param data
     * @return
     */
    public boolean check(int id, String data) {
        boolean checker = true;
        if (id == 0 && data.isEmpty()) { // name
            checker = false;
        } else if (id == 1) { // x
            try {
                Double.parseDouble(data);
            } catch (NumberFormatException e) {
                System.out.println("значение x должно быть integer");
                checker = false;
            }
        } else if (id == 2) { // y
            try {
                Double.parseDouble(data);
            } catch (NumberFormatException e) {
                System.out.println("значение y должно быть integer");
                checker = false;
            }
        } else if (id == 4){ // age
            try {
                int nulle = Integer.parseInt(data);
                if (nulle <= 0){
                    System.out.println("Учимся читать, что пишется в строке");
                    checker = false;
                }
            } catch (NumberFormatException e) {
                System.out.println("значение age должно быть integer");
                checker = false;
            }
        } else if (id == 5){ // color
            try {
                Color.valueOf(data);
            } catch (IllegalArgumentException e) {
                System.out.println("значение Color должно быть из предложенных");
                checker = false;
            }
        } else if (id == 6){ // type
            try {
                DragonType.valueOf(data);
            } catch (IllegalArgumentException e) {
                System.out.println("значение Type должно быть из предложенных");
                checker = false;
            }
        } else if (id == 7){ // character
            try {
                DragonCharacter.valueOf(data);
            } catch (IllegalArgumentException e) {
                System.out.println("значение Character должно быть из предложенных");
                checker = false;
            }
        }else if (id == 8){ // cave
            try {
                int nulle = Integer.parseInt(data);
                if (nulle <= 0){
                    System.out.println("Учимся читать, что пишется в строке");
                    checker = false;
                }
            } catch (NumberFormatException e) {
                System.out.println("значение age должно быть integer");
                checker = false;
            }
        }
        return checker;
    }
}
