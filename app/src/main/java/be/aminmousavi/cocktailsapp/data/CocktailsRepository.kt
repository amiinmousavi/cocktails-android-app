package be.aminmousavi.cocktailsapp.data

import be.aminmousavi.cocktailsapp.network.DrinksApiResponse
import be.aminmousavi.cocktailsapp.network.CocktailsApiService

interface CocktailsRepository {
    suspend fun getNonAlcoholicDrinks(): DrinksApiResponse
    suspend fun getShakes(): DrinksApiResponse
    suspend fun getCocktails(): DrinksApiResponse
    suspend fun getRandomDrink(): DrinksApiResponse
}

class NetworkCocktailsRepository(private val cocktailsApiService: CocktailsApiService) :
    CocktailsRepository {

    override suspend fun getNonAlcoholicDrinks(): DrinksApiResponse =
        cocktailsApiService.getNonAlcoholicDrinks()

    override suspend fun getShakes(): DrinksApiResponse =
        cocktailsApiService.getShakes()

    override suspend fun getCocktails(): DrinksApiResponse =
        cocktailsApiService.getCocktails()

    override suspend fun getRandomDrink(): DrinksApiResponse =
        cocktailsApiService.getRandomDrink()
}