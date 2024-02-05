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

sealed interface CocktailUiState {
    data class Success(val drinks: List<Drink>) : CocktailUiState
    object Error : CocktailUiState
    object Loading : CocktailUiState
}
@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
class CocktailViewModel(private val cocktailsRepository: CocktailsRepository) : ViewModel() {
    var uiState: CocktailUiState by mutableStateOf(CocktailUiState.Loading)
        private set

    init {
        getCocktails()
    }

    fun getCocktails() {
        viewModelScope.launch {
            uiState = try{
                val listResult = cocktailsRepository.getCocktails()
                val drinks = listResult.drinks
                CocktailUiState.Success(drinks)
            } catch (e: IOException) {
                CocktailUiState.Error
            } catch (e: HttpException){
                CocktailUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as CocktailsApplication)
                val drinksRepository = application.container.cocktailsRepository
                CocktailViewModel(cocktailsRepository = drinksRepository)
            }
        }
    }
}