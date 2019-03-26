package ca.mattpearcy.recipeasy.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import ca.mattpearcy.recipeasy.data.Recipe
import ca.mattpearcy.recipeasy.data.RecipeRepo

class IngredientSearchViewModel internal constructor(private val recipeRepo: RecipeRepo) : ViewModel() {

    val recipes: LiveData<List<Recipe>> = recipeRepo.recipes

    fun search(ingredients: String) {
        recipeRepo.search(ingredients)
    }
}