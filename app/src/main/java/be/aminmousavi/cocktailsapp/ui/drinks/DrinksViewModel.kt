package be.aminmousavi.cocktailsapp.ui.drinks

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import be.aminmousavi.cocktailsapp.CocktailsApplication
import be.aminmousavi.cocktailsapp.data.CocktailsRepository
import be.aminmousavi.cocktailsapp.network.Drink
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface DrinksUiState {
    data class Success(val drinks: List<Drink>) : DrinksUiState
    object Error : DrinksUiState
    object Loading : DrinksUiState
}

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
class DrinksViewModel(private val drinksRepository: CocktailsRepository) : ViewModel() {
    var drinksUiState: DrinksUiState by mutableStateOf(DrinksUiState.Loading)
        private set

    init {
        getDrinksByCategory()
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun getDrinksByCategory() {
        viewModelScope.launch {
            try {
                val listResult = drinksRepository.getNonAlcoholicDrinks()
                val drinks = listResult.drinks
                drinksUiState = DrinksUiState.Success(drinks)
            } catch (e: IOException) {
                drinksUiState = DrinksUiState.Error
            }
            catch(e: HttpException) {
                drinksUiState = DrinksUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as CocktailsApplication)
                val drinksRepository = application.container.cocktailsRepository
                DrinksViewModel(drinksRepository = drinksRepository)
            }
        }
    }
}