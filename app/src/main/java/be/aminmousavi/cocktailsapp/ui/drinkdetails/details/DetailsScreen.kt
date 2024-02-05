package be.aminmousavi.cocktailsapp.ui.drinkdetails.details

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import be.aminmousavi.cocktailsapp.R
import be.aminmousavi.cocktailsapp.ui.navigation.NavigationDestination

object DetailsDestination : NavigationDestination {
    override val route = "details"
    override val titleRes = R.string.details
    const val drinkIdArg = "drinkId"
    val routeWithArgs = "$route/{$drinkIdArg}"
}
@Composable
fun DetailsScreen() {
    Text("DetailsScreen")
}