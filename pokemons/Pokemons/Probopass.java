package Pokemons;

import Attaks.*;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Probopass extends Pokemon {
    public Probopass(String name, int level){
        super(name, level);
        setStats(60, 55, 145, 75, 150, 40);
        setType(Type.ROCK, Type.STEEL);
        setMove(new Power_Gem(), new Discharge(), new Facade(), new Magnet_Bomb());
    }
}
