package com.nick.Recipes;

import java.util.ArrayList;

public class Recipe {


    String name;
    String time;
    String servings;
    int id;
    ArrayList<String> ingredients;

    public Recipe(String name, String time, String servings, ArrayList<String> ingredients) {
        this.name = name;
        this.time = time;
        this.servings = servings;
        this.ingredients = ingredients;
    }

    public Recipe(){}

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public String getServings() {
        return servings;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
