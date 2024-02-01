package be.aminmousavi.cocktailsapp.data

import be.aminmousavi.cocktailsapp.model.Drink
import kotlinx.coroutines.flow.Flow

interface DrinkRepository {
    fun getAllDrinksStream(): Flow<List<Drink>>

    fun getDrinkStream(id: String): Flow<Drink?>

    suspend fun insertDrink(drink: Drink)

    suspend fun deleteDrink(drink: Drink)

    suspend fun updateDrink(drink: Drink)
}
