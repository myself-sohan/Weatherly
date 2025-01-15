package com.example.weatherly.screens.favourites

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.weatherly.components.WeatherAppBar
import com.example.weatherly.model.Favourite
import com.example.weatherly.navigation.WeatherScreens

@Composable
fun FavouritesScreen(
    navController: NavController,
    favouriteViewModel: FavouriteViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = {
            WeatherAppBar(
                title = "Favourite Cities",
                navController = navController,
                icon = Icons.AutoMirrored.Default.ArrowBack,
                isMainScreen = false,
            )
            {
                navController.popBackStack()
            }
        }
    )
    {
        Surface(
            modifier = Modifier
                .padding(it)
                .fillMaxWidth()
        )
        {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {
                val list = favouriteViewModel.favList.collectAsState().value
                LazyColumn()
                {
                    items(list){
                        CityRow(it, navController = navController, favouriteViewModel)
                    }
                }
            }
        }
    }
}

@Composable
fun CityRow(favourite: Favourite, navController: NavController, favouriteViewModel: FavouriteViewModel)
{
    Surface(
        modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth()
            .height(100.dp)
            .clickable{
                navController.navigate(WeatherScreens.MainScreen.name+"/${favourite.city.trim()}")
            },
        shape = CircleShape.copy(topEnd = CornerSize(15)),
        color = Color(0XFFB2DFDB)
    )
    {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        )
        {
            Text(
                text = favourite.city,
                style = MaterialTheme.typography.titleLarge
            )
            Surface(
                modifier = Modifier.padding(0.dp),
                shape = CircleShape,
                color = Color(0xFFD1E3E1)
            )
            {
                Text(
                    text = favourite.country,
                    modifier = Modifier.padding(4.dp),
                    style = MaterialTheme.typography.titleMedium
                )
            }
//            Icon(
//                imageVector = Icons.Default.Delete,
//                contentDescription = "Delete Icon",
//                modifier = Modifier.clickable {
//                    favouriteViewModel.deleteFavourite(favourite)
//                },
//                tint = Color.Red.copy(alpha = 0.3f)
//            )
        }
    }
}