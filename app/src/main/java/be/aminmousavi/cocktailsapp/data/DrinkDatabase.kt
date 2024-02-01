package be.aminmousavi.cocktailsapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import be.aminmousavi.cocktailsapp.model.Drink

@Database(entities = [Drink::class], version = 1, exportSchema = false)
abstract class DrinkDatabase : RoomDatabase(){
    abstract fun drinkDao(): DrinkDao

    companion object {
        @Volatile
        private var Instance: DrinkDatabase? = null

        fun getDatabase(context: Context): DrinkDatabase{
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, DrinkDatabase::class.java, "drink_database")
                    .build()
                    .also { Instance = it}
            }
        }
    }
}