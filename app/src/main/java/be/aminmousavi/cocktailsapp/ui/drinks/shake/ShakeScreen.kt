package be.aminmousavi.cocktailsapp.ui.drinks.shake

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import be.aminmousavi.cocktailsapp.R
import be.aminmousavi.cocktailsapp.ui.components.GridScreen
import be.aminmousavi.cocktailsapp.ui.components.ErrorScreen
import be.aminmousavi.cocktailsapp.ui.components.LoadingScreen
import be.aminmousavi.cocktailsapp.ui.navigation.NavigationDestination

object ShakeDestination : NavigationDestination {
    override val route = "shake"
    override val titleRes = R.string.shake
}
@Composable
fun ShakeScreen(
    uiState: ShakeUiState,
    refreshAction: () -> Unit,
    onClickableCardItem: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    when (uiState) {
        is ShakeUiState.Loading -> LoadingScreen(
            modifier = modifier.fillMaxWidth()
        )

        is ShakeUiState.Success -> GridScreen(
            uiState.drinks,
            onClickableCardItem = onClickableCardItem
        )

        else -> ErrorScreen(
            refreshAction = refreshAction,
            modifier = modifier.fillMaxWidth()
        )
    }
}
