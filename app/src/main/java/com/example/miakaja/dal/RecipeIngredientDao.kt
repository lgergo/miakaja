package com.example.miakaja.dal

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.miakaja.model.Recipe
import com.example.miakaja.model.RecipeIngredient

@Dao
interface RecipeIngredientDao {
    @Upsert
    suspend fun upsertRecipeIngredient(recipeIngredient: RecipeIngredient)

    @Delete
    suspend fun deleteRecipeIngredient(recipeIngredient: RecipeIngredient)

    @Query("SELECT * FROM recipe_ingredient")
    fun getAllRecipeIngredients(): LiveData<List<RecipeIngredient>>
}