package com.example.bloodbank.data.model;

public class CityGovernorateSpinnnerModel {

    private String Name;
    private int id;

    public CityGovernorateSpinnnerModel(int id, String name) {
        this.id = id;
        Name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
