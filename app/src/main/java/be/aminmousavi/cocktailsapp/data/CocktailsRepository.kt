package be.aminmousavi.cocktailsapp.data

import be.aminmousavi.cocktailsapp.model.Drink
import be.aminmousavi.cocktailsapp.network.CocktailsApiResponse
import be.aminmousavi.cocktailsapp.network.CocktailsApiService
import kotlinx.coroutines.flow.Flow

interface CocktailsRepository {
    // api
    suspend fun getNonAlcoholicDrinks(): CocktailsApiResponse
    suspend fun getShakes(): CocktailsApiResponse
    suspend fun getCocktails(): CocktailsApiResponse
    suspend fun getRandomDrink(): CocktailsApiResponse

    // db
    fun getAllDrinksStream(): Flow<Drink?>
    fun getDrinkStream(id: String): Flow<Drink?>
    suspend fun insertDrink(drink: Drink)
    suspend fun deleteDrink(drink: Drink)
    suspend fun updateDrink(drink: Drink)
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

    override fun getAllDrinksStream(): Flow<Drink?> {
        TODO("Not yet implemented")
    }

    override fun getDrinkStream(id: String): Flow<Drink?> {
        TODO("Not yet implemented")
    }

    override suspend fun insertDrink(drink: Drink) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteDrink(drink: Drink) {
        TODO("Not yet implemented")
    }

    override suspend fun updateDrink(drink: Drink) {
        TODO("Not yet implemented")
    }
}