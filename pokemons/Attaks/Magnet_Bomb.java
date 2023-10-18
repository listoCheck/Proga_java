package Attaks;

import ru.ifmo.se.pokemon.PhysicalMove;
import ru.ifmo.se.pokemon.Type;

public class Magnet_Bomb extends PhysicalMove{
    public Magnet_Bomb(){
        super(Type.STEEL, 60, Double.POSITIVE_INFINITY);
    }
    protected String describe() {
        return "Использую Magnet_Bomb";
    }
}
