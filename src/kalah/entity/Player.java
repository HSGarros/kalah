package kalah.entity;

import kalah.util.ConfigUtil;

import java.util.Arrays;

//A player entity with several houses and stores
public class Player {
    private final int houseSize;
    private final int seedNum;
    private int[] house;
    private int store;
    public Player(int houseSize, int seedNum) {
        this.houseSize = houseSize;
        this.seedNum = seedNum;
        this.house = new int[this.houseSize];
        Arrays.fill(this.house, this.seedNum);
        this.store = 0;
    }

    public Player() {
        this.houseSize = ConfigUtil.getMaxHouseSize();
        this.seedNum = ConfigUtil.getInitSeedNum();
        this.house = new int[this.houseSize];
        Arrays.fill(this.house, this.seedNum);
        this.store = 0;
    }

    public Player(int[] seedInHouseList , int seedInStore) {
        this.houseSize = ConfigUtil.getMaxHouseSize();
        this.seedNum = ConfigUtil.getInitSeedNum();
        this.house = seedInHouseList.clone();
        this.store = seedInStore;
    }

    public void setHouse(int[] house) {
        this.house = house.clone();
    }

    public void setStore(int store) {
        this.store = store;
    }

    public void restart(){
        this.house = new int[this.houseSize];
        Arrays.fill(this.house, this.seedNum);
        this.store = 0;
    }

    public boolean checkAllOfHousesIsEmpty(){
        for (int i : house) {
            if (i != 0) return false;
        }
        return true;
    }

    public int[] getHouseList(){
        return house;
    }

    public int getSumSeed(){
        int sum = 0;
        for (int j : house) {
            sum = sum + j;
        }
        sum = sum + store;
        return sum;
    }

    public int getSeedInhouse(int position){
        return house[position-1];
    }


    public int takeAllSeedsInhouse(int position) {
        int numOfSeed;
        numOfSeed = house[position-1];
        house[position-1] = 0;
        return numOfSeed;
    }

    public int addSeedInhouse(int position) {
        house[position-1]++;
        return house[position-1];
    }

    public int getSeedInStore(){
        return store;
    }

    public void addSeedInStore() {
        store++;
    }

    public void addSeedsInStore(int seedNumber) {
        store = store + seedNumber;
    }

}
