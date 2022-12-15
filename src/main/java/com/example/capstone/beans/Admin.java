package com.example.capstone.beans;

import com.example.capstone.dao.ApplicationDao;

import java.util.ArrayList;


public class Admin {

    private String fullName;
    private String email;
    private boolean verifiedEmail = false; //default value when creating user object
    private String username;
    private String password;
    private boolean loggedIn;
    private ArrayList<Store> stores = new ArrayList<>();

    public Admin() {
        //Constructor that retrieves the current stores in the database when the object is created.
        //The games assigned to each store are also retrieved

        //retrieve stores from database in the constructor.
        ApplicationDao dao = new ApplicationDao();
        stores = dao.retrieveStoresFromDB();

        //retrieve games assigned to each store as well.
        for (Store storeEntry : stores) {
            dao.retrieveGamesOnStore(storeEntry);
        }
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
    public boolean getVerifiedEmail() { return verifiedEmail;}
    public void setVerifiedEmail(boolean verifiedEmail) {this.verifiedEmail = verifiedEmail;}
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
                //update info in the database with the new store info.

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
