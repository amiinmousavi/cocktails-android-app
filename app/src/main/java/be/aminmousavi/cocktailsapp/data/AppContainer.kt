package be.aminmousavi.cocktailsapp.data

import be.aminmousavi.cocktailsapp.network.DrinksApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val drinksRepository: DrinksRepository
}

class DefaultAppContainer : AppContainer {
    private val baseUrl = "https://www.thecocktaildb.com/api/json/v1/1/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: DrinksApiService by lazy {
        retrofit.create(DrinksApiService::class.java)
    }

    override val drinksRepository: DrinksRepository by lazy {
        NetworkDrinksRepository(retrofitService)
    }
}