package be.aminmousavi.cocktailsapp.ui.drinks.cocktails

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
import be.aminmousavi.cocktailsapp.model.Drink

import kotlinx.coroutines.launch
import java.io.IOException

sealed interface CocktailsUiState {
    data class Success(val drinks: List<Drink>) : CocktailsUiState
    object Error : CocktailsUiState
    object Loading : CocktailsUiState
}
@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
class CocktailsViewModel(private val cocktailsRepository: CocktailsRepository) : ViewModel() {
    var uiState: CocktailsUiState by mutableStateOf(CocktailsUiState.Loading)
        private set

    init {
        getCocktails()
    }

    fun getCocktails() {
        viewModelScope.launch {
            uiState = try{
                val listResult = cocktailsRepository.getCocktails()
                val drinks = listResult.drinks
                CocktailsUiState.Success(drinks)
            } catch (e: IOException) {
                CocktailsUiState.Error
            } catch (e: HttpException){
                CocktailsUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as CocktailsApplication)
                val drinksRepository = application.container.cocktailsRepository
                CocktailsViewModel(cocktailsRepository = drinksRepository)
            }
        }
    }
}