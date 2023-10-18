package Pokemons;

import ru.ifmo.se.pokemon.*;
import Attaks.*;
public class Charjabug extends Pokemon {
    public Charjabug(String name, int level){
        super(name, level);
        setStats(57, 82, 95, 55, 75, 36);
        setType(Type.ELECTRIC, Type.BUG);
        setMove(new Thunder_Wave(), new Rest(), new Discharge());
    }
}
