package game.engine.weapons;

import java.util.*;
import game.engine.interfaces.*;
import game.engine.titans.*;

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

    public abstract int turnAttack(PriorityQueue<Titan> laneTitans);

}
