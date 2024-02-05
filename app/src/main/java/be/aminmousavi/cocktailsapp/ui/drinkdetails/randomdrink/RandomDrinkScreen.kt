package be.aminmousavi.cocktailsapp.ui.drinkdetails.randomdrink

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import be.aminmousavi.cocktailsapp.R
import be.aminmousavi.cocktailsapp.model.Drink
import be.aminmousavi.cocktailsapp.ui.components.ErrorScreen
import be.aminmousavi.cocktailsapp.ui.components.HeadingText
import be.aminmousavi.cocktailsapp.ui.components.LoadingScreen
import be.aminmousavi.cocktailsapp.ui.components.Paragraph
import be.aminmousavi.cocktailsapp.ui.components.SubHeadingText
import be.aminmousavi.cocktailsapp.ui.navigation.NavigationDestination
import coil.compose.AsyncImage
import coil.request.ImageRequest

object RandomDrinkDestination : NavigationDestination {
    override val route = "random_drink"
    override val titleRes = R.string.random_drink
}
@Composable
fun RandomDrinkScreen(
    uiState: RandomDrinkUiState,
    saveItem: () -> Unit,
    refreshAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    when (uiState) {
        is RandomDrinkUiState.Loading -> LoadingScreen(
            modifier = modifier.fillMaxSize()
        )

        is RandomDrinkUiState.Success -> ResultScreen(
            uiState.drink,
            uiState.isFavorite,
            saveItem,
            refreshAction = refreshAction
        )

        else -> ErrorScreen(
            refreshAction = refreshAction,
            modifier = modifier.fillMaxWidth()
        )
    }
}

@Composable
fun ResultScreen(
    drink: Drink,
    isFavorite: Boolean,
    saveItem: () -> Unit,
    refreshAction: () -> Unit,
    modifier: Modifier = Modifier
) {
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
                DrinkImageWithHeartIcon(
                    thumbnailUrl = drink.thumbnailUrl,
                    isFavorite = isFavorite,
                    onClick = saveItem
                )
            }
            drink.dateModified?.let { Paragraph("Date modified: $it") }

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
                SubHeadingText(text = "Instructions")
                drink.instructions?.let { Paragraph(text = it) }
            }
        }
        Button(
            onClick = refreshAction,
            modifier = Modifier.align(Alignment.BottomEnd)
        ) {
            Icon(
                imageVector = Icons.Filled.Refresh,
                contentDescription = stringResource(R.string.refresh)
            )
        }
    }
}

@Composable
fun DrinkInfo(info: String? = "", modifier: Modifier = Modifier) {
    if (info != null) {
        Box(
            modifier = Modifier
                .background(
                    Color(0xFFD78F8F),
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(8.dp) // Adjust the padding as needed
        ) {
            Text(
                info, color = Color(0xFFFFFFFF),
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
    }
}

@Composable
fun DrinkImageWithHeartIcon(
    thumbnailUrl: String,
    isFavorite: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        DrinkImage(thumbnailUrl = thumbnailUrl, modifier = Modifier.fillMaxSize())

        val heartIcon = if (isFavorite) {
            R.drawable.ic_heart_filled
        } else {
            R.drawable.ic_heart_outlined
        }

        Icon(
            painter = painterResource(id = heartIcon),
            contentDescription = null,
            modifier = Modifier
                .size(76.dp)
                .padding(16.dp)
                .align(Alignment.BottomEnd)
                .clickable(onClick = onClick)
        )
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
