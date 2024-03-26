package com.example.miakaja.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.navigation.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.miakaja.MainActivity
import com.example.miakaja.R
import com.example.miakaja.adapter.IngredientAdapter
import com.example.miakaja.databinding.FragmentHomeBinding
import com.example.miakaja.model.Ingredient
import com.example.miakaja.viewmodel.IngredientViewModel


class HomeFragment : Fragment(R.layout.fragment_home), SearchView.OnQueryTextListener, MenuProvider {

    private var homeBinding: FragmentHomeBinding? = null
    private val binding get() = homeBinding!!

    private lateinit var ingredientViewModel: IngredientViewModel
    private lateinit var  ingredientAdapter: IngredientAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        homeBinding=FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menuHost: MenuHost =requireActivity()
        menuHost.addMenuProvider(this,viewLifecycleOwner,Lifecycle.State.RESUMED)

        ingredientViewModel=(activity as MainActivity).ingredinetViewModel
        setupHomeRecycleView()

        binding.addIngredientFab.setOnClickListener{
            it.findNavController().navigate(R.id.action_homeFragment_to_addIngredientFragment)
        }
    }

    private fun updateUI(ingredient:List<Ingredient>?){
        if(ingredient != null){
            if(ingredient.isNullOrEmpty()){
                binding.emptyIngredientsImage.visibility=View.GONE
                binding.homeRecyclerView.visibility=View.VISIBLE
            }
            else{
                binding.emptyIngredientsImage.visibility=View.VISIBLE
                binding.homeRecyclerView.visibility=View.GONE
            }
        }
    }

    private fun setupHomeRecycleView(){
        ingredientAdapter = IngredientAdapter()
        binding.homeRecyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            setHasFixedSize(true)
            adapter=ingredientAdapter
        }

        activity?.let {
            ingredientViewModel.getAllIngredients().observe(viewLifecycleOwner){ingredient->
                ingredientAdapter.differ.submitList(ingredient)
                updateUI(ingredient)
            }
        }
    }

    private fun searchIngredient(query: String?){
        val searchQuery="%$query"

        ingredientViewModel.searchNote(searchQuery).observe(this){list ->
            ingredientAdapter.differ.submitList(list)
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if(newText!=null){
            searchIngredient(newText)
        }
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        homeBinding=null
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menu.clear()
        menuInflater.inflate(R.menu.home_menu, menu)

        val menuSearch=menu.findItem(R.id.searchMenu).actionView as SearchView
        menuSearch.isSubmitButtonEnabled = false
        menuSearch.setOnQueryTextListener(this)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return false
    }
}