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
class DrinksViewModel(private val cocktailsRepository: CocktailsRepository) : ViewModel() {
    var drinksUiState: DrinksUiState by mutableStateOf(DrinksUiState.Loading)
        private set

    init {
        getDrinks()
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun getDrinks() {
        viewModelScope.launch {
            drinksUiState = try {
                val listResult = cocktailsRepository.getNonAlcoholicDrinks()
                val drinks = listResult.drinks
                DrinksUiState.Success(drinks)
            } catch (e: IOException) {
                DrinksUiState.Error
            } catch (e: HttpException) {
                DrinksUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as CocktailsApplication)
                val cocktailsRepository = application.container.cocktailsRepository
                DrinksViewModel(cocktailsRepository = cocktailsRepository)
            }
        }
    }
}