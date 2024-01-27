package be.aminmousavi.cocktailsapp.ui.drinks.nonalcoholic

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
import be.aminmousavi.cocktailsapp.model.Drink
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface NonAlcoholicDrinksUiState {
    data class Success(val drinks: List<Drink>) : NonAlcoholicDrinksUiState
    object Error : NonAlcoholicDrinksUiState
    object Loading : NonAlcoholicDrinksUiState
}

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
class NonAlcoholicDrinksViewModel(private val cocktailsRepository: CocktailsRepository) : ViewModel() {
    var uiState: NonAlcoholicDrinksUiState by mutableStateOf(NonAlcoholicDrinksUiState.Loading)
        private set

    init {
        getNonAlcoholicDrinks()
    }

    fun getNonAlcoholicDrinks() {
        viewModelScope.launch {
            uiState = try {
                val listResult = cocktailsRepository.getNonAlcoholicDrinks()
                val drinks = listResult.drinks
                NonAlcoholicDrinksUiState.Success(drinks)
            } catch (e: IOException) {
                NonAlcoholicDrinksUiState.Error
            } catch(e: HttpException) {
                NonAlcoholicDrinksUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as CocktailsApplication)
                val drinksRepository = application.container.cocktailsRepository
                NonAlcoholicDrinksViewModel(cocktailsRepository = drinksRepository)
            }
        }
    }
}