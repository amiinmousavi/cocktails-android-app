package be.aminmousavi.cocktailsapp.ui.drinks

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import be.aminmousavi.cocktailsapp.ui.utils.DrinksGridScreen
import be.aminmousavi.cocktailsapp.ui.utils.ErrorScreen
import be.aminmousavi.cocktailsapp.ui.utils.LoadingScreen

@Composable
fun ShakeScreen(
    drinksUiState: ShakeUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    when (drinksUiState) {
        is ShakeUiState.Loading -> LoadingScreen(
            modifier = modifier.fillMaxWidth()
        )

        is ShakeUiState.Success -> DrinksGridScreen(
            drinksUiState.drinks
        )

        else -> ErrorScreen(
            retryAction = retryAction,
            modifier = modifier.fillMaxWidth()
        )
    }
}
