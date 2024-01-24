package be.aminmousavi.cocktailsapp.data

import be.aminmousavi.cocktailsapp.network.DrinksApiResponse
import be.aminmousavi.cocktailsapp.network.CocktailsApiService
import be.aminmousavi.cocktailsapp.network.DrinkDetailsApiResponse

interface CocktailsRepository {
    suspend fun getNonAlcoholicDrinks(): DrinksApiResponse
    suspend fun getRandomDrinks(): DrinkDetailsApiResponse
}

class NetworkCocktailsRepository(private val cocktailsApiService: CocktailsApiService) :
    CocktailsRepository {

    override suspend fun getNonAlcoholicDrinks(): DrinksApiResponse =
        cocktailsApiService.getNonAlcoholicDrinks()

    override suspend fun getRandomDrinks(): DrinkDetailsApiResponse =
        cocktailsApiService.getRandomDrink()
}