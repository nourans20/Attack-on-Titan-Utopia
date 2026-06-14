package game.engine.weapons;

import game.engine.interfaces.*;

public abstract class Weapon implements Attacker {

    // Variables

    private final int baseDamage;

    // Constructor
    public Weapon(int baseDamage) {
        this.baseDamage = baseDamage;
    }

    @Override
    public int getDamage() {
        return baseDamage;
    }

}
