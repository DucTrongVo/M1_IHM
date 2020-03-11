/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ups.m1.cisi.homefinder.db;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {

    private Connection conn;

    public Database(String dbFilePath) {
        try {
            createDatabaseFile(dbFilePath);
            String url = "jdbc:sqlite:" + dbFilePath;
            conn = DriverManager.getConnection(url);
            if (conn != null) {
                createHomeTable();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ResultSet getHomes() {
        ResultSet homes = null;
        try {
            Statement homesStatement = conn.createStatement();
            homes = homesStatement.executeQuery("SELECT * FROM home_table");
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return homes;
    }

    public ResultSet getHomeByName(String name) {
        ResultSet home = null;
        try {
            PreparedStatement getHomeStatement = conn.prepareStatement("SELECT * FROM home_table WHERE homeName=?");
            getHomeStatement.setString(1, name);
            home = getHomeStatement.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return home;
    }

    public boolean checkDoublon(String name) {
        ResultSet home = null;
        int result = 0;
        try {
            PreparedStatement getHomeStatement = conn.prepareStatement("SELECT * FROM home_table WHERE homeName=?");
            getHomeStatement.setString(1, name);
            home = getHomeStatement.executeQuery();
            home.beforeFirst();
            while (home.next()) {
                result++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result == 1;
    }

    /**
     * Adds the home to the database
     *
     * @param name
     * @param type
     * @param latitude
     * @param longitude
     * @param size
     * @param nbRoom
     * @param pool
     * @param parking
     * @param terrace
     * @param garden
     * @param cellar
     * @param price
     */
    public void addHome(String name,
            String type,
            double latitude,
            double longitude,
            int size,
            int nbRoom,
            boolean pool,
            boolean parking,
            boolean terrace,
            boolean garden,
            boolean cellar,
            double price) {
        try {
            PreparedStatement addHomeStatement = conn.prepareStatement("INSERT INTO home_table ("
                    + "homeName, homeType, homeLat, homeLong, homeSize, homeRoom,"
                    + "homePool, homeParking, homeTerrace, homeGarden, homeCellar, homePrice)"
                    + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?);");
            addHomeStatement.setString(1, name);
            addHomeStatement.setString(2, type);
            addHomeStatement.setDouble(3, latitude);
            addHomeStatement.setDouble(4, longitude);
            addHomeStatement.setInt(5, size);
            addHomeStatement.setInt(6, nbRoom);
            addHomeStatement.setBoolean(7, pool);
            addHomeStatement.setBoolean(8, parking);
            addHomeStatement.setBoolean(9, terrace);
            addHomeStatement.setBoolean(10, garden);
            addHomeStatement.setBoolean(11, cellar);
            addHomeStatement.setDouble(12, price);
            addHomeStatement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Updates the home with the corresponding id
     *
     * @param id
     * @param name
     * @param type
     * @param latitude
     * @param longitude
     * @param size
     * @param nbRoom
     * @param pool
     * @param parking
     * @param terrace
     * @param garden
     * @param cellar
     * @param price
     */
    public void updateHome(int id,
            String name,
            String type,
            double latitude,
            double longitude,
            int size,
            int nbRoom,
            boolean pool,
            boolean parking,
            boolean terrace,
            boolean garden,
            boolean cellar,
            double price) {
        try {
            PreparedStatement updateHomeStatement = conn.prepareStatement("UPDATE home_table SET "
                    + "homeName=?, homeType=?, homeLat=?, homeLong=?, homeSize=?, homeRoom=?,"
                    + "homePool=?, homeParking=?, homeTerrace=?, homeGarden=?, homeCellar=?, homePrice=?"
                    + " WHERE homeId = ?");
            updateHomeStatement.setString(1, name);
            updateHomeStatement.setString(2, type);
            updateHomeStatement.setDouble(3, latitude);
            updateHomeStatement.setDouble(4, longitude);
            updateHomeStatement.setInt(5, size);
            updateHomeStatement.setInt(6, nbRoom);
            updateHomeStatement.setBoolean(7, pool);
            updateHomeStatement.setBoolean(8, parking);
            updateHomeStatement.setBoolean(9, terrace);
            updateHomeStatement.setBoolean(10, garden);
            updateHomeStatement.setBoolean(11, cellar);
            updateHomeStatement.setDouble(12, price);
            updateHomeStatement.setInt(13, id);
            updateHomeStatement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Deletes the home with the corresponding id
     *
     * @param id
     */
    public void deleteHome(int id) {
        try {
            PreparedStatement deleteHomeStatement = conn.prepareStatement("DELETE FROM home_table "
                    + "WHERE homeId=?");
            deleteHomeStatement.setInt(1, id);
            deleteHomeStatement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Creates the directory structure required for the database storage
     *
     * @param dbFilePath the full path to the databsefile
     */
    private void createDatabaseFile(String dbFilePath) {
        try {
            String path = dbFilePath.substring(0, dbFilePath.lastIndexOf('/'));
            Files.createDirectories(Paths.get(path));
        } catch (IOException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Creates the home_table in the database if it does not exists
     */
    private void createHomeTable() {
        try {
            Statement createHomeTableStatement = conn.createStatement();
            createHomeTableStatement.execute("CREATE TABLE IF NOT EXISTS home_table ("
                    + "homeId INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "homeName VARCHAR,"
                    + "homeType VARCHAR,"
                    + "homeLat DECIMAL(7,5)," //Latitude is between -90° and +90°, 6 decimals
                    + "homeLong DECIMAL (8,5)," //Longitude is between -180° and +180°, 6 decimals
                    + "homeSize INTEGER,"
                    + "homeRoom INTEGER,"
                    + "homePool BOOLEAN,"
                    + "homeParking BOOLEAN,"
                    + "homeTerrace BOOLEAN,"
                    + "homeGarden BOOLEAN,"
                    + "homeCellar BOOLEAN,"
                    + "homePrice DECIMAL (9,2));");
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
