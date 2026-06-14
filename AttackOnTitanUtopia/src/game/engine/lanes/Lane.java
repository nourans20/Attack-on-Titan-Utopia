package game.engine.lanes;

import java.util.*;
import game.engine.base.*;
import game.engine.titans.*;
import game.engine.weapons.Weapon;

public class Lane implements Comparable<Lane> {

    // Variables

    private final Wall laneWall;
    private int dangerLevel;
    private final PriorityQueue<Titan> titans;
    private final ArrayList<Weapon> weapons;

    // Constructor

    public Lane(Wall laneWall) {
        this.laneWall = laneWall;
        this.dangerLevel = 0;
        this.titans = new PriorityQueue<>();
        this.weapons = new ArrayList<>();
    }

    // Getters and Setters

    public Wall getLaneWall() {
        return laneWall;
    }

    public int getDangerLevel() {
        return dangerLevel;
    }

    public void setDangerLevel(int dangerLevel) {
        this.dangerLevel = Math.max(dangerLevel, 0);
    }

    public PriorityQueue<Titan> getTitans() {
        return titans;
    }

    public ArrayList<Weapon> getWeapons() {
        return weapons;
    }

    @Override
    public int compareTo(Lane o) {
        return Integer.compare(this.dangerLevel, o.dangerLevel);
    }

    // Methods

    public void addTitan(Titan titan) {
        titans.add(titan);
    }

    public void addWeapon(Weapon weapon) {
        weapons.add(weapon);
    }

    public void moveLaneTitans() {
        int size = titans.size();
        ArrayList<Titan> processed = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Titan titan = titans.remove();
            if (!titan.hasReachedTarget())
                titan.move();
            processed.add(titan);
        }
        titans.addAll(processed);
    }

    public int performLaneTitansAttacks() {
        int resourcesGained = 0;
        int size = titans.size();
        ArrayList<Titan> processed = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Titan titan = titans.remove();
            if (!titan.hasReachedTarget())
                resourcesGained += titan.attack(laneWall);
            processed.add(titan);
        }
        titans.addAll(processed);
        return resourcesGained;
    }

    public int performLaneWeaponsAttacks() {
        int resourcesGained = 0;
        for (Weapon weapon : weapons)
            resourcesGained += weapon.turnAttack(titans);
        return resourcesGained;
    }

    public boolean isLaneLost() {
        return laneWall.getCurrentHealth() <= 0;
    }

    public void updateLaneDangerLevel() {
        int newDangerLevel = 0;
        for (Titan titan : titans)
            newDangerLevel += titan.getDangerLevel();
        setDangerLevel(newDangerLevel);
    }

}
