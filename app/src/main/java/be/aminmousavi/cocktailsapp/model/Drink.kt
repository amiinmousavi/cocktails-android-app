package be.aminmousavi.cocktailsapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Entity(tableName = "drinks")
@Serializable
data class Drink(
    @SerialName("strDrink")
    val name: String,

    @SerialName("strDrinkThumb")
    val thumbnailUrl: String,
    
    @PrimaryKey
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