package ca.mattpearcy.recipeasy

import ca.mattpearcy.recipeasy.api.RecipePuppyApi
import ca.mattpearcy.recipeasy.data.RecipeRepo
import ca.mattpearcy.recipeasy.viewmodels.IngredientSearchViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val diModule : Module = module {
    single { RecipePuppyApi.create()}
    viewModel { IngredientSearchViewModel(get()) }
    single { RecipeRepo.getInstance(get()) }
}