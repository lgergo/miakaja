package com.example.miakaja

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.miakaja.dal.MenuDatabase
import com.example.miakaja.repository.MenuRepository
import com.example.miakaja.viewmodel.IngredientViewModel
import com.example.miakaja.viewmodel.IngredientViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var ingredinetViewModel:IngredientViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupViewModel()
    }

    private fun setupViewModel(){
        val menuRepository = MenuRepository(MenuDatabase(this))
        val viewModelProviderFactory=IngredientViewModelFactory(application,menuRepository)
        ingredinetViewModel=ViewModelProvider(this,viewModelProviderFactory)[IngredientViewModel::class.java]
    }
}