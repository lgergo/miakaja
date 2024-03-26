package com.example.miakaja.repository

import com.example.miakaja.dal.MenuDatabase
import com.example.miakaja.model.Ingredient
import com.example.miakaja.model.Recipe
import com.example.miakaja.model.RecipeIngredient

class MenuRepository(private val db: MenuDatabase) {

    suspend fun upsertIngredient(ingredient: Ingredient) = db.getIngredientDao().upsertIngredient(ingredient)
    suspend fun deleteIngredient(ingredient: Ingredient) = db.getIngredientDao().deleteIngredient(ingredient)
    fun getAllIngredients() = db.getIngredientDao().getAllIngredients()
    fun searchIngredientByName(name: String) = db.getIngredientDao().searchIngredientByName(name)


    suspend fun upsertRecipe(recipe: Recipe) = db.getRecipeDao().upsertRecipe(recipe)
    suspend fun deleteIngredient(recipe: Recipe) = db.getRecipeDao().deleteRecipe(recipe)
    fun getAllRecipes() = db.getRecipeDao().getAllRecipes()
    fun searchRecipeByName(name: String) = db.getRecipeDao().searchRecipeByName(name)

    suspend fun upsertRecipeIngredient(recipeIngredient: RecipeIngredient) = db.getRecipeIngredientDao().upsertRecipeIngredient(recipeIngredient)
    suspend fun deleteIngredient(recipeIngredient: RecipeIngredient) = db.getRecipeIngredientDao().deleteRecipeIngredient(recipeIngredient)
    fun getAllRecipeIngredients() = db.getRecipeIngredientDao().getAllRecipeIngredients()

}