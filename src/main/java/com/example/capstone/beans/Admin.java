package com.example.capstone.beans;

import java.util.ArrayList;

public class Admin {
    private String fullName;
    private String email;
    private String username;
    private String password;
    private boolean loggedIn;
    private ArrayList<Store> stores = new ArrayList<>();

    public Admin() {
    }

    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public ArrayList<Store> getStores() {
        return stores;
    }

    public void setStores(ArrayList<Store> stores) {
        this.stores = stores;
    }

    public Store getStoreById(long id){
        for (Store store : stores) {
            if (store.getStoreId() == id) {
                return store;
            }
        }
        return null;
    }

    public void setStoreById(long id, Store newStore){
        for (int i = 0; i < stores.size(); i++) {
            if (stores.get(i).getStoreId() == id) {
                stores.set(i, newStore);
            }
        }
    }

    public void addStore(Store store){
        stores.add(store);
    }
    public void removeStore(Store store){
        stores.remove(store);
    }
}
