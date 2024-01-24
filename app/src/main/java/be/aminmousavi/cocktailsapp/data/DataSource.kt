package be.aminmousavi.cocktailsapp.data

import be.aminmousavi.cocktailsapp.R

object DataSource {
    data class ClickableCardOption(
        val labelResId: Int,
        val imageResId: Int? = null,
        val imageDescriptionResId: Int? = null,
        val route: String
    )

    val clickableCardOptions = listOf(
        ClickableCardOption(labelResId = R.string.non_alcoholic, route = "non_alcoholic"),
        ClickableCardOption(labelResId = R.string.random_drink, route = "random_drink"),
        ClickableCardOption(labelResId = R.string.shake, route = "shake"),
        ClickableCardOption(labelResId = R.string.coffee, route = "coffee"),
        ClickableCardOption(labelResId = R.string.cocktail, route = "cocktail"),
        ClickableCardOption(labelResId = R.string.ordinary_drink, route = "ordinary_drink"),
        ClickableCardOption(labelResId = R.string.scotch, route = "scotch"),
        ClickableCardOption(labelResId = R.string.vodka, route = "vodka")
    )

    val drinkDetails = DrinkDetails(
        strDrink = "Jack Rose Cocktail",
        strCategory = "Ordinary Drink",
        strAlcoholic = "Alcoholic",
        strGlass = "Cocktail Glass",
        strDrinkThumb = "https://www.thecocktaildb.com/images/media/drink/uuqqrv1439907068.jpg",
        dateModified = "2015-08-18 15:11:08"
    )
}

data class DrinkDetails(
    val strDrink: String,
    val strCategory: String,
    val strAlcoholic: String,
    val strGlass: String,
    val strDrinkThumb: String,
    val dateModified: String
)
