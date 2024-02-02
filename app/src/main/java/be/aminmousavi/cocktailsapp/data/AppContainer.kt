package be.aminmousavi.cocktailsapp.data

import android.content.Context
import be.aminmousavi.cocktailsapp.network.CocktailsApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit


interface AppContainer {
    val cocktailsRepository: CocktailsRepository
}

class DefaultAppContainer(private val context: Context) : AppContainer {
    private val baseUrl = "https://www.thecocktaildb.com/api/json/v1/1/"

    private val json = Json {
        ignoreUnknownKeys = true
    }

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: CocktailsApiService by lazy {
        retrofit.create(CocktailsApiService::class.java)
    }

//    override val cocktailsRepository: CocktailsRepository by lazy {
//        NetworkCocktailsRepository(retrofitService)
//    }

    override val cocktailsRepository: CocktailsRepository by lazy {
        NetworkCocktailsRepository(retrofitService, CocktailsDatabase.getDatabase(context).drinkDao())
    }
}