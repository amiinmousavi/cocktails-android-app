package be.aminmousavi.cocktailsapp

import androidx.annotation.StringRes
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

enum class CocktailsScreen(@StringRes val title: Int) {
    Home(title = R.string.app_name),
    NonAlcoholic(title = R.string.non_alcoholic),
    RandomDrink(title = R.string.random_drink),
    Shake(title = R.string.shake),
    Coffee(title = R.string.coffee),
    Cocktail(title = R.string.cocktail),
    OrdinaryDrink(title = R.string.ordinary_drink),
    Scotch(title = R.string.scotch)
}

@Composable
fun CocktailsApp(){
    Text("CocktailsApp")
}