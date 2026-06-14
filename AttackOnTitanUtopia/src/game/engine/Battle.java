package game.engine;

import java.io.*;
import java.util.*;
import game.engine.base.Wall;
import game.engine.dataloader.DataLoader;
import game.engine.exceptions.*;
import game.engine.lanes.Lane;
import game.engine.titans.*;
import game.engine.weapons.*;
import game.engine.weapons.factory.FactoryResponse;
import game.engine.weapons.factory.WeaponFactory;

public class Battle {

    // Variables

    private static final int[][] PHASES_APPROACHING_TITANS = {
            { 1, 1, 1, 2, 1, 3, 4 },
            { 2, 2, 2, 1, 3, 3, 4 },
            { 4, 4, 4, 4, 4, 4, 4 }
    };
    private static final int WALL_BASE_HEALTH = 10000;

    private int numberOfTurns;
    private int resourcesGathered;
    private BattlePhase battlePhase = BattlePhase.EARLY;
    private int numberOfTitansPerTurn = 1;
    private int score;
    private int titanSpawnDistance;
    private final WeaponFactory weaponFactory;
    private final HashMap<Integer, TitanRegistry> titansArchives;
    private final ArrayList<Titan> approachingTitans;
    private final PriorityQueue<Lane> lanes;
    private final ArrayList<Lane> originalLanes;

    // Constructor
    public Battle(int numberOfTurns, int score, int titanSpawnDistance,
            int initialNumOfLanes, int initialResourcesPerLane) throws IOException {

        this.numberOfTurns = numberOfTurns;
        this.score = score;
        this.titanSpawnDistance = titanSpawnDistance;
        this.resourcesGathered = initialResourcesPerLane * initialNumOfLanes;
        this.weaponFactory = new WeaponFactory();
        this.titansArchives = DataLoader.readTitanRegistry();
        this.approachingTitans = new ArrayList<>();
        this.lanes = new PriorityQueue<>();
        this.originalLanes = new ArrayList<>();
        this.initializeLanes(initialNumOfLanes);
    }

    // Methods
    private void initializeLanes(int numOfLanes) {
        for (int i = 0; i < numOfLanes; i++) {
            Wall wall = new Wall(WALL_BASE_HEALTH);
            Lane lane = new Lane(wall);
            lanes.add(lane);
            originalLanes.add(lane);
        }
    }

    // Getters and Setters
    public int getNumberOfTurns() {
        return numberOfTurns;
    }

    public void setNumberOfTurns(int numberOfTurns) {
        this.numberOfTurns = Math.max(numberOfTurns, 0);
    }

    public int getResourcesGathered() {
        return resourcesGathered;
    }

    public void setResourcesGathered(int resourcesGathered) {
        this.resourcesGathered = Math.max(resourcesGathered, 0);
    }

    public BattlePhase getBattlePhase() {
        return battlePhase;
    }

    public void setBattlePhase(BattlePhase battlePhase) {
        this.battlePhase = battlePhase;
    }

    public int getNumberOfTitansPerTurn() {
        return numberOfTitansPerTurn;
    }

    public void setNumberOfTitansPerTurn(int numberOfTitansPerTurn) {
        this.numberOfTitansPerTurn = Math.max(numberOfTitansPerTurn, 0);
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = Math.max(score, 0);
    }

    public int getTitanSpawnDistance() {
        return titanSpawnDistance;
    }

    public void setTitanSpawnDistance(int titanSpawnDistance) {
        this.titanSpawnDistance = Math.max(titanSpawnDistance, 0);
    }

    public WeaponFactory getWeaponFactory() {
        return weaponFactory;
    }

    public HashMap<Integer, TitanRegistry> getTitansArchives() {
        return titansArchives;
    }

    public ArrayList<Titan> getApproachingTitans() {
        return approachingTitans;
    }

    public PriorityQueue<Lane> getLanes() {
        return lanes;
    }

    public ArrayList<Lane> getOriginalLanes() {
        return originalLanes;
    }

    public void refillApproachingTitans() {
        int[] phaseTitanCodes = PHASES_APPROACHING_TITANS[battlePhase.ordinal()];
        approachingTitans.clear();
        for (int code : phaseTitanCodes)
            approachingTitans.add(titansArchives.get(code).spawnTitan(titanSpawnDistance));
    }

    public void purchaseWeapon(int weaponCode, Lane lane) throws InsufficientResourcesException, InvalidLaneException {
        if (!lanes.contains(lane) || lane.isLaneLost())
            throw new InvalidLaneException();
        FactoryResponse order = weaponFactory.buyWeapon(resourcesGathered, weaponCode);
        lane.addWeapon(order.getWeapon());
        setResourcesGathered(order.getRemainingResources());
        performTurn();
    }

    public void passTurn() {
        performTurn();
    }

    private void addTurnTitansToLane() {
        ArrayList<Lane> skippedLanes = new ArrayList<Lane>();
        Lane lane = lanes.remove();
        while (lane.isLaneLost() && !lanes.isEmpty()) {
            skippedLanes.add(lane);
            lane = lanes.remove();
        }
        for (int i = 0; i < numberOfTitansPerTurn; i++) {
            if (approachingTitans.isEmpty())
                refillApproachingTitans();
            lane.addTitan(approachingTitans.remove(0));
        }
        lanes.add(lane);
        lanes.addAll(skippedLanes);
    }

    private void moveTitans() {
        ArrayList<Lane> processedLanes = new ArrayList<Lane>();
        while (!lanes.isEmpty()) {
            Lane lane = lanes.remove();
            lane.moveLaneTitans();
            processedLanes.add(lane);
        }
        lanes.addAll(processedLanes);
    }

    private int performWeaponsAttacks() {
        ArrayList<Lane> processedLanes = new ArrayList<Lane>();
        int resourcesGained = 0;
        while (!lanes.isEmpty()) {
            Lane lane = lanes.remove();
            resourcesGained += lane.performLaneWeaponsAttacks();
            processedLanes.add(lane);
        }
        lanes.addAll(processedLanes);
        return resourcesGained;
    }

    private int performTitansAttacks() {
        ArrayList<Lane> processedLanes = new ArrayList<Lane>();
        int resourcesGained = 0;
        while (!lanes.isEmpty()) {
            Lane lane = lanes.remove();
            resourcesGained += lane.performLaneTitansAttacks();
            processedLanes.add(lane);
        }
        lanes.addAll(processedLanes);
        return resourcesGained;
    }

    private void updateLanesDangerLevels() {
        ArrayList<Lane> processedLanes = new ArrayList<Lane>();
        while (!lanes.isEmpty()) {
            Lane lane = lanes.remove();
            lane.updateLaneDangerLevel();
            processedLanes.add(lane);
        }
        lanes.addAll(processedLanes);
    }

    private void finalizeTurns() {
        setNumberOfTurns(numberOfTurns + 1);
        if (numberOfTurns < 15)
            setBattlePhase(BattlePhase.EARLY);
        else if (numberOfTurns < 30)
            setBattlePhase(BattlePhase.INTENSE);
        else
            setBattlePhase(BattlePhase.GRUMBLING);
        if (numberOfTurns >= 30 && numberOfTurns % 5 == 0)
            setNumberOfTitansPerTurn(numberOfTitansPerTurn * 2);
    }

    private void performTurn() {
        moveTitans();
        int resourcesGained = performWeaponsAttacks();
        resourcesGained += performTitansAttacks();
        setResourcesGathered(resourcesGathered + resourcesGained);
        setScore(score + resourcesGained);
        addTurnTitansToLane();
        updateLanesDangerLevels();
        finalizeTurns();
    }

    public boolean isGameOver() {
        for (Lane lane : lanes)
            if (!lane.isLaneLost())
                return false;
        return true;
    }

}