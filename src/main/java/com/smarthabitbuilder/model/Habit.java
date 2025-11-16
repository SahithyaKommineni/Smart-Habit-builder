package com.smarthabitbuilder.model;


public class Habit {
    private int id;
    private String name;
    private String frequency; // daily, weekly, once
    private String category;


    public Habit(String name, String frequency, String category) {
        this.name = name;
        this.frequency = frequency;
        this.category = category;
    }


    public Habit(int id, String name, String frequency, String category) {
        this.id = id;
        this.name = name;
        this.frequency = frequency;
        this.category = category;
    }


    public int getId() { return id; }
    public String getName() { return name; }
    public String getFrequency() { return frequency; }
    public String getCategory() { return category; }


    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setFrequency(String frequency) { this.frequency = frequency; }
    public void setCategory(String category) { this.category = category; }


    @Override
    public String toString() {
        return id + ". " + name + " (" + frequency + ") [" + category + "]";
    }
}