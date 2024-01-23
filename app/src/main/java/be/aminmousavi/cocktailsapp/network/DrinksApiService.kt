package be.aminmousavi.cocktailsapp.network

import retrofit2.http.GET
interface DrinksApiService {
    @GET("filter.php?a=Non_Alcoholic")
    suspend fun getNonAlcoholicDrinks(): DrinksApiResponse
}
