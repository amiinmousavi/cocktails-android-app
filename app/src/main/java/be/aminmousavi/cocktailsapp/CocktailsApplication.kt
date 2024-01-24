package be.aminmousavi.cocktailsapp

import android.app.Application
import be.aminmousavi.cocktailsapp.data.AppContainer
import be.aminmousavi.cocktailsapp.data.DefaultAppContainer

class CocktailsApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}