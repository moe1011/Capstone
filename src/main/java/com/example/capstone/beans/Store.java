package com.example.capstone.beans;

public class Store {
    private long storeId; // Auto Increment somehow
    private String storeName;
    private String storeAddress;
    private String[] gamesList;
    private static long counter = 1;

    public Store(){
        this.storeId = counter;
        counter++;
    }

    public Store(String storeName, String storeAddress) {
        this.storeId = counter;
        counter++;
        this.storeName = storeName;
        this.storeAddress = storeAddress;
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

    public String[] getGamesList() {
        return gamesList;
    }

    public void setGamesList(String[] gamesList) {
        this.gamesList = gamesList;
    }
}
