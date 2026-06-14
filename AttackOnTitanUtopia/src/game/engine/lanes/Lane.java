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

}
