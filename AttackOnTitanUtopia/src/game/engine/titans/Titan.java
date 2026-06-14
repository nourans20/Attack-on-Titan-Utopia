package game.engine.titans;

import game.engine.interfaces.*;

/*A class representing the Titans available in the game. A titan class can do the following
functionalities:
1. move
2. attack
3. gets attacked
4. gets compared based on it's distance from the base/wall
No objects of type Titan can be instantiated.*/

public abstract class Titan implements Mobil, Attackee, Attacker, Comparable<Titan> {

    // Variables
    private final int baseHealth;
    private int currentHealth;
    private final int baseDamage;
    private final int heightInMeters;
    private int distanceFromBase;
    private int speed;
    private final int resourcesValue;
    private final int dangerLevel;

    // Constructor
    public Titan(int baseHealth, int baseDamage, int heightInMeters, int distanceFromBase, int speed,
            int resourcesValue, int dangerLevel) {

        this.baseHealth = baseHealth;
        this.currentHealth = baseHealth;
        this.baseDamage = baseDamage;
        this.heightInMeters = heightInMeters;
        this.setDistance(distanceFromBase);
        this.speed = speed;
        this.resourcesValue = resourcesValue;
        this.dangerLevel = dangerLevel;
    }

    // Getter methods
    public int getBaseHealth() {
        return baseHealth;
    }

    @Override
    public int getCurrentHealth() {
        return currentHealth;
    }

    public int getBaseDamage() {
        return baseDamage;
    }

    public int getHeightInMeters() {
        return heightInMeters;
    }

    @Override
    public int getDistance() {
        return distanceFromBase;
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public int getResourcesValue() {
        return resourcesValue;
    }

    public int getDangerLevel() {
        return dangerLevel;
    }

    // Setter methods
    @Override
    public void setCurrentHealth(int health) {
        this.currentHealth = health;
    }

    @Override
    public void setDistance(int distance) {
        this.distanceFromBase = distance;
    }

    @Override
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public int getDamage() {
        return baseDamage;
    }

    @Override
    public int compareTo(Titan o) {
        return this.distanceFromBase - o.distanceFromBase;
    }
}