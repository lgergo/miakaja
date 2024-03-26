package com.example.miakaja.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.miakaja.repository.MenuRepository

class IngredientViewModelFactory(val app: Application, private val menuRepository: MenuRepository) :ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return IngredientViewModel(app, menuRepository) as T
    }
}