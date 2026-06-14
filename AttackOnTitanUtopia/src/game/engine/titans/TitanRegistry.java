package game.engine.titans;

public class TitanRegistry {

    // Variables
    private final int code;
    private final int baseHealth;
    private final int baseDamage;
    private final int heightInMeters;
    private final int speed;
    private final int resourcesValue;
    private final int dangerLevel;

    // Constructor
    public TitanRegistry(int code, int baseHealth, int baseDamage, int heightInMeters, int speed, int resourcesValue,
            int dangerLevel) {
        this.code = code;
        this.baseHealth = baseHealth;
        this.baseDamage = baseDamage;
        this.heightInMeters = heightInMeters;
        this.speed = speed;
        this.resourcesValue = resourcesValue;
        this.dangerLevel = dangerLevel;
    }

    // Getters
    public int getCode() {
        return code;
    }

    public int getBaseHealth() {
        return baseHealth;
    }

    public int getBaseDamage() {
        return baseDamage;
    }

    public int getHeightInMeters() {
        return heightInMeters;
    }

    public int getSpeed() {
        return speed;
    }

    public int getResourcesValue() {
        return resourcesValue;
    }

    public int getDangerLevel() {
        return dangerLevel;
    }

    public Titan spawnTitan(int distanceFromBase) {

        Titan titan = null;

        if (code == 1)
            titan = new PureTitan(baseHealth, baseDamage, heightInMeters, distanceFromBase, speed, resourcesValue,
                    dangerLevel);
        else if (code == 2)
            titan = new AbnormalTitan(baseHealth, baseDamage, heightInMeters, distanceFromBase, speed, resourcesValue,
                    dangerLevel);
        else if (code == 3)
            titan = new ArmoredTitan(baseHealth, baseDamage, heightInMeters, distanceFromBase, speed, resourcesValue,
                    dangerLevel);
        else if (code == 4)
            titan = new ColossalTitan(baseHealth, baseDamage, heightInMeters, distanceFromBase, speed, resourcesValue,
                    dangerLevel);

        return titan;

    }

}