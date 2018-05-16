package com.example.ethan.dream_fit;

public class Item {

    private String description;
    private String calorie;

    // constructor which will initialize the 2 variable
    public Item(String thisDescription, String thisCalorie){
        description = thisDescription ;
        calorie = thisCalorie ;
    }

    // Getter and Setters
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCalorie() {
        return calorie;
    }

    public void setCalorie(String calorie) {
        this.calorie = calorie;
    }

}
