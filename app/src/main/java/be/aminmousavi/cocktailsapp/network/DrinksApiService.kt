package be.aminmousavi.cocktailsapp.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
//import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
interface DrinksApiService {
    @GET("filter.php?a=Non_Alcoholic")
    suspend fun getNonAlcoholicDrinks(): DrinksApiResponse
    //    @GET("photos")
    //    suspend fun getNonAlcoholicDrinks(): List<MarsPhoto>
}
