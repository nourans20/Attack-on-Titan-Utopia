package game.engine.weapons;

import java.util.*;
import game.engine.titans.*;

public class SniperCannon extends Weapon {

    public static final int WEAPON_CODE = 2;

    public SniperCannon(int baseDamage) {
        super(baseDamage);
    }

    @Override
    public int turnAttack(PriorityQueue<Titan> laneTitans) {

        int resourcesGained = 0;

        if (!laneTitans.isEmpty()) {
            Titan titan = laneTitans.peek();
            resourcesGained += titan.takeDamage(getDamage());
            if (titan.isDefeated()) {
                laneTitans.remove(titan);
            }
        }

        return resourcesGained;

    }

}
