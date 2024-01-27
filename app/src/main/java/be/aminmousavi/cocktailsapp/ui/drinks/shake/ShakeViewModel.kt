package be.aminmousavi.cocktailsapp.ui.drinks.shake

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
import be.aminmousavi.cocktailsapp.model.Drink
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface ShakeUiState {
    data class Success(val drinks: List<Drink>) : ShakeUiState
    object Error : ShakeUiState
    object Loading : ShakeUiState
}

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
class ShakeViewModel(private val cocktailsRepository: CocktailsRepository) : ViewModel() {
    var uiState: ShakeUiState by mutableStateOf(ShakeUiState.Loading)
        private set
    init {
        getShakes()
    }
    fun getShakes() {
        viewModelScope.launch {
            uiState = try{
                val listResult = cocktailsRepository.getShakes()
                val drinks = listResult.drinks
                ShakeUiState.Success(drinks)
            } catch (e: IOException) {
                ShakeUiState.Error
            } catch (e: HttpException){
                ShakeUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as CocktailsApplication)
                val drinksRepository = application.container.cocktailsRepository
                ShakeViewModel(cocktailsRepository = drinksRepository)
            }
        }
    }
}