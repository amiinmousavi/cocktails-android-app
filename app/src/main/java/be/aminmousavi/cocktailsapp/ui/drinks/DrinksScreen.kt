package be.aminmousavi.cocktailsapp.ui.drinks

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import be.aminmousavi.cocktailsapp.R
import be.aminmousavi.cocktailsapp.network.Drink
import be.aminmousavi.cocktailsapp.ui.theme.CocktailsAppTheme
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun DrinksScreen(
    route: String,
    drinksUiState: DrinksUiState? = null,
    modifier: Modifier = Modifier
) {
    when (drinksUiState) {
        is DrinksUiState.Loading -> LoadingScreen(
            modifier = modifier.fillMaxWidth()
        )

//        is DrinksUiState.Success -> ResultScreen(
//            drinksUiState.drinks,
//            modifier = modifier.fillMaxWidth()
//        )

//        is DrinksUiState.Success -> DrinkCard(drink = drinksUiState.drinks, modifier = modifier)

        is DrinksUiState.Success -> DrinksGridScreen(drinksUiState.drinks)
        else -> ErrorScreen(
            modifier = modifier.fillMaxWidth()
        )
    }
}

@Composable
fun DrinksGridScreen(drinks: List<Drink>, modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(4.dp)
    ) {
        items(items = drinks, key = { drink -> drink.id }) { drink ->
            DrinkCard(drink = drink)
        }
    }
}

@Composable
fun DrinkCard(drink: Drink, modifier: Modifier = Modifier) {
    AsyncImage(
        model = ImageRequest.Builder(context = LocalContext.current)
            .data(drink.thumbnailUrl)
            .build(),
        contentDescription = stringResource(R.string.drink),
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.loading_img),
        contentDescription = stringResource(R.string.loading)
    )
}

@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error),
            contentDescription = stringResource(R.string.connection_error)
        )
        Text(text = stringResource(R.string.loading_failed), modifier = Modifier.padding(16.dp))
    }
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