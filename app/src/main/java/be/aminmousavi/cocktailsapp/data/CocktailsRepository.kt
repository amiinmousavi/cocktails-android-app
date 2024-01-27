package be.aminmousavi.cocktailsapp.data

import be.aminmousavi.cocktailsapp.network.CocktailsApiResponse
import be.aminmousavi.cocktailsapp.network.CocktailsApiService

interface CocktailsRepository {
    suspend fun getNonAlcoholicDrinks(): CocktailsApiResponse
    suspend fun getShakes(): CocktailsApiResponse
    suspend fun getCocktails(): CocktailsApiResponse
    suspend fun getRandomDrink(): CocktailsApiResponse
}

class NetworkCocktailsRepository(private val cocktailsApiService: CocktailsApiService) :
    CocktailsRepository {

    override suspend fun getNonAlcoholicDrinks(): CocktailsApiResponse =
        cocktailsApiService.getNonAlcoholicDrinks()

    override suspend fun getShakes(): CocktailsApiResponse =
        cocktailsApiService.getShakes()

    override suspend fun getCocktails(): CocktailsApiResponse =
        cocktailsApiService.getCocktails()

    override suspend fun getRandomDrink(): CocktailsApiResponse =
        cocktailsApiService.getRandomDrink()
}