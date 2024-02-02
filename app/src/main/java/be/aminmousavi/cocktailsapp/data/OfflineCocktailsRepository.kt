package be.aminmousavi.cocktailsapp.data

import be.aminmousavi.cocktailsapp.model.Drink
import be.aminmousavi.cocktailsapp.network.CocktailsApiResponse
import kotlinx.coroutines.flow.Flow

class OfflineCocktailsRepository(private val drinkDao: DrinkDao) : CocktailsRepository {
    override suspend fun getNonAlcoholicDrinks(): CocktailsApiResponse {
        TODO("Not yet implemented")
    }

    override suspend fun getShakes(): CocktailsApiResponse {
        TODO("Not yet implemented")
    }

    override suspend fun getCocktails(): CocktailsApiResponse {
        TODO("Not yet implemented")
    }

    override suspend fun getRandomDrink(): CocktailsApiResponse {
        TODO("Not yet implemented")
    }

    override fun getAllDrinksStream(): Flow<Drink?> = drinkDao.getAllDrinks()

    override fun getDrinkStream(id: String): Flow<Drink?> = drinkDao.getDrink(id)

    override suspend fun insertDrink(drink: Drink) = drinkDao.insert(drink)

    override suspend fun deleteDrink(drink: Drink) = drinkDao.delete(drink)

    override suspend fun updateDrink(drink: Drink) = drinkDao.update(drink)
}