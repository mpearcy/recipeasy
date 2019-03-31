package ca.mattpearcy.recipeasy.api

import ca.mattpearcy.recipeasy.data.Recipe

data class SearchResult(
    val results: List<Recipe>
)