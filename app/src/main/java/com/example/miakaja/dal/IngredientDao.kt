package com.example.miakaja.dal

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.example.miakaja.model.Ingredient
import com.example.miakaja.model.IngredientWithRecipe

@Dao
interface IngredientDao {
    @Upsert
    suspend fun upsertIngredient(ingredient: Ingredient)

    @Delete
    suspend fun deleteIngredient(ingredient: Ingredient)

    @Query("SELECT * FROM ingredient ORDER BY name DESC")
    fun getAllIngredients():LiveData<List<Ingredient>>

    @Query("SELECT * FROM ingredient WHERE name LIKE :query")
    fun searchIngredientByName(query:String?):LiveData<List<Ingredient>>

    @Transaction
    @Query("SELECT * FROM ingredient")
    fun getRecipesByIngredient(): List<IngredientWithRecipe>
}