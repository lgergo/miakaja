package com.example.miakaja.dal

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.miakaja.model.Ingredient
import com.example.miakaja.model.Recipe
import com.example.miakaja.model.RecipeIngredient

@Database(entities = [Ingredient::class, Recipe::class, RecipeIngredient::class], version = 1)
abstract class MenuDatabase: RoomDatabase() {

    abstract fun getIngredientDao(): IngredientDao
    abstract fun getRecipeDao(): RecipeDao
    abstract fun getRecipeIngredientDao(): RecipeIngredientDao

    companion object{
        @Volatile   //singleton
        private var instance: MenuDatabase? = null
        private val LOCK = Any()

        //thread safety
        operator fun invoke(context: Context) = instance ?:
        synchronized(LOCK){
            instance ?:
            createDatabase(context).also{
                instance=it
            }
        }

        private fun createDatabase(context:Context) =
            Room.databaseBuilder(
                    context.applicationContext,
                MenuDatabase::class.java,
                "menu_db"
            ).build()
    }
}