package be.aminmousavi.cocktailsapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import be.aminmousavi.cocktailsapp.model.Drink
import kotlinx.coroutines.flow.Flow

@Dao
interface DrinkDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(drink: Drink)
    @Update
    suspend fun update(drink: Drink)
    @Delete
    suspend fun delete(drink: Drink)
    @Query("SELECT * FROM drinks WHERE id = :id")
    fun getDrink(id: String): Flow<Drink>
    @Query("SELECT * FROM drinks ORDER BY name ASC")
    fun getAllDrinks(): Flow<List<Drink>>
}