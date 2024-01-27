package be.aminmousavi.cocktailsapp.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Drink(
    @SerialName("strDrink")
    val name: String,

    @SerialName("strDrinkThumb")
    val thumbnailUrl: String,

    @SerialName("idDrink")
    val id: String,

    @SerialName("strCategory")
    var category: String? = null,

    @SerialName("strAlcoholic")
    var alcoholic: String? = null,

    @SerialName("strGlass")
    var glass: String? = null,

    @SerialName("strInstructions")
    var instructions: String? = null,

    @SerialName("dateModified")
    var dateModified: String? = null
)