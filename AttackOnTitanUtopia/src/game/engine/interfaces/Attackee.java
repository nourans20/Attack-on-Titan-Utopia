package game.engine.interfaces;

// An interface containing the methods available to all objects that get attacked within the game

public interface Attackee {

    // A getter method to retrieve the current health of the attackee

    public int getCurrentHealth();

    // A setter method that changes the Attackee's current health to the input value

    public void setCurrentHealth(int health);

    // A getter method that retrieves the Attackee's resource value

    public int getResourcesValue();

}
