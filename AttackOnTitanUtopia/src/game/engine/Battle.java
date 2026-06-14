package game.engine;

import java.io.*;
import java.util.*;
import game.engine.base.Wall;
import game.engine.dataloader.DataLoader;
import game.engine.lanes.Lane;
import game.engine.titans.*;
import game.engine.weapons.factory.WeaponFactory;

public class Battle {

    // Variables
    @SuppressWarnings("unused")
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
}