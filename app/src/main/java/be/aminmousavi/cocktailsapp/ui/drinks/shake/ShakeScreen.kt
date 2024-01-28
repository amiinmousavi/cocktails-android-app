package be.aminmousavi.cocktailsapp.ui.drinks.shake

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import be.aminmousavi.cocktailsapp.ui.utils.GridScreen
import be.aminmousavi.cocktailsapp.ui.utils.ErrorScreen
import be.aminmousavi.cocktailsapp.ui.utils.LoadingScreen

@Composable
fun ShakeScreen(
    uiState: ShakeUiState,
    refreshAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    when (uiState) {
        is ShakeUiState.Loading -> LoadingScreen(
            modifier = modifier.fillMaxWidth()
        )

        is ShakeUiState.Success -> GridScreen(
            uiState.drinks
        )

        else -> ErrorScreen(
            refreshAction = refreshAction,
            modifier = modifier.fillMaxWidth()
        )
    }
}
