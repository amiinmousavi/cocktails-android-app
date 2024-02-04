package be.aminmousavi.cocktailsapp.ui.drinks.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import be.aminmousavi.cocktailsapp.CocktailsApplication
import be.aminmousavi.cocktailsapp.data.CocktailsRepository
import be.aminmousavi.cocktailsapp.model.Drink
import be.aminmousavi.cocktailsapp.ui.drinkdetails.randomdrink.RandomDrinkViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class FavoritesViewModel(private val cocktailsRepository: CocktailsRepository) : ViewModel() {
    companion object {
        private const val TIMEOUT_MILLIS = 5_000L

        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as CocktailsApplication)
                val cocktailsRepository = application.container.cocktailsRepository
                FavoritesViewModel(cocktailsRepository = cocktailsRepository)
            }
        }
    }

    val favoritesUiState: StateFlow<FavoritesUiState> =
        cocktailsRepository.getAllDrinksStream().map { FavoritesUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = FavoritesUiState()
            )

//    fun getAllDrinks() {
//        viewModelScope.launch {
//            uiState = try {
//                val listResult = cocktailsRepository.getAllDrinksStream()
//            }
//        }
//    }
}

data class FavoritesUiState(val drinkList: List<Drink> = listOf())
