package com.example.capstone.beans;

import java.util.ArrayList;

public class Store {
    private long storeId; // Will be retrieved after being added to database?
    private String storeName;
    private String storeAddress;
    private ArrayList<String> gamesList = new ArrayList<>();

    public Store(String storeName, String storeAddress, long storeId) {
        this.storeName = storeName;
        this.storeAddress = storeAddress;
        this.storeId = storeId;
    }

    public long getStoreId() {
        return storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    public ArrayList<String> getGamesList() {
        return gamesList;
    }

    public void setGamesList(ArrayList<String> gamesList) {
        this.gamesList = gamesList;
    }

    public void addGame(String game){
        gamesList.add(game);
    }

    public void removeGame(int index){
        gamesList.remove(index);
    }

}
