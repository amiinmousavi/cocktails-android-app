package be.aminmousavi.cocktailsapp.ui.drinks

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import be.aminmousavi.cocktailsapp.R
import be.aminmousavi.cocktailsapp.ui.theme.CocktailsAppTheme

@Composable
fun DrinksScreen(
    route: String,
    drinks: String = "Drinks placeholder",
    modifier: Modifier = Modifier
) {
    ResultScreen(drinks)
}

@Composable
fun ResultScreen(muscles: String, modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        Text(text = muscles)
    }
}

@Preview(showBackground = true)
@Composable
fun ResultScreenPreview() {
    CocktailsAppTheme {
        ResultScreen("Placeholder text")
    }
}