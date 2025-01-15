package com.example.weatherly.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.weatherly.model.Favourite
import com.example.weatherly.navigation.WeatherScreens

@Composable
fun ShowSettingDropDownMenu(
    showDialog: MutableState<Boolean>,
    navController: NavController,
)
{
    var expanded = remember {
        mutableStateOf(true)
    }
    val items = listOf( "About", "Favourites","Settings")
    Column(
        modifier = Modifier.Companion
            .fillMaxWidth()
            .wrapContentSize(Alignment.Companion.TopEnd)
            .absolutePadding(top = 4.dp, right = 20.dp)
    )
    {
        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = {
                expanded.value = false
                showDialog.value = false
            },
            modifier = Modifier.Companion
                .width(140.dp)
                .background(Color.Companion.White)
        )
        {
            items.forEachIndexed { index, text ->
                DropdownMenuItem(
                    text = {
                        Row(
                            modifier = Modifier.Companion
                                .fillMaxWidth()
                                .clickable {
                                    navController.navigate(
                                        when (text) {
                                            "About" -> WeatherScreens.AboutScreen.name
                                            "Favourites" -> WeatherScreens.FavouritesScreen.name
                                            else -> WeatherScreens.SettingsScreen.name
                                        }
                                    )
                                },
                        )
                        {
                            Text(
                                text = text,
                                fontWeight = FontWeight.Companion.W500
                            )
                        }
                    },
                    leadingIcon = {
                        if (index == 0)
                            Icon(
                                imageVector = Icons.Default.Info,
                                contentDescription = "Info Icon",
                                tint = Color.Companion.LightGray
                            )
                        else if (index == 1) {
                            Icon(
                                imageVector = Icons.Default.FavoriteBorder,
                                contentDescription = "Favourites Icon",
                                tint = Color.Companion.LightGray
                            )
                        } else {
                            Icon(
                                imageVector = Icons.Default.Settings,
                                contentDescription = "Settings Icon",
                                tint = Color.Companion.LightGray
                            )
                        }
                    },
                    onClick = {
                        expanded.value = false
                        showDialog.value = false
                    }
                )
            }
        }
    }
}