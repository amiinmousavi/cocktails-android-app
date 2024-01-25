package be.aminmousavi.cocktailsapp.ui.drinks

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
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
import be.aminmousavi.cocktailsapp.network.Drink
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface CoffeeUiState {
    data class Success(val drinks: List<Drink>) : CoffeeUiState
    object Error : CoffeeUiState
    object Loading : CoffeeUiState
}
class CoffeeViewModel(private val drinksRepository: CocktailsRepository) : ViewModel() {
    var drinksUiState: CoffeeUiState by mutableStateOf(CoffeeUiState.Loading)
        private set

    init {
        getCoffee()
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun getCoffee() {
        viewModelScope.launch {
            try{
                val listResult = drinksRepository.getCoffee()
                val drinks = listResult.drinks
                drinksUiState = CoffeeUiState.Success(drinks)
            } catch (e: IOException) {
                drinksUiState = CoffeeUiState.Error
            } catch (e: HttpException){
                drinksUiState = CoffeeUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as CocktailsApplication)
                val drinksRepository = application.container.cocktailsRepository
                CoffeeViewModel(drinksRepository = drinksRepository)
            }
        }
    }
}