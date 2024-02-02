package be.aminmousavi.cocktailsapp.ui.drinks.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import be.aminmousavi.cocktailsapp.data.CocktailsRepository
import be.aminmousavi.cocktailsapp.model.Drink
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class FavoritesViewModel(cocktailsRepository: CocktailsRepository) : ViewModel() {
    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    val favoritesUiState: StateFlow<FavoritesUiState> =
        cocktailsRepository.getAllDrinksStream().map {FavoritesUiState(it)}
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = FavoritesUiState()
            )
}

data class FavoritesUiState(val drinkList: List<Drink> = listOf()){

}