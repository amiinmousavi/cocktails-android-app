package be.aminmousavi.cocktailsapp.network

import be.aminmousavi.cocktailsapp.model.Drink
import kotlinx.serialization.SerialName

data class DetailsApiResponse(
    @SerialName(value = "drinks")
    val drinks: List<Drink>
)