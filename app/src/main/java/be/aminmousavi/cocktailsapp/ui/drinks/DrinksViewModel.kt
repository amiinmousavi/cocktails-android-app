package be.aminmousavi.cocktailsapp.ui.drinks

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import be.aminmousavi.cocktailsapp.network.DrinksApi
import kotlinx.coroutines.launch

class DrinksViewModel(): ViewModel() {
    var drinksUiState: String by mutableStateOf("")
        private set

    init {
        getDrinks()
    }

    private fun getDrinks() {
        viewModelScope.launch {
            val listResult = DrinksApi.retrofitService.getNonAlcoholicDrinks()
            drinksUiState = listResult
        }
    }
}