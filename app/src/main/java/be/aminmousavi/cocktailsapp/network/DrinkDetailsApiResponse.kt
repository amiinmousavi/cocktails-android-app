package be.aminmousavi.cocktailsapp.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DrinkDetailsApiResponse(
    @SerialName(value = "drinks")
    val drinks: List<DrinkDetails>
)

@Serializable
data class DrinkDetails(
    @SerialName(value = "idDrink")
    val idDrink: String,

    @SerialName(value = "strDrink")
    val strDrink: String,

    @SerialName(value = "strCategory")
    val strCategory: String,

    @SerialName(value = "strAlcoholic")
    val strAlcoholic: String,

    @SerialName(value = "strGlass")
    val strGlass: String,

    @SerialName(value = "strInstructions")
    val strInstructions: String,

    @SerialName(value = "strDrinkThumb")
    val strDrinkThumb: String,

    @SerialName(value = "strIngredient1")
    val strIngredient1: String,

    @SerialName(value = "strIngredient2")
    val strIngredient2: String?,

    @SerialName(value = "strIngredient3")
    val strIngredient3: String?,

    @SerialName(value = "strIngredient4")
    val strIngredient4: String?,

    @SerialName(value = "strIngredient5")
    val strIngredient5: String?,

    @SerialName(value = "strIngredient6")
    val strIngredient6: String?,

    @SerialName(value = "strIngredient7")
    val strIngredient7: String?,

    @SerialName(value = "strIngredient8")
    val strIngredient8: String?,

    @SerialName(value = "strIngredient9")
    val strIngredient9: String?,

    @SerialName(value = "strIngredient10")
    val strIngredient10: String?,

    @SerialName(value = "strMeasure1")
    val strMeasure1: String,

    @SerialName(value = "strMeasure2")
    val strMeasure2: String?,

    @SerialName(value = "strMeasure3")
    val strMeasure3: String?,

    @SerialName(value = "strMeasure4")
    val strMeasure4: String?,

    @SerialName(value = "strMeasure5")
    val strMeasure5: String?,

    @SerialName(value = "strMeasure6")
    val strMeasure6: String?,

    @SerialName(value = "strMeasure7")
    val strMeasure7: String?,

    @SerialName(value = "strMeasure8")
    val strMeasure8: String?,

    @SerialName(value = "strMeasure9")
    val strMeasure9: String?,

    @SerialName(value = "strMeasure10")
    val strMeasure10: String?,

    @SerialName(value = "dateModified")
    val dateModified: String
)