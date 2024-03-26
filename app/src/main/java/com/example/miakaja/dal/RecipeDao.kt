package com.example.miakaja.dal

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.example.miakaja.model.Recipe
import com.example.miakaja.model.RecipeWithIngredients

@Dao
interface RecipeDao {

    @Upsert
    suspend fun upsertRecipe(recipe: Recipe)

    @Delete
    suspend fun deleteRecipe(recipe: Recipe)

    @Query("SELECT * FROM recipe ORDER BY name DESC")
    fun getAllRecipes():LiveData<List<Recipe>>

    @Query("SELECT * FROM recipe WHERE name LIKE :query")
    fun searchRecipeByName(query:String?):LiveData<List<Recipe>>

    @Transaction
    @Query("SELECT * FROM recipe")
    fun getIngredientsByRecipe(): List<RecipeWithIngredients>
}