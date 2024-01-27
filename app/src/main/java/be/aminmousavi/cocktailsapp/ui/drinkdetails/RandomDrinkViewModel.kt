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
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface RandomDrinkUiState {
    data class Success(val drinks: String) : RandomDrinkUiState
    object Error : RandomDrinkUiState
    object Loading : RandomDrinkUiState
}

class RandomDrinkViewModel(private val drinksRepository: CocktailsRepository) : ViewModel() {
    var randomDrinkUiState: RandomDrinkUiState by mutableStateOf(RandomDrinkUiState.Loading)
        private set

    init {
        getMarsPhotos()
    }

    fun getMarsPhotos() {
        viewModelScope.launch {
            randomDrinkUiState = try {
                val listResult = drinksRepository.getRandomDrink()
                RandomDrinkUiState.Success("${listResult.drinks}")
            } catch (e: IOException) {
                RandomDrinkUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as CocktailsApplication)
                val drinksRepository = application.container.cocktailsRepository
                RandomDrinkViewModel(drinksRepository = drinksRepository)
            }
        }
    }
}