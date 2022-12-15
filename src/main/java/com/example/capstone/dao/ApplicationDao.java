package com.example.capstone.dao;

import com.example.capstone.beans.Admin;
import com.example.capstone.beans.Store;
import com.example.capstone.beans.StoreBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ApplicationDao {

    public void createUser(Admin user) {

        try {
            //Connect to DB
            Connection connection = DBConnection.getInstance();

            //Write query
            String createQuery = "INSERT INTO users (`fullName`,`email`,`username`,`password`,`verifiedEmail`) VALUES (?,?,?,?,?)";

            //Set parameters with PreparedStatement
            PreparedStatement statement = connection.prepareStatement(createQuery);
            statement.setString(1, user.getFullName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getUsername());
            statement.setString(4, user.getPassword());
            statement.setString(5, String.valueOf(user.getVerifiedEmail()));

            //Execute the Statement
            statement.executeUpdate();

            //close the connection
            statement.close();
        }

        catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public boolean validateUser (String username, String password) {
            boolean isValidUser = false;
        try {
            //connect to database
            Connection connection = DBConnection.getInstance();

            //query the database with username and password
            String userQuery = "SELECT * FROM users WHERE username=? AND password=?";

            //prepare statement
            PreparedStatement statement = connection.prepareStatement(userQuery);
            statement.setString(1,username);
            statement.setString(2, password);

            //execute query
            ResultSet set = statement.executeQuery();

            //validation of return
            while (set.next()){
                isValidUser=true;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return isValidUser;
    }

    public boolean verifyUserExists(String username) {
        boolean existingUser = false;
        try {
            //connect to database
            Connection connection = DBConnection.getInstance();

            //query the database with username and password
            String userQuery = "SELECT * FROM users WHERE username=?";

            //prepare statement
            PreparedStatement statement = connection.prepareStatement(userQuery);
            statement.setString(1,username);

            //execute query
            ResultSet set = statement.executeQuery();

            //validation of return
            while (set.next()){
                existingUser=true;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return existingUser;
    }

    public boolean checkEmailVerified(String username) {
        boolean isEmailVerified = false;
        try {
            //connect to database
            Connection connection = DBConnection.getInstance();

            //query the database with username and password
            String userQuery = "SELECT verifiedEmail FROM users WHERE username=?";

            //prepare statement
            PreparedStatement statement = connection.prepareStatement(userQuery);
            statement.setString(1,username);

            //execute query
            ResultSet set = statement.executeQuery();

            //validation of return
            String returnFromDB = "";
            while (set.next()){
                returnFromDB = set.getString("verifiedEmail");
            }

            if (returnFromDB.equals("true")) {
                isEmailVerified = true;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return isEmailVerified;
    }

    public void addEmailVerificationToken(String username, String token) {
        try {
            //Connect to DB
            Connection connection = DBConnection.getInstance();

            //Write query
            String createQuery = "UPDATE users SET emailVerificationToken=? WHERE username=?";

            //Set parameters with PreparedStatement
            PreparedStatement statement = connection.prepareStatement(createQuery);
            statement.setString(1, token);
            statement.setString(2, username);

            //Execute the Statement
            statement.executeUpdate();

            //close the connection
            statement.close();
        }

        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getEmailVerificationToken(String username) {
        String emailVerificationToken = "";
        try {
            //Connect to DB
            Connection connection = DBConnection.getInstance();

            //Write query
            String query = "SELECT emailVerificationToken FROM users WHERE username=?";

            //Set parameters with PreparedStatement
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);

            //execute query
            ResultSet set = statement.executeQuery();

            //Get token from ResultSet
            while (set.next()){
                emailVerificationToken = set.getString("emailVerificationToken");
            }
        }

        catch (SQLException e) {
            e.printStackTrace();
        }
        return emailVerificationToken;
    }

    public void markEmailVerified(String username) {
        try {
            //Connect to DB
            Connection connection = DBConnection.getInstance();

            //Write query
            String Query = "UPDATE users SET verifiedEmail='true' WHERE username=?";

            //Set parameters with PreparedStatement
            PreparedStatement statement = connection.prepareStatement(Query);
            statement.setString(1, username);

            //Execute the Statement
            statement.executeUpdate();

            //close the connection
            statement.close();
        }

        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean verifyEmailExists(String email) {
        boolean existingEmail = false;
        try {
            //connect to database
            Connection connection = DBConnection.getInstance();

            //query the database with username and password
            String userQuery = "SELECT * FROM users WHERE email=?";

            //prepare statement
            PreparedStatement statement = connection.prepareStatement(userQuery);
            statement.setString(1,email);

            //execute query
            ResultSet set = statement.executeQuery();

            //validation of return
            while (set.next()){
                existingEmail=true;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return existingEmail;
    }

    public void addPasswordResetToken(String email, String passwordToken) {
        try {
            //Connect to DB
            Connection connection = DBConnection.getInstance();

            //Write query
            String passwordResetQuery = "UPDATE users SET passwordResetToken=? WHERE email=?";

            //Set parameters with PreparedStatement
            PreparedStatement statement = connection.prepareStatement(passwordResetQuery);
            statement.setString(1, passwordToken);
            statement.setString(2, email);

            //Execute the Statement
            statement.executeUpdate();

            //close the connection
            statement.close();
        }

        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean verifyPasswordResetToken(String email, String passwordToken) {
        boolean validPasswordResetToken = false;
        try {
            //connect to database
            Connection connection = DBConnection.getInstance();

            //query the database with username and password
            String userQuery = "SELECT * FROM users WHERE email=?";

            //prepare statement
            PreparedStatement statement = connection.prepareStatement(userQuery);
            statement.setString(1,email);

            //execute query
            ResultSet set = statement.executeQuery();

            //validation of return
            while (set.next()){
                String tokenFromDB = set.getString("passwordResetToken");
                if (tokenFromDB.equals(passwordToken)) {
                    validPasswordResetToken = true;
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return validPasswordResetToken;
    }

    public void changePassword(String email, String newPassword) {
        try {
            //Connect to DB
            Connection connection = DBConnection.getInstance();

            //Write query
            String passwordResetQuery = "UPDATE users SET password=? WHERE email=?";

            //Set parameters with PreparedStatement
            PreparedStatement statement = connection.prepareStatement(passwordResetQuery);
            statement.setString(1, newPassword);
            statement.setString(2, email);

            //Execute the Statement
            statement.executeUpdate();

            //close the connection
            statement.close();
        }

        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Store> retrieveStoresFromDB() {

        Store storeEntry;
        ArrayList<Store> storeList = new ArrayList<>();

        try {
            //connect to database
            Connection connection = DBConnection.getInstance();

            //query the database with username and password
            String userQuery = "SELECT * FROM store";

            //prepare statement
            PreparedStatement statement = connection.prepareStatement(userQuery);

            //execute query
            ResultSet set = statement.executeQuery();

            //validation of return
            while (set.next()){
                String name = set.getString("name");
                String address = set.getString("address");
                long storeId = set.getLong("id");

                storeEntry = new Store(name, address, storeId);

                storeList.add(storeEntry);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return storeList;

    }

    public void retrieveGamesOnStore(Store storeEntry) {

        long storeId = storeEntry.getStoreId();

        try {
            //connect to database
            Connection connection = DBConnection.getInstance();

            //query the database with username and password
            String userQuery = "select * from game where storeID = ? order by name";

            //prepare statement
            PreparedStatement statement = connection.prepareStatement(userQuery);
            statement.setLong(1,storeId);

            //execute query
            ResultSet set = statement.executeQuery();

            //validation of return
            while (set.next()){
                String gameName = set.getString("name");
                storeEntry.addGame(gameName);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addStoreToDatabase(Store addedStore) {

        try {
            //Connect to DB
            Connection connection = DBConnection.getInstance();

            //Write query
            String createQuery = "INSERT INTO store (`name`,`address`) VALUES (?,?)";

            //Set parameters with PreparedStatement
            PreparedStatement statement = connection.prepareStatement(createQuery);
            statement.setString(1, addedStore.getStoreName());
            statement.setString(2, addedStore.getStoreAddress());

            //Execute the Statement
            statement.executeUpdate();

            //close the connection
            statement.close();
        }

        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public long retrieveStoreID(Store addedStore) {

        long storeId = 0;

        try {
            //connect to database
            Connection connection = DBConnection.getInstance();

            //query the database with username and password
            String query = "SELECT id FROM store where name = ? and address = ?";

            //prepare statement
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, addedStore.getStoreName());
            statement.setString(2, addedStore.getStoreAddress());

            //execute query
            ResultSet set = statement.executeQuery();

            //validation of return
            while (set.next()){

                storeId = set.getLong("id");

            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return storeId;
    }

    public void updateStoreInDB(Store fetchedStore) {

        try {
            //Connect to DB
            Connection connection = DBConnection.getInstance();

            //Write query
            String query = "update store set name = ?,  address = ? where id = ?";

            //Set parameters with PreparedStatement
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, fetchedStore.getStoreName());
            statement.setString(2, fetchedStore.getStoreAddress());
            statement.setLong(3, fetchedStore.getStoreId());

            //Execute the Statement
            statement.executeUpdate();

            //close the connection
            statement.close();
        }

        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addGameToStore(String gameName, Store chosenStore) {

        try {
            //Connect to DB
            Connection connection = DBConnection.getInstance();

            //Write query
            String query = "INSERT INTO game (`name`,`storeID`) VALUES (?,?)";

            //Set parameters with PreparedStatement
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, gameName);
            statement.setLong(2, chosenStore.getStoreId());

            //Execute the Statement
            statement.executeUpdate();

            //close the connection
            statement.close();
        }

        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeGameFromStore(String removeGame, Store chosenStore) {
        try {
            //Connect to DB
            Connection connection = DBConnection.getInstance();

            //Write query
            String query = "delete from game where name = ? and storeID = ?";

            //Set parameters with PreparedStatement
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, removeGame);
            statement.setLong(2, chosenStore.getStoreId());

            //Execute the Statement
            statement.executeUpdate();

            //close the connection
            statement.close();
        }

        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeStoreFromDatabase(Store chosenStore) {
        //in case the store has games, need to remove games from store in DB first
        if (!chosenStore.getGamesList().isEmpty()) {
            ApplicationDao dao = new ApplicationDao();
            //iterating in games for a store
            for (String game : chosenStore.getGamesList()) {
                dao.removeGameFromStore(game, chosenStore);
            }
        }

        //after all games are removed from store, remove store from DB
        try {
            //Connect to DB
            Connection connection = DBConnection.getInstance();

            //Write query
            String query = "delete from store where id = ?";

            //Set parameters with PreparedStatement
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, chosenStore.getStoreId());

            //Execute the Statement
            statement.executeUpdate();

            //close the connection
            statement.close();
        }

        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}