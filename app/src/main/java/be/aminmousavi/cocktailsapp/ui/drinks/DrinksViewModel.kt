package be.aminmousavi.cocktailsapp.ui.drinks

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import be.aminmousavi.cocktailsapp.DrinksApplication
import be.aminmousavi.cocktailsapp.data.DrinksRepository
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface DrinksUiState {
    data class Success(val drinks: String) : DrinksUiState
    object Error : DrinksUiState
    object Loading : DrinksUiState
}

class DrinksViewModel(private val drinksRepository: DrinksRepository) : ViewModel() {
    var drinksUiState: DrinksUiState by mutableStateOf(DrinksUiState.Loading)
        private set

    init {
        getDrinks()
    }

    private fun getDrinks() {
        viewModelScope.launch {
            try {
                val listResult = drinksRepository.getNonAlcoholicDrinks()
                drinksUiState =
                    DrinksUiState.Success("Success: ${listResult.drinks.size} drinks retrieved.")
            } catch (e: IOException) {
                drinksUiState = DrinksUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as DrinksApplication)
                val drinksRepository = application.container.drinksRepository
                DrinksViewModel(drinksRepository = drinksRepository)
            }
        }
    }
}