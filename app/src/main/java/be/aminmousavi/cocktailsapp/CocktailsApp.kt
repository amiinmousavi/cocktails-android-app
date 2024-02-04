package be.aminmousavi.cocktailsapp

import android.os.Build
import android.util.Log
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import be.aminmousavi.cocktailsapp.data.DataUI
import be.aminmousavi.cocktailsapp.ui.drinkdetails.details.DetailsScreen
import be.aminmousavi.cocktailsapp.ui.drinkdetails.randomdrink.RandomDrinkScreen
import be.aminmousavi.cocktailsapp.ui.drinkdetails.randomdrink.RandomDrinkViewModel
import be.aminmousavi.cocktailsapp.ui.drinks.cocktails.CocktailsScreen
import be.aminmousavi.cocktailsapp.ui.drinks.cocktails.CocktailsViewModel
import be.aminmousavi.cocktailsapp.ui.drinks.favorites.FavoritesScreen
import be.aminmousavi.cocktailsapp.ui.drinks.favorites.FavoritesViewModel
import be.aminmousavi.cocktailsapp.ui.drinks.nonalcoholic.NonAlcoholicDrinksScreen
import be.aminmousavi.cocktailsapp.ui.drinks.nonalcoholic.NonAlcoholicDrinksViewModel
import be.aminmousavi.cocktailsapp.ui.drinks.shake.ShakeScreen
import be.aminmousavi.cocktailsapp.ui.drinks.shake.ShakeViewModel
import be.aminmousavi.cocktailsapp.ui.home.HomeScreen
import kotlinx.coroutines.launch


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
    Cocktail(title = R.string.cocktail),
    Details(title = R.string.details),
    Favorites(title = R.string.favorites)
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
                    clickableCardOptions = DataUI.clickableCardOptions,
                    onClickableCardClicked = { route ->
                        when (route) {
                            CocktailsScreen.NonAlcoholic.title -> {
                                navController.navigate(CocktailsScreen.NonAlcoholic.name)
                            }

                            CocktailsScreen.Shake.title -> {
                                navController.navigate(CocktailsScreen.Shake.name)
                            }

                            CocktailsScreen.Cocktail.title -> {
                                navController.navigate(CocktailsScreen.Cocktail.name)
                            }

                            CocktailsScreen.RandomDrink.title -> {
                                navController.navigate(CocktailsScreen.RandomDrink.name)
                            }

                            CocktailsScreen.Favorites.title -> {
                                navController.navigate(CocktailsScreen.Favorites.name)
                            }
                        }
                    }
                )
            }
            composable(route = CocktailsScreen.NonAlcoholic.name) {
                val viewModel: NonAlcoholicDrinksViewModel =
                    viewModel(factory = NonAlcoholicDrinksViewModel.Factory)
                NonAlcoholicDrinksScreen(
                    uiState = viewModel.uiState,
                    refreshAction = viewModel::getNonAlcoholicDrinks,
                    onClickableCardItem = { drinkId ->
                        // drinkId wordt doorgegeven maar navigeren met argument lukt momenteel niet
                        Log.i("DrinkId", drinkId)
                    }
                )
            }

            composable(route = CocktailsScreen.Shake.name) {
                val viewModel: ShakeViewModel = viewModel(factory = ShakeViewModel.Factory)
                ShakeScreen(
                    uiState = viewModel.uiState,
                    refreshAction = viewModel::getShakes,
                    onClickableCardItem = { drinkId ->
                        Log.i("DrinkId", drinkId)
                    }
                )
            }

            composable(route = CocktailsScreen.Cocktail.name) {
                val viewModel: CocktailsViewModel =
                    viewModel(factory = CocktailsViewModel.Factory)
                CocktailsScreen(
                    uiState = viewModel.uiState,
                    refreshAction = viewModel::getCocktails,
                    onClickableCardItem = { drinkId ->
                        Log.i("DrinkId", drinkId)
                    }
                )
            }

            composable(route = CocktailsScreen.RandomDrink.name) {
                val viewModel: RandomDrinkViewModel =
                    viewModel(factory = RandomDrinkViewModel.Factory)
                val coroutineScope = rememberCoroutineScope()
                RandomDrinkScreen(
                    uiState = viewModel.uiState,
                    saveItem = {
                        coroutineScope.launch {
                            viewModel.saveItem()
                        }
                    },
                    refreshAction = viewModel::getRandomDrink
                )
            }

            composable(route = CocktailsScreen.Details.name) {
                DetailsScreen(

                )
            }

            composable(route = CocktailsScreen.Favorites.name) {
                val viewModel: FavoritesViewModel =
                    viewModel(factory = FavoritesViewModel.Factory)
//                val coroutineScope = rememberCoroutineScope()
                FavoritesScreen(
                    viewModel = viewModel,
                    // TODO (fix navigation)
                    navigateToDrinkEntry = { navController.navigate("${CocktailsScreen.Details.name}/${it}") })
            }
        }
    }
}