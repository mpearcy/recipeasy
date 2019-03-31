package ca.mattpearcy.recipeasy

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import ca.mattpearcy.recipeasy.data.Recipe
import ca.mattpearcy.recipeasy.viewmodels.IngredientSearchViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private val ingredientSearchViewModel : IngredientSearchViewModel by viewModel()
    private var adapter: ResultsAdapter = object : ResultsAdapter() {

        override fun openLink(link: String) {
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(link)
            startActivity(i)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        results_list.adapter = adapter

        search_button.setOnClickListener {
            if (search_box.text.toString().isNotBlank()) {
                 search(search_box.text.toString())
            }
        }

        ingredientSearchViewModel.recipes.observe(this, Observer {recipes : List<Recipe> ->
            adapter.submitList(recipes)
        })

    }

    private fun search(ingredients: String) {
        ingredientSearchViewModel.search(ingredients)
    }
}
