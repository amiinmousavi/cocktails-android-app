package be.aminmousavi.cocktailsapp.data
import be.aminmousavi.cocktailsapp.network.DrinksApiResponse
import be.aminmousavi.cocktailsapp.network.DrinksApiService

interface DrinksRepository {
    suspend fun getNonAlcoholicDrinks(): DrinksApiResponse
}

class NetworkDrinksRepository(private val drinksApiService: DrinksApiService) : DrinksRepository {
//    override suspend fun getNonAlcoholicDrinks(): DrinksApiResponse {
//        return DrinksApi.retrofitService.getNonAlcoholicDrinks()
//    }

    override suspend fun getNonAlcoholicDrinks(): DrinksApiResponse =
        drinksApiService.getNonAlcoholicDrinks()
}