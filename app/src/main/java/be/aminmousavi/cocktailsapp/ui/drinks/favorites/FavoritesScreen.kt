package be.aminmousavi.cocktailsapp.ui.drinks.favorites

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import be.aminmousavi.cocktailsapp.model.Drink
import be.aminmousavi.cocktailsapp.ui.components.GridScreen

@Composable
fun FavoritesScreen(
    modifier: Modifier = Modifier,
    viewModel: FavoritesViewModel = viewModel()
) {
    val favoritesUiState by viewModel.favoritesUiState.collectAsState()
//    FavoritesBody(drinkList = favoritesUiState.drinkList)

    GridScreen(favoritesUiState.drinkList, {})
}

//@Composable
//fun FavoritesBody(
//    drinkList: List<Drink>,
//    modifier: Modifier = Modifier
//) {
//    for (drink in drinkList) {
//    }
//}

