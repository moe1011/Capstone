package com.example.capstone.dao;

import com.example.capstone.beans.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
}
