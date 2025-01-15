package com.example.weatherly.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.weatherly.screens.about.AboutScreen
import com.example.weatherly.screens.favourites.FavouritesScreen
import com.example.weatherly.screens.main.MainScreen
import com.example.weatherly.screens.main.MainViewModel
import com.example.weatherly.screens.search.SearchScreen
import com.example.weatherly.screens.settings.SettingsScreen
import com.example.weatherly.screens.splashscreen.WeatherSplashScreen
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun WeatherNavigation()
{
    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = WeatherScreens.SplashScreen.name)
    {
        composable(WeatherScreens.SplashScreen.name)
        {
            WeatherSplashScreen(navController = navController)
        }
        val route = WeatherScreens.MainScreen.name
        composable(route = "$route/{city}",
            arguments = listOf(
                navArgument(name = "city")
                {
                    type = NavType.StringType
                }
            ))
        { navBack ->
            navBack.arguments?.getString("city").let{city->
                val mainViewModel = hiltViewModel<MainViewModel>()
                MainScreen(
                    navController = navController,
                    mainViewModel = mainViewModel,
                    city = city
                )
            }
        }
        composable(
            route= WeatherScreens.SearchScreen.name +"/{isValid}",
            arguments = listOf(
                navArgument(name = "isValid")
                {
                    type = NavType.BoolType
                }
            )
        )
        {
            it.arguments?.getBoolean("isValid")?.let { isValid ->
                SearchScreen(
                    navController = navController,
                    isValid = isValid
                )
            }
        }
        composable(WeatherScreens.AboutScreen.name)
        {
            AboutScreen(
                navController = navController,
            )
        }
        composable(WeatherScreens.FavouritesScreen.name)
        {
            FavouritesScreen(
                navController = navController,
            )
        }
        composable(WeatherScreens.SettingsScreen.name)
        {
            SettingsScreen(
                navController = navController,
            )
        }
    }
}