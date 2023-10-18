package Pokemons;

import Attaks.*;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Nosepass extends Pokemon {
    public Nosepass(String name, int level){
        super(name, level);
        setStats(30, 45, 135, 45, 90, 30);
        setType(Type.ROCK);
        setMove(new Power_Gem(), new Facade(), new Discharge());
    }
}
