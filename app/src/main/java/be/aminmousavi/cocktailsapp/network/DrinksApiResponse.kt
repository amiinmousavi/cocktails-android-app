package be.aminmousavi.cocktailsapp.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DrinksApiResponse(
    @SerialName(value = "drinks")
    val drinks: List<Drink>
)

@Serializable
data class Drink(
    @SerialName("strDrink")
    val name: String,

    @SerialName("strDrinkThumb")
    val thumbnailUrl: String,

    @SerialName("idDrink")
    val id: String
)