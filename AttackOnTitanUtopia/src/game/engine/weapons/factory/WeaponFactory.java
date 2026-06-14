package game.engine.weapons.factory;

import java.io.IOException;
import java.util.HashMap;
import game.engine.weapons.WeaponRegistry;

public class WeaponFactory {

    // Variables

    private final HashMap<Integer, WeaponRegistry> weaponShop;

    // Constructor

    public WeaponFactory() throws IOException {
        weaponShop = new HashMap<>();
    }

}
