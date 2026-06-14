package game.engine.weapons.factory;

import game.engine.weapons.Weapon;

public class FactoryResponse {

    // Variables

    private final Weapon weapon;
    private final int remainingResources;

    // Constructor

    public FactoryResponse(Weapon weapon, int remainingResources) {
        this.weapon = weapon;
        this.remainingResources = remainingResources;
    }

    // Getters

    public Weapon getWeapon() {
        return weapon;
    }

    public int getRemainingResources() {
        return remainingResources;
    }

}
