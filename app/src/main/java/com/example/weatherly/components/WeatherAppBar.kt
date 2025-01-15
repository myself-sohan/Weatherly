package com.example.weatherly.components

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.weatherly.model.Favourite
import com.example.weatherly.screens.favourites.FavouriteViewModel

@OptIn(ExperimentalMaterial3Api::class)
//@Preview
@Composable
fun WeatherAppBar(
    title: String = "Title",
    icon: ImageVector? = null,
    isMainScreen: Boolean = true,
    elevation: Dp = 0.dp,
    favouriteViewModel: FavouriteViewModel = hiltViewModel(),
    navController: NavController,
    onAddActionClicked: () -> Unit = {},
    onButtonClicked: (Any?) -> Unit = {}
    )
{
    val showDialog = remember() {
        mutableStateOf(false)
    }

    Card(
        elevation = CardDefaults.cardElevation(elevation)
    ) {
        if (showDialog.value)
            ShowSettingDropDownMenu(
                showDialog = showDialog,
                navController = navController,
            )
        TopAppBar(
            title = {
                Row(
                    modifier = Modifier.Companion.fillMaxWidth(),
                    verticalAlignment = Alignment.Companion.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = title,
                        color = Color.Companion.Black,
                        style = TextStyle(
                            fontWeight = FontWeight.Companion.Bold,
                            fontSize = MaterialTheme.typography.titleLarge.fontSize,
                            textAlign = TextAlign.Companion.Justify
                        )
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Companion.Transparent),
            navigationIcon = {
                if (icon != null) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = Color.Companion.Black,
                        modifier = Modifier.Companion
                            .clickable {
                                //onButtonClicked.invoke() --> alternate way
                                onButtonClicked(null)
                            }
                    )
                }
                if (isMainScreen)
                {
                    val favList = favouriteViewModel.favList.collectAsState().value.filter {
                        (it.city == title.split(",")[0] && it.country == title.split(",")[1])
                    }

                    Icon(
                        imageVector =
                        if(favList.isNotEmpty())
                            Icons.Default.Favorite
                        else
                            Icons.Default.FavoriteBorder,
                        contentDescription = "Favourites Icon",
                        tint = Color.Red.copy(0.6f),
                        modifier = Modifier
                            .padding(start = 15.dp)
                            .scale(1.2f)
                            .clickable{
                                when(favList.isNotEmpty())
                                {
                                    true ->
                                    {
                                        favouriteViewModel.deleteFavourite(
                                            Favourite(
                                                city = title.split(",")[0],
                                                country = title.split(",")[1]
                                            )
                                        )
                                        //toastMessage.value = "Removed from Favourites"
                                        onButtonClicked("Removed from Favourites")
//                                        Toast.makeText(context, "Removed from Favourites", Toast.LENGTH_SHORT).apply {
//                                            show()
//                                        }
                                    }
                                    else ->
                                    {
                                        favouriteViewModel.insertFavourite(
                                            Favourite(
                                                city = title.split(",")[0],
                                                country = title.split(",")[1]
                                            )
                                        )
                                        //toastMessage.value = "Added to Favourites"
                                        onButtonClicked("Added to Favourites")
//                                        Toast.makeText(context, "Added to Favourites", Toast.LENGTH_SHORT).apply{
//                                            show()
//                                        }
                                    }
                                }
                            }
                    )
                }
            },
            actions = {
                if (isMainScreen) {
                    IconButton(
                        onClick =
                        {
                            onAddActionClicked()
                        }
                    )
                    {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search Icon",
                            tint = Color.Companion.Black
                        )
                    }
                    IconButton(
                        onClick = {
                            showDialog.value = true
                        }
                    )
                    {
                        Icon(
                            imageVector = Icons.Rounded.MoreVert,
                            contentDescription = "Search Icon",
                            tint = Color.Companion.Black
                        )
                    }
                } else
                    Box()
                    {}
            },
            modifier = Modifier.Companion.fillMaxWidth()
        )
    }

}


