package com.example.capstone.beans;

public class StoreBuilder {
    private String storeName;
    private String storeAddress;
    private long storeId;

    public StoreBuilder setStoreId(long storeId) {
        this.storeId = storeId;
        return this;
    }

    public StoreBuilder setStoreName(String storeName) {
        this.storeName = storeName;
        return this;
    }

    public StoreBuilder setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
        return this;
    }

    public Store createStore() {
        return new Store(storeName, storeAddress, storeId);
    }
}