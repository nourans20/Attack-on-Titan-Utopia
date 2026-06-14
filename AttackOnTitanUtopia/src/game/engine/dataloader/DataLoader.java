package game.engine.dataloader;

import java.io.*;
import java.util.*;
import game.engine.titans.TitanRegistry;
import game.engine.weapons.WeaponRegistry;
import game.engine.exceptions.InvalidCSVFormat;

public class DataLoader {

    // Attributes - static final (class level, initialized once)
    private static final String TITANS_FILE_NAME = "titans.csv";
    private static final String WEAPONS_FILE_NAME = "weapons.csv";

    // Reads titans.csv and returns a HashMap of TitanRegistry objects
    public static HashMap<Integer, TitanRegistry> readTitanRegistry() throws IOException {
        HashMap<Integer, TitanRegistry> titanRegistry = new HashMap<>();

        BufferedReader br = new BufferedReader(new FileReader(TITANS_FILE_NAME));
        String line;

        while ((line = br.readLine()) != null) {
            String[] values = line.split(",");

            if (values.length != 7)
                throw new InvalidCSVFormat(line);

            int code = Integer.parseInt(values[0].trim());
            int baseHealth = Integer.parseInt(values[1].trim());
            int baseDamage = Integer.parseInt(values[2].trim());
            int heightInMeters = Integer.parseInt(values[3].trim());
            int speed = Integer.parseInt(values[4].trim());
            int resourcesValue = Integer.parseInt(values[5].trim());
            int dangerLevel = Integer.parseInt(values[6].trim());

            TitanRegistry titan = new TitanRegistry(code, baseHealth, baseDamage,
                    heightInMeters, speed, resourcesValue, dangerLevel);
            titanRegistry.put(code, titan);
        }

        br.close();
        return titanRegistry;
    }

    // Reads weapons.csv and returns a HashMap of WeaponRegistry objects
    public static HashMap<Integer, WeaponRegistry> readWeaponRegistry() throws IOException {
        HashMap<Integer, WeaponRegistry> weaponRegistry = new HashMap<>();

        BufferedReader br = new BufferedReader(new FileReader(WEAPONS_FILE_NAME));
        String line;

        while ((line = br.readLine()) != null) {
            String[] values = line.split(",");

            if (values.length != 4 && values.length != 6)
                throw new InvalidCSVFormat(line);

            int code = Integer.parseInt(values[0].trim());
            int price = Integer.parseInt(values[1].trim());
            int damage = Integer.parseInt(values[2].trim());
            String name = values[3].trim();

            WeaponRegistry weapon;

            if (values.length == 6) {
                int minRange = Integer.parseInt(values[4].trim());
                int maxRange = Integer.parseInt(values[5].trim());
                weapon = new WeaponRegistry(code, price, damage, name, minRange, maxRange);
            } else {
                weapon = new WeaponRegistry(code, price, damage, name);
            }

            weaponRegistry.put(code, weapon);
        }

        br.close();
        return weaponRegistry;
    }
}