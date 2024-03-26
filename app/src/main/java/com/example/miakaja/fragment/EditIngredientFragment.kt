package com.example.miakaja.fragment

import android.app.AlertDialog
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
import androidx.navigation.fragment.navArgs
import com.example.miakaja.MainActivity
import com.example.miakaja.R
import com.example.miakaja.databinding.FragmentEditIngredientBinding
import com.example.miakaja.model.Ingredient
import com.example.miakaja.viewmodel.IngredientViewModel

class EditIngredientFragment : Fragment(R.layout.fragment_edit_ingredient), MenuProvider {

    private var editIngredientBinding: FragmentEditIngredientBinding? = null
    private val binding get() = editIngredientBinding!!

    private lateinit var ingredientViewModel: IngredientViewModel
    private lateinit var currentIngredient: Ingredient

    private val args : EditIngredientFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        editIngredientBinding= FragmentEditIngredientBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menuHost: MenuHost =requireActivity()
        menuHost.addMenuProvider(this,viewLifecycleOwner, Lifecycle.State.RESUMED)

        ingredientViewModel=(activity as MainActivity).ingredinetViewModel
        currentIngredient= args.ingredient!!

        binding.editIngredientName.setText(currentIngredient.name)

        binding.editIngredientFab.setOnClickListener{
            val name=binding.editIngredientName.text.toString().trim()

            if(name.isNotEmpty()){
                val ingredient=Ingredient(currentIngredient.ingredientId,name)
                ingredientViewModel.uspertIngredient(ingredient)
                view.findNavController().popBackStack(R.id.homeFragment,false)
            }
            else {
                Toast.makeText(context, "Please enter ingredient name", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun deleteIngredient(){
        AlertDialog.Builder(activity).apply {
            setTitle("Delete")
            setMessage("Are you sure?")
            setPositiveButton("Delete"){_,_ ->
                ingredientViewModel.deleteIngredient(currentIngredient)
                Toast.makeText(context, "Successfully deleted", Toast.LENGTH_SHORT).show()
                view?.findNavController()?.popBackStack(R.id.homeFragment,false)
            }
            setNegativeButton("Cancel", null)
        }.create().show()
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menu.clear()
        menuInflater.inflate(R.menu.menu_edit_ingredient,menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId){
            R.id.deleteMenu -> {
                deleteIngredient()
                true
            }
            else -> false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        editIngredientBinding = null
    }
}