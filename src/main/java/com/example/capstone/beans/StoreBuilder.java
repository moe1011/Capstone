package com.example.capstone.beans;

public class StoreBuilder {
    private String storeName;
    private String storeAddress;
    private long storeId; // Will be retrieved after being added to database?
    private static long counter = 1; // Temporary until database is setup

    public StoreBuilder setStoreName(String storeName) {
        this.storeName = storeName;
        return this;
    }

    public StoreBuilder setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
        return this;
    }

    public Store createStore() {
        this.storeId = counter;
        counter++; // this will have to removed in the future to dynamically retrieve the store id.
        return new Store(storeName, storeAddress, storeId);
    }
}