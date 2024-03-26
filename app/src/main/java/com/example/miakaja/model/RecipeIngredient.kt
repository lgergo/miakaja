package com.example.miakaja.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

//many to many relation with room: https://developer.android.com/training/data-storage/room/relationships
// example double PK not building
@Entity(tableName = "recipe_ingredient")
@Parcelize
data class RecipeIngredient (
    @PrimaryKey(autoGenerate = true)
    val repIngId:Int,
    @ColumnInfo(index=true)
    val ingredientId:Int,
    @ColumnInfo(index=true)
    val recipeId: Int,
    val unit: String,
    val quantity: Long
): Parcelable