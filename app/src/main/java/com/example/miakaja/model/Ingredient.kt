package com.example.miakaja.model

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.PrimaryKey
import androidx.room.Relation
import kotlinx.parcelize.Parcelize

//
@Entity(tableName = "ingredient")
@Parcelize
data class Ingredient (
    @PrimaryKey(autoGenerate = true)
    val ingredientId: Int,
    val name:String
): Parcelable


//many to many relation with room
data class IngredientWithRecipe(
    @Embedded val ingredient: Ingredient,
    @Relation(
        parentColumn = "ingredientId",
        entityColumn = "recipeId",
        associateBy = Junction(RecipeIngredient::class)
    )
    val recipeList: List<Recipe>
)
