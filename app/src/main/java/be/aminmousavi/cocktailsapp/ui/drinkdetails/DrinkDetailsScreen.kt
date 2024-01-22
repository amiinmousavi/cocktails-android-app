package be.aminmousavi.cocktailsapp.ui.drinkdetails

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import be.aminmousavi.cocktailsapp.ui.theme.CocktailsAppTheme

@Composable
fun DrinkDetailsScreen(route: String, modifier: Modifier = Modifier) {
    Text(route)
}

@Preview(showBackground = true)
@Composable
fun DrinkDetailsScreenPreview() {
    CocktailsAppTheme {
        DrinkDetailsScreen(route = "Route")
    }
}

