package com.nick.Recipes;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileWriter;
import java.io.IOException;

@SpringBootApplication
public class RecipesApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(RecipesApplication.class, args);

		//add pancakes to textfile
		/*JSONObject recipes = new JSONObject();
		JSONArray recipeList = new JSONArray();

		JSONObject obj = new JSONObject();
		obj.put("name", "Pannkakor");
		obj.put("time", "15 minuter");
		obj.put("servings", "8" );

		recipeList.add(obj);

		JSONArray ingredients = new JSONArray();
		ingredients.add("3 dl vetemjöl");
		ingredients.add("6 dl mjölk");
		ingredients.add("3 ägg");
		obj.put("ingredients", ingredients);

		recipes.put("recipes", recipeList);

		// try-with-resources statement based on post comment below :)
		try (FileWriter file = new FileWriter("SavedRecipes.txt")) {
			file.write(recipes.toJSONString());
			System.out.println("Successfully Copied JSON Object to File...");
			System.out.println("\nJSON Object: " + recipes);
		}*/


	}
}
