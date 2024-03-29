package be.aminmousavi.cocktailsapp.network

import be.aminmousavi.cocktailsapp.model.Drink
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CocktailsApiResponse(
    @SerialName(value = "drinks")
    val drinks: List<Drink>
)

