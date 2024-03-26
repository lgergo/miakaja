package com.example.miakaja.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.navigation.findNavController
import com.example.miakaja.MainActivity
import com.example.miakaja.R
import com.example.miakaja.databinding.FragmentAddIngredientBinding
import com.example.miakaja.model.Ingredient
import com.example.miakaja.viewmodel.IngredientViewModel

class AddIngredientFragment : Fragment(R.layout.fragment_add_ingredient), MenuProvider {

    private var addIngredientBinding: FragmentAddIngredientBinding? = null
    private val binding get() = addIngredientBinding!!

    private lateinit var ingredientViewModel: IngredientViewModel
    private lateinit var addIngredientView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        addIngredientBinding=FragmentAddIngredientBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menuHost: MenuHost =requireActivity()
        menuHost.addMenuProvider(this,viewLifecycleOwner, Lifecycle.State.RESUMED)

        ingredientViewModel=(activity as MainActivity).ingredinetViewModel
        addIngredientView=view
    }

    private fun saveIngredient(view:View){
        val name=binding.addIngredientName.text.toString().trim()

        if(name.isNotEmpty()){
            val ingredient=Ingredient(0,name)
            ingredientViewModel.uspertIngredient(ingredient)

            Toast.makeText(addIngredientView.context, "Ingredient saved", Toast.LENGTH_SHORT).show()
            view.findNavController().popBackStack(R.id.homeFragment,false)
        }
        else {
            Toast.makeText(addIngredientView.context, "Please enter ingredinet name", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menu.clear()
        menuInflater.inflate(R.menu.menu_add_ingredient,menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId){
            R.id.saveMenu -> {
                saveIngredient(addIngredientView)
                true
            }
            else -> false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        addIngredientBinding = null
    }
}