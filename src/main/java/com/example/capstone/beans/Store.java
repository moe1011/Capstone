package com.example.capstone.beans;

public class Store {
    private int storeId; // Auto Increment somehow
    private String storeName;
    private String storeAddress;
    private String[] gamesList;

    public Store(){

    }

    public Store(String storeName, String storeAddress) {
        this.storeName = storeName;
        this.storeAddress = storeAddress;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
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
