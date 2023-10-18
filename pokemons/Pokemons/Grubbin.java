package Pokemons;

import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;
import Attaks.*;
public class Grubbin extends Pokemon {
    public Grubbin(String name, int level){
        super(name, level);
        setStats(47, 62, 45, 55, 45, 46);
        setType(Type.BUG);
        setMove(new Thunder_Wave(), new Rest());
    }
}
