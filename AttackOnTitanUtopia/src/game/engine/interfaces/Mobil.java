package game.engine.interfaces;

// An Interface Containing the methods available to all objects that can move within the game

public interface Mobil {

    // A getter method that retrieves the Mobil's distance from its target

    public int getDistance();

    // A setter method that changes the Mobil's distance from its target to the
    // input value

    public void setDistance(int distance);

    // A getter method that retrieves the Mobil's movment speed

    public int getSpeed();

    // A setter method that changes the Mobil's movment speed to the input value

    public void setSpeed(int speed);

    default boolean hasReachedTarget() {
        return getDistance() <= 0;
    }

    default boolean move() {
        setDistance(getDistance() - getSpeed());
        return hasReachedTarget();
    }

}
