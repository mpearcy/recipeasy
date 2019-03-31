package ca.mattpearcy.recipeasy.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import ca.mattpearcy.recipeasy.LiveDataTestUtil
import ca.mattpearcy.recipeasy.api.RecipePuppyApi
import ca.mattpearcy.recipeasy.api.SearchResult
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.CompletableDeferred
import org.junit.*
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

class RecipeRepoTest : KoinTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var api: RecipePuppyApi

    val recipeRepo : RecipeRepo by inject()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        startKoin{
            modules(
                module {
                    single { api }
                    single { RecipeRepo.getInstance(get()) }
                })
        }
    }

    @Test
    fun searchRecipesFound() {

        val searchString = "cheese"
        val recipe = Recipe(title = "Tasty Cheese", ingredients = "cheese", href = "")
        val searchResult = SearchResult(listOf(recipe))

        whenever(api.recipeSearch(searchString)).thenReturn(CompletableDeferred(Response.success(searchResult)))

        recipeRepo.search(searchString)
        val recipes = LiveDataTestUtil.getValue(recipeRepo.recipes)


        Assert.assertEquals(listOf(recipe), recipes)
    }


    /**
     * See [Memory leak in mockito-inline...](https://github.com/mockito/mockito/issues/1614)
     */
    @After
    fun clearMocks() {
        Mockito.framework().clearInlineMocks()
    }

}