package com.example.miakaja.model

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.PrimaryKey
import androidx.room.Relation
import kotlinx.parcelize.Parcelize

@Entity(tableName = "recipe")
@Parcelize
data class Recipe (
    @PrimaryKey(autoGenerate = true)
    val recipeId: Int,
    val name:String,
    val prepTimeInMinutes :Int,
    val cookTimeInMinutes: Int,
    val description: String
): Parcelable

//many to many relation with room
data class RecipeWithIngredients(
    @Embedded val recipe: Recipe,
    @Relation(
        parentColumn = "recipeId",
        entityColumn = "ingredientId",
        associateBy = Junction(RecipeIngredient::class)
    )
    val ingredientList: List<Ingredient>
)
