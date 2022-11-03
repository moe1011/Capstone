package com.example.capstone.dao;

import com.example.capstone.beans.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ApplicationDao {

    public void createUser(Admin user) {

        try {
            //Connect to DB
            Connection connection = DBConnection.getConnectionToDatabase();

            //Write query
            String createQuery = "INSERT INTO users (`fullName`,`email`,`username`,`password`) VALUES (?,?,?,?)";

            //Set parameters with PreparedStatement
            PreparedStatement statement = connection.prepareStatement(createQuery);
            statement.setString(1, user.getFullName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getUsername());
            statement.setString(4, user.getPassword());

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
            Connection connection = DBConnection.getConnectionToDatabase();

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
        //placeholder for method that will return true if the username entered exists in the database
        return false;
    }
}
