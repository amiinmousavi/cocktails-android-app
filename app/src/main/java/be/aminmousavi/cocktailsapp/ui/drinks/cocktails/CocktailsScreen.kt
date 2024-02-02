package be.aminmousavi.cocktailsapp.ui.drinks.cocktails

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import be.aminmousavi.cocktailsapp.ui.components.GridScreen
import be.aminmousavi.cocktailsapp.ui.components.ErrorScreen
import be.aminmousavi.cocktailsapp.ui.components.LoadingScreen

@Composable
fun CocktailsScreen(
    uiState: CocktailsUiState,
    refreshAction: () -> Unit,
    onClickableCardItem: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    when (uiState) {
        is CocktailsUiState.Loading -> LoadingScreen(
            modifier = modifier.fillMaxWidth()
        )

        is CocktailsUiState.Success -> GridScreen(
            uiState.drinks,
            onClickableCardItem = { onClickableCardItem }
        )

        else -> ErrorScreen(
            refreshAction = refreshAction,
            modifier = modifier.fillMaxWidth()
        )
    }
}