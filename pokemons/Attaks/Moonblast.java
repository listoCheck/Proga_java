package Attaks;

import ru.ifmo.se.pokemon.*;


public class Moonblast extends StatusMove {
    public Moonblast(){
        super(Type.FAIRY, 95, 100);
    }
    protected void applyOppEffects(Pokemon a) {
        a.addEffect(new Effect().chance(0.3).condition(Status.BURN));
    }
    protected String describe() {
        return "Использую Moonblast";
    }

}
