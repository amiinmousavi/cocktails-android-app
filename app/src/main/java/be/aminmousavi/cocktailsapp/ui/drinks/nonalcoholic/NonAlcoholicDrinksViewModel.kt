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
import be.aminmousavi.cocktailsapp.network.Drink
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface NonAlcoholicDrinksUiState {
    data class Success(val drinks: List<Drink>) : NonAlcoholicDrinksUiState
    object Error : NonAlcoholicDrinksUiState
    object Loading : NonAlcoholicDrinksUiState
}

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
class NonAlcoholicDrinksViewModel(private val drinksRepository: CocktailsRepository) : ViewModel() {
    var drinksUiState: NonAlcoholicDrinksUiState by mutableStateOf(NonAlcoholicDrinksUiState.Loading)
        private set

    init {
        getNonAlcoholicDrinks()
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun getNonAlcoholicDrinks() {
        viewModelScope.launch {
            try {
                val listResult = drinksRepository.getNonAlcoholicDrinks()
                val drinks = listResult.drinks
                drinksUiState = NonAlcoholicDrinksUiState.Success(drinks)
            } catch (e: IOException) {
                drinksUiState = NonAlcoholicDrinksUiState.Error
            }
            catch(e: HttpException) {
                drinksUiState = NonAlcoholicDrinksUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as CocktailsApplication)
                val drinksRepository = application.container.cocktailsRepository
                NonAlcoholicDrinksViewModel(drinksRepository = drinksRepository)
            }
        }
    }
}