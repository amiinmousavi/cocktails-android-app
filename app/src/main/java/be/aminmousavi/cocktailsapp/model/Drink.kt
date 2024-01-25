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
    val id: String
)