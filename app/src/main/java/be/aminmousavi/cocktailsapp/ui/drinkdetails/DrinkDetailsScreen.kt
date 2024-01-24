package be.aminmousavi.cocktailsapp.ui.drinkdetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import be.aminmousavi.cocktailsapp.R
import be.aminmousavi.cocktailsapp.data.DataSource
import be.aminmousavi.cocktailsapp.data.DrinkDetails
import be.aminmousavi.cocktailsapp.ui.theme.CocktailsAppTheme
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun DrinkDetailsScreen(
    route: String,
    drinkDetails: DrinkDetails,
    modifier: Modifier = Modifier
) {

//    Column() {
//        Text(drinkDetails.strDrink)
//        Text(drinkDetails.strCategory)
//        Text(drinkDetails.strAlcoholic)
//        Text(drinkDetails.strGlass)
//        Text(drinkDetails.strDrinkThumb)
//        Text(drinkDetails.dateModified)
//    }

    DrinkImage(drinkDetails = drinkDetails)
}

@Composable
fun DrinkImage(drinkDetails: DrinkDetails, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.padding(5.dp).size(200.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(drinkDetails.strDrinkThumb)
                .build(),
            contentDescription = stringResource(R.string.drink),
            modifier = Modifier
                .clip(shape = RoundedCornerShape(8.dp))
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DrinkDetailsScreenPreview() {
    CocktailsAppTheme {
        DrinkDetailsScreen(route = "Random Drink", drinkDetails = DataSource.drinkDetails)
    }
}

