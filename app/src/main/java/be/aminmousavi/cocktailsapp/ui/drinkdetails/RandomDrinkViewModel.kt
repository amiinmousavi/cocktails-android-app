package be.aminmousavi.cocktailsapp.ui.drinkdetails

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import be.aminmousavi.cocktailsapp.CocktailsApplication
import be.aminmousavi.cocktailsapp.data.CocktailsRepository
import be.aminmousavi.cocktailsapp.model.Drink
import kotlinx.coroutines.launch
import java.io.IOException
import java.lang.IllegalStateException

sealed interface RandomDrinkUiState {
    data class Success(val drink: Drink, val isFavorite: Boolean = false) : RandomDrinkUiState
    object Error : RandomDrinkUiState
    object Loading : RandomDrinkUiState
}

class RandomDrinkViewModel(private val cocktailsRepository: CocktailsRepository) : ViewModel() {
    var uiState: RandomDrinkUiState by mutableStateOf(RandomDrinkUiState.Loading)
        private set

    init {
        getRandomDrink()
    }

    fun getRandomDrink() {
        viewModelScope.launch {
            uiState = try {
                val listResult = cocktailsRepository.getRandomDrink()
                val drink = listResult.drinks[0]
                RandomDrinkUiState.Success(drink)
            } catch (e: IOException) {
                RandomDrinkUiState.Error
            }
        }
    }

    fun toggleFavorite() {
        when (val currentState = uiState) {
            is RandomDrinkUiState.Success -> {
                uiState = currentState.copy(isFavorite = !currentState.isFavorite)
            }
            else -> throw IllegalStateException("Can only toggle favorite for Success state")
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as CocktailsApplication)
                val cocktailsRepository = application.container.cocktailsRepository
                RandomDrinkViewModel(cocktailsRepository = cocktailsRepository)
            }
        }
    }
}