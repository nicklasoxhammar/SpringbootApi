package com.nick.Recipes;

import java.util.ArrayList;
import java.util.Iterator;

public class Recipes {

    ArrayList<Recipe> recipes;

    public Recipes(ArrayList<Recipe> recipes){
        this.recipes = recipes;
    }

    public void addRecipe(Recipe recipe){
        recipes.add(recipe);
    }

    public void removeRecipe(int id){
        for(Iterator<Recipe> iter = recipes.iterator(); iter.hasNext();){
            Recipe r = iter.next();
            if (r.getId() == id){
                iter.remove();
            }
        }
    }

    public ArrayList<Recipe> getRecipes() {
        return recipes;
    }
}
