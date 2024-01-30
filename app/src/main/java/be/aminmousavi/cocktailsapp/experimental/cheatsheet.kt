package be.aminmousavi.cocktailsapp.experimental

import androidx.compose.foundation.clickable
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

// navigation button

@Composable
fun TopScreen(
    onClickableCardItem: () -> Unit
) {
    ExpScreen(
        // add reference
        onClickableElement = { onClickableCardItem }
    )
}

@Composable
fun ExpScreen(
    onClickableElement: () -> Unit,
    modifier: Modifier = Modifier
) {
    Element(
        // call
        onClick = { onClickableElement() }
    )
}

@Composable
fun Element(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            // add reference
            .clickable(onClick = onClick)
    ) {

    }
}


// navController.navigate(CocktailsScreen.Details.name)

// composable(route = CocktailsScreen.Details.name {
//      DetailsScreen(
//          ...
//      )
// }