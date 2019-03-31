package ca.mattpearcy.recipeasy.api

import ca.mattpearcy.recipeasy.data.SearchResults
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipePuppyApi {

    @GET("api/")
    fun recipeSearch(@Query("i") ingredients: String): Deferred<Response<SearchResults>>

    companion object {
        fun create() : RecipePuppyApi = Retrofit.Builder()
            .client(OkHttpClient())
            .baseUrl("http://www.recipepuppy.com")
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build().create(RecipePuppyApi::class.java)
    }
}