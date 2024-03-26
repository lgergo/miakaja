package com.example.miakaja.viewmodel

import android.app.Application
import com.example.miakaja.model.Ingredient
import com.example.miakaja.repository.MenuRepository
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.launch

class IngredientViewModel(app: Application, private val menuRepository: MenuRepository): AndroidViewModel(app) {

    fun deleteIngredient(ingredient: Ingredient) =
        viewModelScope.launch {
            menuRepository.deleteIngredient(ingredient)
        }
    fun uspertIngredient(ingredient: Ingredient) =
        viewModelScope.launch {
            menuRepository.upsertIngredient(ingredient)
        }

    fun getAllIngredients() = menuRepository.getAllIngredients()

    fun searchNote(query: String) =
        menuRepository.searchIngredientByName(query)
}