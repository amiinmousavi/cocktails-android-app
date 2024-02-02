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
    fun getAllDrinksStream(): Flow<List<Drink>>
    fun getDrinkStream(id: String): Flow<Drink?>
    suspend fun insertDrink(drink: Drink)
    suspend fun deleteDrink(drink: Drink)
    suspend fun updateDrink(drink: Drink)
}


class NetworkCocktailsRepository(
    private val cocktailsApiService: CocktailsApiService,
    private val drinkDao: DrinkDao
) :
    CocktailsRepository {

    override suspend fun getNonAlcoholicDrinks(): CocktailsApiResponse =
        cocktailsApiService.getNonAlcoholicDrinks()

    override suspend fun getShakes(): CocktailsApiResponse =
        cocktailsApiService.getShakes()

    override suspend fun getCocktails(): CocktailsApiResponse =
        cocktailsApiService.getCocktails()

    override suspend fun getRandomDrink(): CocktailsApiResponse =
        cocktailsApiService.getRandomDrink()
    override suspend fun insertDrink(drink: Drink) = drinkDao.insert(drink)
    override suspend fun deleteDrink(drink: Drink) = drinkDao.delete(drink)
    override suspend fun updateDrink(drink: Drink) = drinkDao.update(drink)
    override fun getAllDrinksStream(): Flow<List<Drink>> = drinkDao.getAllDrinks()
    override fun getDrinkStream(id: String): Flow<Drink?> = drinkDao.getDrink(id)
}