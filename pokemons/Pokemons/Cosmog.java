package Pokemons;

import Attaks.*;
import ru.ifmo.se.pokemon.*;

public class Cosmog extends Pokemon{
    public Cosmog(String name, int level){
        super(name, level);
        setStats(43, 29, 31, 29, 31, 37);
        setType(Type.PSYCHIC);
        setMove(new Shadow_Ball(), new Natures_Madnes(), new Facade(), new Moonblast());
    }
}
