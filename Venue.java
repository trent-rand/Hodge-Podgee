package com.TrentRand.coe848;

public class Venue {
    private String Name;
    private String Address;
    private String HOO;
    private String Vibes;
    private int Adventurous;
    private int Price;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getHOO() {
        return HOO;
    }

    public void setHOO(String HOO) {
        this.HOO = HOO;
    }

    public String getVibes() {
        return Vibes;
    }

    public void setVibes(String vibes) {
        Vibes = vibes;
    }

    public int getAdventurous() {
        return Adventurous;
    }

    public void setAdventurous(int adventurous) {
        Adventurous = adventurous;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }
}
