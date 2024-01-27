package be.aminmousavi.cocktailsapp.ui.drinkdetails

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import be.aminmousavi.cocktailsapp.ui.utils.ErrorScreen
import be.aminmousavi.cocktailsapp.ui.utils.LoadingScreen

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
            uiState.drinks, modifier = modifier.fillMaxWidth()
        )

        is RandomDrinkUiState.Error -> ErrorScreen(
            retryAction = retryAction,
            modifier
        )
    }
}

@Composable
fun ResultScreen(drink: String, modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        Text(text = drink)
    }
}

//@Composable
//fun LoadingScreen(modifier: Modifier = Modifier) {
//    Image(
//        modifier = modifier.size(200.dp),
//        painter = painterResource(R.drawable.loading_img),
//        contentDescription = stringResource(R.string.loading)
//    )
//}
//
//@Composable
//fun ErrorScreen(modifier: Modifier = Modifier) {
//    Column(
//        modifier = modifier,
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Image(
//            painter = painterResource(id = R.drawable.ic_connection_error), contentDescription = ""
//        )
//        Text(text = stringResource(R.string.loading_failed), modifier = Modifier.padding(16.dp))
//    }
//}