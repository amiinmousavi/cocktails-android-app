package be.aminmousavi.cocktailsapp.ui.drinkdetails

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
import be.aminmousavi.cocktailsapp.network.DrinkDetails
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface DrinkDetailsUiState {
    data class Success(val drinkDetails: DrinkDetails) : DrinkDetailsUiState
    object Error : DrinkDetailsUiState
    object Loading : DrinkDetailsUiState
}

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
class DrinkDetailsViewModel(private val cocktailsRepository: CocktailsRepository) : ViewModel() {
    var drinkDetailsUiState: DrinkDetailsUiState by mutableStateOf(DrinkDetailsUiState.Loading)
        private set

    init {
        getDrinkDetails()
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun getDrinkDetails() {
        viewModelScope.launch {
            drinkDetailsUiState = try {
                val listResult = cocktailsRepository.getRandomDrinks()
                val drinkDetails = listResult.drinks[0]
                DrinkDetailsUiState.Success(drinkDetails)
            } catch (e: IOException) {
                DrinkDetailsUiState.Error
            } catch (e: HttpException) {
                DrinkDetailsUiState.Loading
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as CocktailsApplication)
                val cocktailsRepository = application.container.cocktailsRepository
                DrinkDetailsViewModel(cocktailsRepository = cocktailsRepository)
            }
        }
    }
}