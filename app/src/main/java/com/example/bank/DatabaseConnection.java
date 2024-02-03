package com.example.bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    private final static String DbUrl = "jdbc:mysql://127.0.0.1/bank";
    private final static String DbUser = "root";
    private final static String DbPassword = "";
    private final static String DbDriver = "com.mysql.jdbc.Driver";

    private Connection connection;
    private Statement statement;
    private String query;
    private UserParser userParser;

    public DatabaseConnection(){
        userParser = new UserParser();
    }

    public void save(User user) {
        query = userParser.createSaveQuery(user);

        try {
            Class.forName(DbDriver).newInstance();
            connection = DriverManager.getConnection(DbUrl, DbUser, DbPassword);
            statement = connection.createStatement();
            statement.executeUpdate(query);

            statement.close();
            connection.close();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

}
