package be.aminmousavi.cocktailsapp.ui.drinks.cocktails

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import be.aminmousavi.cocktailsapp.R
import be.aminmousavi.cocktailsapp.ui.components.GridScreen
import be.aminmousavi.cocktailsapp.ui.components.ErrorScreen
import be.aminmousavi.cocktailsapp.ui.components.LoadingScreen
import be.aminmousavi.cocktailsapp.ui.navigation.NavigationDestination

object CocktailDestination : NavigationDestination {
    override val route = "cocktail"
    override val titleRes = R.string.cocktail
}
@Composable
fun CocktailScreen(
    uiState: CocktailUiState,
    refreshAction: () -> Unit,
    onClickableCardItem: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    when (uiState) {
        is CocktailUiState.Loading -> LoadingScreen(
            modifier = modifier.fillMaxWidth()
        )

        is CocktailUiState.Success -> GridScreen(
            uiState.drinks,
            onClickableCardItem = { onClickableCardItem }
        )

        else -> ErrorScreen(
            refreshAction = refreshAction,
            modifier = modifier.fillMaxWidth()
        )
    }
}