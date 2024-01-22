package be.aminmousavi.cocktailsapp.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://www.thecocktaildb.com/api/json/v1/1/"
private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface DrinksApiService {
    @GET("filter.php?a=Non_Alcoholic")
    suspend fun getNonAlcoholicDrinks(): String
}

object DrinksApi {
    val retrofitService : DrinksApiService by lazy {
        retrofit.create(DrinksApiService::class.java)
    }
}