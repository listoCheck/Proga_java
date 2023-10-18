package Attaks;

import ru.ifmo.se.pokemon.*;


public class Discharge extends SpecialMove {
    public Discharge(){
        super(Type.ELECTRIC, 80, 100);
    }
    protected void applyOppEffects(Pokemon a) {
        a.addEffect(new Effect().chance(0.3).condition(Status.PARALYZE));
    }
    @Override
    protected String describe() {
        return "Использую Discharge";
    }
}
