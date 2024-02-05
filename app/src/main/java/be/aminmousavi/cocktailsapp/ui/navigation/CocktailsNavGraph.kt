package be.aminmousavi.cocktailsapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import be.aminmousavi.cocktailsapp.ui.home.HomeDestination

@Composable
fun CocktailsNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
   NavHost(
       navController = navController,
       startDestination = HomeDestination.route
   ) {

   }
}