package com.nick.Recipes;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.google.gson.Gson;

import javax.servlet.http.HttpServletResponse;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

@RestController
public class RecipesController{
    Recipes recipes;

    @RequestMapping("/recipes")
    public ArrayList<Recipe> recipes() throws Exception {

        readRecipeJsonFromTextFile();

        return recipes.getRecipes();

    }

    @RequestMapping(method=RequestMethod.POST, value="/recipes")
    public void addRecipe(@RequestBody Recipe r) throws Exception{

        readRecipeJsonFromTextFile();

        r.setId(getRandomUniqueId());
        recipes.addRecipe(r);

        writeRecipeJsonToTextFile();

    }


    @RequestMapping(value = "/recipes/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Recipe getRecipeWithId(@PathVariable("id") int id) throws Exception {

        readRecipeJsonFromTextFile();

        for(Recipe r : recipes.getRecipes()){

            if(r.getId() == id){
                return r;
            }

        }

        throw new IllegalArgumentException("We don't have a recipe with the id " + String.valueOf(id));

    }

    @RequestMapping(value = "/recipes/{id}", method = RequestMethod.DELETE)
    public String deleteRecipeWithId(@PathVariable("id") int id) throws Exception {

        readRecipeJsonFromTextFile();

        for(Recipe r : recipes.getRecipes()){

            if(r.getId() == id){
                recipes.removeRecipe(r.getId());
                writeRecipeJsonToTextFile();
                return "Recipe for " + r.getName() + " has been deleted.";
            }

        }

        throw new IllegalArgumentException("We don't have a recipe with the id " + String.valueOf(id));

    }

    @RequestMapping(value = "/recipes/{id}", method = RequestMethod.PUT)
    public void updateRecipeWithId(@PathVariable("id") int id, @RequestBody Recipe recipe) throws Exception {

        readRecipeJsonFromTextFile();

        //sets the id to the original id, in case they tried to change it when making the Put request.
        recipe.setId(id);

        for(int i = 0; i < recipes.getRecipes().size(); i++){

            if(recipes.getRecipes().get(i).getId() == id){
                recipes.getRecipes().set(i, recipe);
                writeRecipeJsonToTextFile();
            }

        }


    }



    public void readRecipeJsonFromTextFile() throws Exception{

        try (Reader reader = new FileReader("SavedRecipes.txt")) {
            Gson gson = new Gson();
            recipes = gson.fromJson(reader, Recipes.class);
        }

    }

    public void writeRecipeJsonToTextFile() throws Exception{

        try (FileWriter file = new FileWriter("SavedRecipes.txt")) {
            Gson gson = new Gson();
            file.write(gson.toJson(recipes));

        }

    }

    @ExceptionHandler
    void handleIllegalArgumentException(IllegalArgumentException e, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value());
    }

    public int getRandomUniqueId(){

        int randomId = 0;

        boolean uniqueId = false;
        while(!uniqueId) {
            //generate random 4 digit integer
            randomId = (int) (Math.random() * 9000) + 1000;

            for(Recipe recipe : recipes.getRecipes()){
                if (recipe.getId() == randomId) {
                    break;
                }

                uniqueId = true;
            }
        }

        return randomId;

    }
}
