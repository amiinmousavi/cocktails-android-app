package be.aminmousavi.cocktailsapp.ui.drinkdetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import be.aminmousavi.cocktailsapp.R
import be.aminmousavi.cocktailsapp.model.Drink
import be.aminmousavi.cocktailsapp.ui.utils.ErrorScreen
import be.aminmousavi.cocktailsapp.ui.utils.HeadingText
import be.aminmousavi.cocktailsapp.ui.utils.LoadingScreen
import be.aminmousavi.cocktailsapp.ui.utils.Paragraph
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun RandomDrinkScreen(
    uiState: RandomDrinkUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    when (uiState) {
        is RandomDrinkUiState.Loading -> LoadingScreen(
            modifier = modifier.fillMaxSize()
        )

        is RandomDrinkUiState.Success -> ResultScreen(
            uiState.drink
        )

        else -> ErrorScreen(
            retryAction = retryAction,
            modifier = modifier.fillMaxWidth()
        )
    }
}

@Composable
fun ResultScreen(drink: Drink, modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .weight(3f)
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
            ) {
                DrinkImage(
                    thumbnailUrl = drink.thumbnailUrl,
                )
            }
            drink.dateModified?.let { Paragraph("Date modified: $it") }

            // Other content takes up the remaining two-thirds
            Column(
                modifier = Modifier
                    .weight(4f)
                    .fillMaxWidth()
            ) {
                HeadingText(drink.name)
                Row(
                ) {
                    DrinkInfo(drink.alcoholic)
                    DrinkInfo(drink.category)
                }

            }
        }
    }
}

@Composable
fun DrinkInfo(info: String? = "", modifier: Modifier = Modifier) {
    if (info != null) {
        Box(
            modifier = Modifier
                .background(Color(0xFFD78F8F), shape = RoundedCornerShape(8.dp))
                .padding(8.dp) // Adjust the padding as needed
        ) {
            Text(info, color = Color(0xFFFFFFFF), fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.width(8.dp))

    }
}

@Composable
fun DrinkImage(thumbnailUrl: String, modifier: Modifier = Modifier) {
    AsyncImage(
        model = ImageRequest.Builder(context = LocalContext.current)
            .data(thumbnailUrl)
            .build(),
        contentDescription = stringResource(R.string.drink),
        modifier = Modifier
            .fillMaxSize()
            .clip(shape = RoundedCornerShape(8.dp)),
        contentScale = ContentScale.Crop
    )
}
