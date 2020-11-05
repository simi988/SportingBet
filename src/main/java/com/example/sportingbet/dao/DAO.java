package com.example.sportingbet.dao;

import java.sql.*;

import static java.lang.System.err;
import static java.lang.System.out;

public class DAO {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/mydatabase";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "password";
    private static final String CREATE_DATABASE_IF_NOT_EXISTS_MYDATABASE = "CREATE DATABASE IF NOT EXISTS mydatabase";
    private static final String CREATE_TABLE_CLIENT = "CREATE TABLE IF NOT EXISTS TBL_CLIENT " + "(TABLE_NUMBER INTEGER, " +
            " MONEY Double," + "ID LONG)";
    private static final String CREATE_TABLE_BET = "CREATE TABLE IF NOT EXISTS TBL_BET " + "(GAMELIST String, " +
            " MONEY Double," + "ID LONG)";
    private static final String CREATE_TABLE_GAME = "CREATE TABLE IF NOT EXISTS TBL_GAME " + "(TABLE_NUMBER INTEGER, " +
            " TYPE String," + "ID LONG)";
    private static final String CREATE_TABLE_GAME_TYPE = "CREATE TABLE IF NOT EXISTS TBL_GAME_TYPE " + "(SHARE Double, " +
            " TYPE String," + "ID LONG)";

    private static final String DROP_TABLE_TBL_CLIENT = "DROP TABLE TBL_CLIENT";
    private static final String DROP_TABLE_TBL_BET = "DROP TABLE TBL_BET";
    private static final String DROP_TABLE_TBL_GAME = "DROP TABLE TBL_GAME";
    private static final String DROP_TABLE_TBL_GAME_TYPE = "DROP TABLE TBL_GAME_TYPE";
    Connection connection = null;

    public void createDatabase() {
        Statement stmt;
        startConnection();
        try {
            stmt = connection.createStatement();
            stmt.executeUpdate(CREATE_DATABASE_IF_NOT_EXISTS_MYDATABASE);
            stmt.executeUpdate(CREATE_TABLE_CLIENT);
            stmt.executeUpdate(CREATE_TABLE_BET);
            stmt.executeUpdate(CREATE_TABLE_GAME);
            stmt.executeUpdate(CREATE_TABLE_GAME_TYPE);

        } catch (SQLException exception) {
            err.println(exception.getMessage());
        }
    }

    private void startConnection() {
        try {
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        } catch (SQLException exception) {
            err.println(exception.getMessage());
        }
    }

    private void executeUpdate(PreparedStatement preparedStatement) {
        try {
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            err.println(exception.getMessage());
        }
    }

    private void executeQuery(PreparedStatement preparedStatement) {
        try {
            preparedStatement.executeQuery();
        } catch (SQLException exception) {
            out.println(exception.getMessage());
        }
    }

    private PreparedStatement createPreparedStatement(String query) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(query);
        } catch (SQLException exception) {
            err.println(exception.getMessage());
        }
        return preparedStatement;
    }
}
