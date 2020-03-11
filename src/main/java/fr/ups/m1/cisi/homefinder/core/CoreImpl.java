/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ups.m1.cisi.homefinder.core;


import fr.ups.m1.cisi.homefinder.core.home.Home;
import fr.ups.m1.cisi.homefinder.db.Database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CoreImpl implements fr.ups.m1.cisi.homefinder.core.IHomeStorageProvider {

    private final Database db;

    private List<Home> homes;

    public CoreImpl() {
        this("C://HomeFinder/HomeFinder.db");
    }
    
    public CoreImpl(String path){
        homes = new ArrayList<>();
        db = new Database(path);
        populateHomesList();
    }

    private void populateHomesList() {
        homes = new ArrayList<>();
        ResultSet rs = db.getHomes();
        try {
            if (rs != null) {
                while (rs.next()) {
                    int homeId = rs.getInt("homeId");
                    String homeName = rs.getString("homeName");
                    String homeType = rs.getString("homeType");
                    double homeLatitude = rs.getDouble("homeLat");
                    double homeLongitude = rs.getDouble("homeLong");
                    int homeSize = rs.getInt("homeSize");
                    int homeRoom = rs.getInt("homeRoom");
                    boolean homePool = rs.getBoolean("homePool");
                    boolean homeParking = rs.getBoolean("homeParking");
                    boolean homeTerrace = rs.getBoolean("homeTerrace");
                    boolean homeGarden = rs.getBoolean("homeGarden");
                    boolean homeCellar = rs.getBoolean("homeCellar");
                    double homePrice = rs.getDouble("homePrice");
                    Home homeToAdd = new Home(homeId, homeName, homeType, homeLatitude, homeLongitude, homeSize, homeRoom, homePool, homeParking, homeTerrace, homeGarden, homeCellar, homePrice);
                    homes.add(homeToAdd);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CoreImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Home> getHomes() {
        populateHomesList();
        return homes;
    }

    @Override
    public Home getHomeByName(String name) {
        Home home = null;
        try {
            ResultSet res = db.getHomeByName(name);
            int i = 0;
            if (res.next()) {
                while (!homes.get(i).getName().equals(name) && i < homes.size()) {
                    i++;
                }
                if (homes.get(i).getName().equals(name)) {
                    home = homes.get(i);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CoreImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return home;
    }
    
    public List<Home> getFilteredHome(String type, int minSize, int minRoom, int maxPrice,  boolean requiresPool, boolean requiresParking, boolean requiresTerrace, boolean requiresGarden, boolean requiresCellar){
        List<Home> filteredHome = new ArrayList<>();
        return filteredHome;
    }

    @Override
    public void addHome(Home h) {
        db.addHome(h.getName(), h.getType(), h.getLatitude(), h.getLongitude(), h.getSize(), h.getNbRoom(), h.isHasPool(), h.isHasParking(), h.isHasTerrace(), h.isHasGarden(), h.isHasTerrace(), h.getPrice());
        try {
            Thread.sleep(25);

        } catch (InterruptedException ex) {
            Logger.getLogger(CoreImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteHome(Home h) {
        db.deleteHome(h.getHomeId());
    }

    @Override
    public void updateHome(Home h) {
        db.updateHome(h.getHomeId(), h.getName(), h.getType(), h.getLatitude(), h.getLongitude(), h.getSize(), h.getNbRoom(), h.isHasPool(), h.isHasParking(), h.isHasTerrace(), h.isHasGarden(), h.isHasTerrace(), h.getPrice());
    }

}
