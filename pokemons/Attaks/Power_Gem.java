package Attaks;

import ru.ifmo.se.pokemon.SpecialMove;
import ru.ifmo.se.pokemon.Type;

public class Power_Gem extends SpecialMove {
    public Power_Gem(){
        super(Type.ROCK, 80, 100);
    }
    protected String describe() {
        return "Использую Power_Gem";
    }
}
