package be.aminmousavi.cocktailsapp.network

import retrofit2.http.GET
interface CocktailsApiService {
    @GET("filter.php?a=Non_Alcoholic")
    suspend fun getNonAlcoholicDrinks(): DrinksApiResponse

    @GET("random.php")
    suspend fun getRandomDrink(): DrinkDetailsApiResponse
}
