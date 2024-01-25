package be.aminmousavi.cocktailsapp.ui.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import be.aminmousavi.cocktailsapp.R
import be.aminmousavi.cocktailsapp.network.Drink
import be.aminmousavi.cocktailsapp.ui.theme.CocktailsAppTheme
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun Logo(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.logo),
        contentDescription = stringResource(R.string.logo_png),

    )
}

@Composable
fun HeadingText(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        style = TextStyle(
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        ),
        modifier = modifier.padding(0.dp, 5.dp)
    )
}

@Composable
fun SubHeadingText(text: String, color: Color = Color.Black, modifier: Modifier = Modifier) {
    Text(
        text = text,
        style = TextStyle(
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = color
        ),
        modifier = modifier
    )
}

@Composable
fun TitleText(text: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .background(Color(0xFFD78F8F), RoundedCornerShape(2.dp))
            .fillMaxWidth()
    ) {
        SubHeadingText(text = text, color = Color.White, modifier = Modifier.padding(2.dp))
    }
}

@Composable
fun Paragraph(text: String, modifier: Modifier = Modifier) {
    Text(text = text, modifier = Modifier.padding(0.dp, 4.dp, 0.dp, 12.dp))
}

@Composable
fun DrinksGridScreen(drinks: List<Drink>, modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(190.dp),
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(4.dp)
    ) {
        items(items = drinks, key = { drink -> drink.id }) { drink ->
            DrinkCard(
                drink = drink,
                modifier = modifier
                    .padding(4.dp)
                    .fillMaxWidth()
            )
        }
    }
}

@Composable
fun DrinkCard(drink: Drink, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.padding(5.dp, 10.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(drink.thumbnailUrl)
                    .build(),
                contentDescription = stringResource(R.string.drink),
                modifier = Modifier
                    .fillMaxSize()
                    .clip(shape = RoundedCornerShape(8.dp)),

                )
        }
        Text(
            drink.name,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            color = Color.Black,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
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
fun ErrorScreen(
    retryAction: () -> Unit,
    modifier: Modifier = Modifier
) {
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
        Button(onClick = retryAction) {
            Text(stringResource(R.string.retry))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CocktailsAppTheme {
        Column {
            Logo()
            HeadingText("Heading Text")
            SubHeadingText(text = "Sub Heading Text")
            TitleText(text = "Title")
            Paragraph(text = "Rub the rim of the glass with the lime slice to make the salt stick to it. " +
                    "Take care to moisten only the outer rim and sprinkle the salt on it. " +
                    "The salt should present to the lips of the imbiber and never mix into the cocktail.")
        }
    }
}