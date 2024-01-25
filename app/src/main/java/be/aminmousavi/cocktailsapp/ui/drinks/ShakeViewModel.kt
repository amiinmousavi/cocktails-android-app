package be.aminmousavi.cocktailsapp.ui.drinks

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

sealed interface ShakeUiState {
    data class Success(val drinks: List<Drink>) : ShakeUiState
    object Error : ShakeUiState
    object Loading : ShakeUiState
}

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
class ShakeViewModel(private val drinksRepository: CocktailsRepository) : ViewModel() {
    var drinksUiState: ShakeUiState by mutableStateOf(ShakeUiState.Loading)
        private set
    init {
        getShakes()
    }
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun getShakes() {
        viewModelScope.launch {
            try{
                val listResult = drinksRepository.getShakes()
                val drinks = listResult.drinks
                drinksUiState = ShakeUiState.Success(drinks)
            } catch (e: IOException) {
                drinksUiState = ShakeUiState.Error
            } catch (e: HttpException){
                drinksUiState = ShakeUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as CocktailsApplication)
                val drinksRepository = application.container.cocktailsRepository
                ShakeViewModel(drinksRepository = drinksRepository)
            }
        }
    }
}