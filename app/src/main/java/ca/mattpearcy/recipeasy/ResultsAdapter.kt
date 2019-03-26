package ca.mattpearcy.recipeasy

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ca.mattpearcy.recipeasy.data.Recipe

internal abstract class ResultsAdapter : ListAdapter<Recipe, ResultsAdapter.RecipeViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recipe_row, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.setup(position)
    }


    internal inner class RecipeViewHolder(var rootView: View) : RecyclerView.ViewHolder(rootView) {

        var recipeName: TextView
        var recipeIngredients: TextView


        init {
            recipeName = rootView.findViewById(R.id.recipe_name)
            recipeIngredients = rootView.findViewById(R.id.recipe_ingredients)

        }

        fun setup(position: Int) {
            val (title, ingredients, href) = getItem(position)
            recipeName.text = title.trim { it <= ' ' }
            recipeIngredients.text = ingredients.trim { it <= ' ' }

            rootView.setOnClickListener { openLink(href) }
        }
    }

    internal abstract fun openLink(link: String)

    companion object {

        val DIFF_CALLBACK: DiffUtil.ItemCallback<Recipe> = object : DiffUtil.ItemCallback<Recipe>() {
            override fun areItemsTheSame(
                oldRecipe: Recipe, newRecipe: Recipe
            ): Boolean {
                return oldRecipe.title == newRecipe.title
            }

            override fun areContentsTheSame(
                oldRecipe: Recipe, newRecipe: Recipe
            ): Boolean {

                return oldRecipe == newRecipe
            }
        }
    }
}
