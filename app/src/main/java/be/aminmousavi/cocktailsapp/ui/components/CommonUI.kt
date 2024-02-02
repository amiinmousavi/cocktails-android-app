package be.aminmousavi.cocktailsapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Surface
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
import be.aminmousavi.cocktailsapp.model.Drink
import be.aminmousavi.cocktailsapp.ui.theme.CocktailsAppTheme
import coil.compose.AsyncImage
import coil.request.ImageRequest

// Logo Composable
@Composable
fun Logo(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.logo),
        contentDescription = stringResource(R.string.logo_png),
        modifier = Modifier.padding(0.dp, 16.dp, 0.dp, 0.dp)
    )
}

// Text Composables
@Composable
fun HeadingText(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        style = TextStyle(
            fontSize = 35.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        ),
        modifier = modifier.padding(0.dp, 0.dp, 0.dp, 8.dp)
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
        modifier = modifier.padding(0.dp, 14.dp, 0.dp, 4.dp)
    )
}

// TODO misschien later verwijderen (wordt nergens gebruikt)
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

// Network Composables
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
    refreshAction: () -> Unit,
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
        Button(onClick = refreshAction) {
            Text(stringResource(R.string.refresh))
        }
    }
}

// List Composables
@Composable
fun GridScreen(
    drinks: List<Drink>,
    onClickableCardItem: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(190.dp),
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(4.dp)
    ) {
        items(items = drinks, key = { drink -> drink.id }) { drink ->
            CardItem(
                drink = drink,
                onClick = { onClickableCardItem(drink.id) },
                modifier = modifier
                    .padding(4.dp)
                    .fillMaxWidth()
            )
        }
    }
}

@Composable
fun CardItem(
    drink: Drink,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.clickable(onClick = onClick)
    ) {
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
}