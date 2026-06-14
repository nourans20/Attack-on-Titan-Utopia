package game.engine.base;

import game.engine.interfaces.*;

public class Wall implements Attackee {

    // Variables
    private final int baseHealth;
    private int currentHealth;
    private final int resourcesValue = -1;

    // Constructor
    public Wall(int baseHealth) {
        this.baseHealth = baseHealth;
        this.currentHealth = baseHealth;
    }

    public int getBaseHealth() {
        return baseHealth;
    }

    @Override
    public int getCurrentHealth() {
        return currentHealth;
    }

    @Override
    public void setCurrentHealth(int health) {
        currentHealth = Math.max(health, 0);
    }

    @Override
    public int getResourcesValue() {
        return resourcesValue;
    }

}
