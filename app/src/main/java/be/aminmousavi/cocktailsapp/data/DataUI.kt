package be.aminmousavi.cocktailsapp.data

import be.aminmousavi.cocktailsapp.R

object DataUI {
    data class ClickableCardOption(
        val labelResId: Int,
        val imageResId: Int? = null,
        val backgroundColorResId: Int,
        val fontColorResId: Int = R.color.gray,
        val imageDescriptionResId: Int? = null,
        val route: String
    )

    val clickableCardOptions = listOf(
        ClickableCardOption(
            labelResId = R.string.non_alcoholic,
            backgroundColorResId = R.color.light_gray,
            route = "non_alcoholic"
        ),
        ClickableCardOption(
            labelResId = R.string.random_drink,
            backgroundColorResId = R.color.light_pink,
            fontColorResId = R.color.white,
            route = "random_drink"
        ),
        ClickableCardOption(
            labelResId = R.string.shake,
            backgroundColorResId = R.color.light_gray,
            route = "shake"
        ),
        ClickableCardOption(
            labelResId = R.string.cocktail,
            backgroundColorResId = R.color.light_gray,
            route = "cocktail"
        ),
    )
}