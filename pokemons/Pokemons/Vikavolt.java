package Pokemons;

import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;
import Attaks.*;
public class Vikavolt extends Pokemon {
    public Vikavolt(String name, int level){
        super(name, level);
        setStats(77, 70, 90, 145, 75, 43);
        setType(Type.BUG, Type.ELECTRIC);
        setMove(new Thunder_Wave(), new Rest(), new Discharge(), new Zap_Cannon());
    }
}
