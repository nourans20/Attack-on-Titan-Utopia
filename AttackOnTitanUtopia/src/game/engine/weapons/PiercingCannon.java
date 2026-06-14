package game.engine.weapons;

import java.util.ArrayList;
import java.util.PriorityQueue;

import game.engine.titans.*;

public class PiercingCannon extends Weapon {

    public static final int WEAPON_CODE = 1;

    public PiercingCannon(int baseDamage) {
        super(baseDamage);
    }

    @Override
    public int turnAttack(PriorityQueue<Titan> laneTitans) {
        int resourcesGained = 0;
        ArrayList<Titan> survivors = new ArrayList<>();
        for (int i = 0; i < 5 && !laneTitans.isEmpty(); i++) {
            Titan titan = laneTitans.poll();
            resourcesGained += titan.takeDamage(getDamage());
            if (!titan.isDefeated()) {
                survivors.add(titan);
            }
        }
        laneTitans.addAll(survivors);
        return resourcesGained;
    }

}
