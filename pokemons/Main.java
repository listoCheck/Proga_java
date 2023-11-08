import ru.ifmo.se.pokemon.*;
import Pokemons.*;

public class Main {
    public static void main(String[] args) {
        Battle b = new Battle();
        b.addAlly(new Charjabug("Конченный идиот", 1));
        b.addAlly(new Cosmog("Самый крутой мужик в мире", 2));
        b.addAlly(new Grubbin("Горячая чикса", 1));
        b.addFoe(new Nosepass("Злодей британец", 3));
        b.addFoe(new Probopass("Так себе шутник", 1));
        b.addFoe(new Vikavolt("Какой-то мужик", 2));
        b.go();
    }
}