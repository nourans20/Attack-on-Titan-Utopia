package game.engine.weapons;

import java.util.*;
import game.engine.titans.*;

public class VolleySpreadCannon extends Weapon {

    public static final int WEAPON_CODE = 3;
    private final int minRange;
    private final int maxRange;

    public VolleySpreadCannon(int baseDamage, int minRange, int maxRange) {
        super(baseDamage);
        this.minRange = minRange;
        this.maxRange = maxRange;
    }

    public int getMinRange() {
        return minRange;
    }

    public int getMaxRange() {
        return maxRange;
    }

    @Override
    public int turnAttack(PriorityQueue<Titan> laneTitans) {
        int resourcesGained = 0;
        ArrayList<Titan> survivors = new ArrayList<>();

        while (!laneTitans.isEmpty()) {

            Titan titan = laneTitans.remove();

            if (titan.getDistance() >= minRange && titan.getDistance() <= maxRange)
                resourcesGained += titan.takeDamage(getDamage());

            if (!titan.isDefeated())
                survivors.add(titan);
        }

        laneTitans.addAll(survivors);

        return resourcesGained;
    }

}
