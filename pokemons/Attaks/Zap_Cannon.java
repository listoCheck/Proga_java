package Attaks;


import ru.ifmo.se.pokemon.*;

public class Zap_Cannon extends StatusMove {
    public Zap_Cannon() {

        super(Type.ELECTRIC, 120, 50);
    }

    @Override
    protected void applyOppEffects(Pokemon a) {
        a.addEffect(new Effect().chance(0.25).condition(Status.PARALYZE));
        if (a.getLevel() <= 6 && a.getLevel() >= 1) a.addEffect(new Effect().stat(Stat.SPEED, (int)(a.getStat(Stat.SPEED) * 0.25)));
        else a.addEffect(new Effect().stat(Stat.SPEED, (int)a.getStat(Stat.SPEED) / 2));
    }

    @Override
    protected String describe() {
        return "парализую противника с помощью Zap_Cannon";
    }
}