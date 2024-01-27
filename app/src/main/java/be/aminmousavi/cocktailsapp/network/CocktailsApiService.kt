package be.aminmousavi.cocktailsapp.network

import retrofit2.http.GET
interface CocktailsApiService {
    @GET("filter.php?a=Non_Alcoholic")
    suspend fun getNonAlcoholicDrinks(): CocktailsApiResponse

    @GET("filter.php?c=Shake")
    suspend fun getShakes(): CocktailsApiResponse

    @GET("filter.php?c=Cocktail")
    suspend fun getCocktails(): CocktailsApiResponse

    @GET("random.php")
    suspend fun getRandomDrink(): CocktailsApiResponse

}
