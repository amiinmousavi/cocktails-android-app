package be.aminmousavi.cocktailsapp.network

import retrofit2.http.GET
interface CocktailsApiService {
    //www.thecocktaildb.com/api/json/v1/1/filter.php?a=Non_Alcoholic
    @GET("filter.php?a=Non_Alcoholic")
    suspend fun getNonAlcoholicDrinks(): DrinksApiResponse

    @GET("filter.php?c=Shake")
    suspend fun getShakes(): DrinksApiResponse
    //www.thecocktaildb.com/api/json/v1/1/random.php
    //www.thecocktaildb.com/api/json/v1/1/filter.php?c=Shake
    //www.thecocktaildb.com/api/json/v1/1/filter.php?i=Coffee
    //www.thecocktaildb.com/api/json/v1/1/filter.php?c=Cocktail
    //www.thecocktaildb.com/api/json/v1/1/filter.php?c=Ordinary_Drink
    //www.thecocktaildb.com/api/json/v1/1/filter.php?i=Scotch
    //www.thecocktaildb.com/api/json/v1/1/filter.php?i=Vodka
}





