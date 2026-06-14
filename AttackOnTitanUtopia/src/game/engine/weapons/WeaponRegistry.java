package game.engine.weapons;

public class WeaponRegistry {

    // Variables
    private final int code;
    private int price;
    private int damage;
    private String name;
    private int minRange;
    private int maxRange;

    // Constructors
    public WeaponRegistry(int code, int price) {
        this.code = code;
        this.price = price;
        this.damage = 0;
        this.name = null;
        this.minRange = 0;
        this.maxRange = 0;
    }

    public WeaponRegistry(int code, int price, int damage, String name) {
        this.code = code;
        this.price = price;
        this.damage = damage;
        this.name = name;
        this.minRange = 0;
        this.maxRange = 0;
    }

    public WeaponRegistry(int code, int price, int damage, String name, int minRange, int maxRange) {
        this.code = code;
        this.price = price;
        this.damage = damage;
        this.name = name;
        this.minRange = minRange;
        this.maxRange = maxRange;
    }

    // Getters
    public int getCode() {
        return code;
    }

    public int getPrice() {
        return price;
    }

    public int getDamage() {
        return damage;
    }

    public String getName() {
        return name;
    }

    public int getMinRange() {
        return minRange;
    }

    public int getMaxRange() {
        return maxRange;
    }

    public Weapon buildWeapon() {

        Weapon weapon = null;

        if (code == 1)
            weapon = new PiercingCannon(damage);
        else if (code == 2)
            weapon = new SniperCannon(damage);
        else if (code == 3)
            weapon = new VolleySpreadCannon(damage, minRange, maxRange);
        else if (code == 4)
            weapon = new WallTrap(damage);

        return weapon;

    }

}