package be.aminmousavi.cocktailsapp.data
import be.aminmousavi.cocktailsapp.network.DrinksApiResponse
import be.aminmousavi.cocktailsapp.network.CocktailsApiService

interface CocktailsRepository {
    suspend fun getNonAlcoholicDrinks(): DrinksApiResponse
}

class NetworkCocktailsRepository(private val drinksApiService: CocktailsApiService) : CocktailsRepository {

    override suspend fun getNonAlcoholicDrinks(): DrinksApiResponse =
        drinksApiService.getNonAlcoholicDrinks()
}