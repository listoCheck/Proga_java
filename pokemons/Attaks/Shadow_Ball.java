package Attaks;

import ru.ifmo.se.pokemon.*;

public class Shadow_Ball extends StatusMove {

    public Shadow_Ball(){
        super(Type.GHOST, 80, 1);
    }

    @Override
    protected String describe() {
        return "использую Shadow Ball";
    }

    @Override
    protected void applyOppEffects(Pokemon p) {
        if(Math.random() <= 0.2)
            p.addEffect(new Effect().chance(0.25).stat(Stat.SPECIAL_DEFENSE, -1));
    }
}