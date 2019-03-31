package ca.mattpearcy.recipeasy.data

data class Recipe(
    val title: String,
    val ingredients: String,
    val href: String,
    val thumbnail: String? = null
)