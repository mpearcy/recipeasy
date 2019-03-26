package ca.mattpearcy.recipeasy.data

import androidx.lifecycle.MutableLiveData
import ca.mattpearcy.recipeasy.api.RecipePuppyApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecipeRepo private constructor(private val recipePuppyApi: RecipePuppyApi) : BaseRepository() {


    fun search(ingredients: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val searchRespose = safeApiCall(
                    call = { recipePuppyApi.recipeSearch(ingredients).await()},
                    errorMessage = "Couldn't fetch recipes"
            )
            searchRespose?.let {
                recipes.postValue(it.results)
            }
        }
    }

    val recipes = MutableLiveData<List<Recipe>>()



    companion object {

        // For Singleton instantiation
        @Volatile private var instance: RecipeRepo? = null

        fun getInstance(recipePuppyApi: RecipePuppyApi) =
                instance ?: synchronized(this) {
                    instance ?: RecipeRepo(recipePuppyApi).also { instance = it }
                }
    }
}