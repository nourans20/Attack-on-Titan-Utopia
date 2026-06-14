package game.engine.interfaces;

// An interface containing the methods available to all attackers within the game

public interface Attacker {

    // A getter method that retrieves the damage value that the attacker inflicts

    public int getDamage();

    default int attack(Attackee target) {
        return target.takeDamage(getDamage());
    }

}
