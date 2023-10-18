package Attaks;

import ru.ifmo.se.pokemon.*;

public class Natures_Madnes extends StatusMove {
    public Natures_Madnes(){
        super(Type.FAIRY, 0, 90);
    }
    protected String describe() {
        return "использую Natures_Madnes";
    }
    public void applyOppDamage(Pokemon poc, double damage){
        poc.setMod(Stat.HP, -2*(int)Math.round(damage));
    }
}
