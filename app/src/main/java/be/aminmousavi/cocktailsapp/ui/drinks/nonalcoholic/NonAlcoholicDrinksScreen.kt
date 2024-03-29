package be.aminmousavi.cocktailsapp.ui.drinks.nonalcoholic

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import be.aminmousavi.cocktailsapp.ui.components.GridScreen
import be.aminmousavi.cocktailsapp.ui.components.ErrorScreen
import be.aminmousavi.cocktailsapp.ui.components.LoadingScreen

@Composable
fun NonAlcoholicDrinksScreen(
    uiState: NonAlcoholicDrinksUiState,
    refreshAction: () -> Unit,
    onClickableCardItem: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    when (uiState) {
        is NonAlcoholicDrinksUiState.Loading -> LoadingScreen(
            modifier = modifier.fillMaxWidth()
        )

        is NonAlcoholicDrinksUiState.Success -> GridScreen(
            uiState.drinks,
            onClickableCardItem = onClickableCardItem
        )

        else -> ErrorScreen(
            refreshAction = refreshAction,
            modifier = modifier.fillMaxWidth()
        )
    }
}