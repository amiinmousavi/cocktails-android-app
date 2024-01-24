package be.aminmousavi.cocktailsapp

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import be.aminmousavi.cocktailsapp.data.DataSource
import be.aminmousavi.cocktailsapp.ui.drinkdetails.DrinkDetailsScreen
import be.aminmousavi.cocktailsapp.ui.drinkdetails.DrinkDetailsViewModel
import be.aminmousavi.cocktailsapp.ui.drinks.DrinksScreen
import be.aminmousavi.cocktailsapp.ui.drinks.DrinksViewModel
import be.aminmousavi.cocktailsapp.ui.home.HomeScreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CocktailsAppBar(
    currentScreen: CocktailsScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(currentScreen.title)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),

        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {

                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }

            }
        }
    )
}

enum class CocktailsScreen(@StringRes val title: Int) {
    Home(title = R.string.app_name),
    NonAlcoholic(title = R.string.non_alcoholic),
    RandomDrink(title = R.string.random_drink),
    Shake(title = R.string.shake),
    Coffee(title = R.string.coffee),
    Cocktail(title = R.string.cocktail),
    OrdinaryDrink(title = R.string.ordinary_drink),
    Scotch(title = R.string.scotch),
    Vodka(title = R.string.vodka)
}

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CocktailsApp(navController: NavHostController = rememberNavController()) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = CocktailsScreen.valueOf(
        backStackEntry?.destination?.route ?: CocktailsScreen.Home.name
    )
    Scaffold(topBar = {
        CocktailsAppBar(
            currentScreen = currentScreen,
            canNavigateBack = navController.previousBackStackEntry != null,
            navigateUp = { navController.navigateUp() })
    }) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = CocktailsScreen.Home.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = CocktailsScreen.Home.name) {
                HomeScreen(
                    clickableCardOptions = DataSource.clickableCardOptions,
                    onClickableCardClicked = { route ->
                        when (route) {
                            CocktailsScreen.NonAlcoholic.title -> {
                                navController.navigate(CocktailsScreen.NonAlcoholic.name)
                            }

                            CocktailsScreen.RandomDrink.title -> {
                                navController.navigate(CocktailsScreen.RandomDrink.name)
                            }
//
//                            CocktailsScreen.Shake.title -> {
//                                navController.navigate(CocktailsScreen.Shake.name)
//                            }
//
//                            CocktailsScreen.Coffee.title -> {
//                                navController.navigate(CocktailsScreen.Coffee.name)
//                            }
//
//                            CocktailsScreen.Cocktail.title -> {
//                                navController.navigate(CocktailsScreen.Cocktail.name)
//                            }
//
//                            CocktailsScreen.OrdinaryDrink.title -> {
//                                navController.navigate(CocktailsScreen.OrdinaryDrink.name)
//                            }
//
//                            CocktailsScreen.Scotch.title -> {
//                                navController.navigate(CocktailsScreen.Scotch.name)
//                            }
//
//                            else -> {
//                                navController.navigate(CocktailsScreen.Vodka.name)
//                            }
                        }
                    }
                )
            }
            composable(route = CocktailsScreen.NonAlcoholic.name) {
                val drinksViewModel: DrinksViewModel =
                    viewModel(factory = DrinksViewModel.Factory)
                DrinksScreen(
                    route = "Non Alcoholic",
                    drinksUiState = drinksViewModel.drinksUiState,
                    retryAction = drinksViewModel::getDrinks
                )
            }

            composable(route = CocktailsScreen.RandomDrink.name) {
                val drinkDetailsViewModel: DrinkDetailsViewModel =
                    viewModel(factory = DrinkDetailsViewModel.Factory)
                DrinkDetailsScreen(
                    route = "Random Drink",
                    drinkDetailsUiState = drinkDetailsViewModel.drinkDetailsUiState
                )
            }

//            composable(route = CocktailsScreen.Shake.name) {
//                DrinksScreen(route = "Shake")
//            }
//
//            composable(route = CocktailsScreen.Coffee.name) {
//                DrinksScreen(route = "Coffee")
//            }
//
//            composable(route = CocktailsScreen.Cocktail.name) {
//                DrinksScreen(route = "Cocktail")
//            }
//
//            composable(route = CocktailsScreen.OrdinaryDrink.name) {
//                DrinksScreen(route = "Ordinary Drink")
//            }
//
//            composable(route = CocktailsScreen.Scotch.name) {
//                DrinksScreen(route = "Scotch")
//            }
//
//            composable(route = CocktailsScreen.Vodka.name) {
//                DrinksScreen(route = "Vodka")
//            }

        }
    }
}