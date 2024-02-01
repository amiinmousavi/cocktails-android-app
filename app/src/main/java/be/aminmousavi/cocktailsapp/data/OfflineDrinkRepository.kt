package be.aminmousavi.cocktailsapp.data

import be.aminmousavi.cocktailsapp.model.Drink
import kotlinx.coroutines.flow.Flow

class OfflineDrinkRepository(private val drinkDao:DrinkDao) : DrinkRepository {
    override fun getAllDrinksStream(): Flow<List<Drink>> = drinkDao.getAllDrinks()

    override fun getDrinkStream(id: String): Flow<Drink?> = drinkDao.getDrink(id)

    override suspend fun insertDrink(item: Drink) = drinkDao.insert(item)

    override suspend fun deleteDrink(item: Drink) = drinkDao.delete(item)

    override suspend fun updateDrink(item: Drink) = drinkDao.update(item)
}