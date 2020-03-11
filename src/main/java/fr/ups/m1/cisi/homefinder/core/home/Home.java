/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ups.m1.cisi.homefinder.core.home;

public class Home {

    public static final String TYPE_APPARTMENT = "Appartement";
    public static final String TYPE_HOUSE = "Maison";

    private int homeId;

    private String name;
    private String type;

    private double latitude;
    private double longitude;

    private int size;
    private int nbRoom;

    private boolean hasPool;
    private boolean hasParking;
    private boolean hasTerrace;
    private boolean hasGarden;
    private boolean hasCellar;

    private double price;

    public Home(String name, String type, double latitude, double longitude, int size, int nbRoom, boolean hasPool, boolean hasParking, boolean hasTerrace, boolean hasGarden, boolean hasCellar, double price) {
        this.homeId = -1;
        this.name = name;
        this.type = type;
        this.latitude = latitude;
        this.longitude = longitude;
        this.size = size;
        this.nbRoom = nbRoom;
        this.hasPool = hasPool;
        this.hasParking = hasParking;
        this.hasTerrace = hasTerrace;
        this.hasGarden = hasGarden;
        this.hasCellar = hasCellar;
        this.price = price;
    }

    public Home(int homeId, String name, String type, double latitude, double longitude, int size, int nbRoom, boolean hasPool, boolean hasParking, boolean hasTerrace, boolean hasGarden, boolean hasCellar, double price) {

        this(name, type, latitude, longitude, size, nbRoom, hasPool, hasParking, hasTerrace, hasGarden, hasCellar, price);
        this.homeId = homeId;
    }

    public int getHomeId() {
        return homeId;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public int getSize() {
        return size;
    }

    public int getNbRoom() {
        return nbRoom;
    }

    public boolean isHasPool() {
        return hasPool;
    }

    public boolean isHasParking() {
        return hasParking;
    }

    public boolean isHasTerrace() {
        return hasTerrace;
    }

    public boolean isHasGarden() {
        return hasGarden;
    }

    public boolean isHasCellar() {
        return hasCellar;
    }

    public double getPrice() {
        return price;
    }

    public void setHomeId(int homeId) {
        this.homeId = homeId;
    }

    @Override
    public String toString() {
        String toReturn = "[" + name + "]\n"
                + type + " of " + size + "sqm with " + nbRoom + " rooms.\n"
                + "Location: [" + latitude + "," + longitude + "]\n"
                + "Commodities: Pool? " + hasPool + " Parking? " + hasParking + " Terrace? " + hasTerrace
                + " Garden? " + hasGarden + " Cellar? " + hasCellar + "\n"
                + "Price: " + price + "€";
        return toReturn;
    }

}
