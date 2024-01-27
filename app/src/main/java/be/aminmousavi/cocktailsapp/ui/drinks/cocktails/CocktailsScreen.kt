package be.aminmousavi.cocktailsapp.ui.drinks.cocktails

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import be.aminmousavi.cocktailsapp.ui.utils.DrinksGridScreen
import be.aminmousavi.cocktailsapp.ui.utils.ErrorScreen
import be.aminmousavi.cocktailsapp.ui.utils.LoadingScreen

@Composable
fun CocktailsScreen(
    uiState: CocktailsUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    when (uiState) {
        is CocktailsUiState.Loading -> LoadingScreen(
            modifier = modifier.fillMaxWidth()
        )

        is CocktailsUiState.Success -> DrinksGridScreen(
            uiState.drinks
        )

        else -> ErrorScreen(
            retryAction = retryAction,
            modifier = modifier.fillMaxWidth()
        )
    }
}