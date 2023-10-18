package Attaks;

import ru.ifmo.se.pokemon.*;

public class Thunder_Wave extends StatusMove {
    public Thunder_Wave() {
        super(Type.ELECTRIC, 0, 0.9);
    }

    protected void applyOppEffects(Pokemon a) {
        a.addEffect(new Effect().chance(0.25).condition(Status.PARALYZE));
        if (a.getLevel() <= 6 && a.getLevel() >= 1) a.addEffect(new Effect().stat(Stat.SPEED, (int)(a.getStat(Stat.SPEED) * 0.25)));
        else a.addEffect(new Effect().stat(Stat.SPEED, (int)a.getStat(Stat.SPEED) / 2));
    }

    @Override
    protected String describe() {
        return "парализую противника с помощью Thunder Wave";
    }
}