package game.engine.weapons.factory;

import java.io.IOException;
import java.util.HashMap;
import game.engine.dataloader.DataLoader;
import game.engine.weapons.WeaponRegistry;
import game.engine.exceptions.*;

public class WeaponFactory {

    // Variables

    private final HashMap<Integer, WeaponRegistry> weaponShop;

    // Constructor

    public WeaponFactory() throws IOException {
        weaponShop = DataLoader.readWeaponRegistry();
    }

    // Getters

    public HashMap<Integer, WeaponRegistry> getWeaponShop() {
        return weaponShop;
    }

    public FactoryResponse buyWeapon(int resources, int weaponCode) throws InsufficientResourcesException {

        WeaponRegistry weaponRegistry = weaponShop.get(weaponCode);

        if (resources < weaponRegistry.getPrice())
            throw new InsufficientResourcesException(resources);

        return new FactoryResponse(weaponRegistry.buildWeapon(), resources - weaponRegistry.getPrice());

    }

    public void addWeaponToShop(int code, int price) {
        weaponShop.put(code, new WeaponRegistry(code, price));
    }

    public void addWeaponToShop(int code, int price, int damage, String name) {
        weaponShop.put(code, new WeaponRegistry(code, price, damage, name));
    }

    public void addWeaponToShop(int code, int price, int damage, String name, int minRange, int maxRange) {
        weaponShop.put(code, new WeaponRegistry(code, price, damage, name, minRange, maxRange));
    }

}
