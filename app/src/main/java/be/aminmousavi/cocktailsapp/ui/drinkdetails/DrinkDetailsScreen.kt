package be.aminmousavi.cocktailsapp.ui.drinkdetails

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import be.aminmousavi.cocktailsapp.network.DrinkDetails

@Composable
fun DrinkDetailsScreen(
    route: String,
    drinkDetailsUiState: DrinkDetailsUiState,
    modifier: Modifier = Modifier
) {
    Text(route)
    when (drinkDetailsUiState) {
        is DrinkDetailsUiState.Loading -> LoadingScreen(

        )
        is DrinkDetailsUiState.Success -> ResultScreen(drinkDetailsUiState.drinkDetails)
        else -> ErrorScreen()
    }
}

@Composable
fun LoadingScreen(){}
@Composable
fun ResultScreen(drinkDetails: DrinkDetails, modifier: Modifier = Modifier){
    Text(drinkDetails.strDrink)
}
@Composable
fun ErrorScreen(){}

//@Preview(showBackground = true)
//@Composable
//fun DrinkDetailsScreenPreview() {
//    CocktailsAppTheme {
//        DrinkDetailsScreen(route = "Route")
//    }
//}