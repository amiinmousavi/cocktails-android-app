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
        ClickableCardOption(labelResId = R.string.cocktail, route = "cocktail"),
    )
}