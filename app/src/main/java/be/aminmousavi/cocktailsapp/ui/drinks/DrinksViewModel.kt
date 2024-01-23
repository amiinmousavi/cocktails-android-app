package be.aminmousavi.cocktailsapp.ui.drinks

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import be.aminmousavi.cocktailsapp.network.DrinksApi
import kotlinx.coroutines.launch
import java.io.IOException
    sealed interface DrinksUiState {
        data class Success(val drinks: String) : DrinksUiState
        object Error : DrinksUiState
        object Loading : DrinksUiState
    }

class DrinksViewModel(): ViewModel() {
    var drinksUiState: DrinksUiState by mutableStateOf(DrinksUiState.Loading)
        private set

    init {
        getDrinks()
    }

    private fun getDrinks() {
        viewModelScope.launch {
            try {
                val listResult = DrinksApi.retrofitService.getNonAlcoholicDrinks()
                drinksUiState = DrinksUiState.Success(listResult)
            } catch (e: IOException) {
                drinksUiState = DrinksUiState.Error
            }
        }
    }
}