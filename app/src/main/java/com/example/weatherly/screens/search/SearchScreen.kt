package com.example.weatherly.screens.search


import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.weatherly.components.CommonTextField
import com.example.weatherly.components.WeatherAppBar
import com.example.weatherly.navigation.WeatherScreens

@Composable
fun SearchScreen (
    navController: NavController,
    isValid: Boolean
)
{
    val notValidCity = remember{
        mutableStateOf(isValid)
    }
    val context = LocalContext.current
    if(notValidCity.value)
    {
        Toast.makeText(context, "Enter Valid City!", Toast.LENGTH_SHORT).show()
        notValidCity.value = false
    }
    Scaffold(
        topBar ={
            WeatherAppBar(
                title = "Search",
                navController = navController,
                icon = Icons.AutoMirrored.Default.ArrowBack,
                isMainScreen = false
            )
            {
                navController.popBackStack()
            }
        }
    )
    {
        Surface(modifier = Modifier.padding(it))
        {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {
                SearchBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .align(Alignment.CenterHorizontally)
                )
                {
                    navController.navigate(WeatherScreens.MainScreen.name+"/$it")
                }
            }
        }
    }
}

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    onSearch: (String) -> Unit = {}
)
{
    Column()
    {
        val searchQueryState = rememberSaveable()
        {
            mutableStateOf("")
        }
        val keyboardController = LocalSoftwareKeyboardController.current
        val valid = remember(searchQueryState.value)
        {
            searchQueryState.value.trim().isNotEmpty()
        }
        CommonTextField(
            valueState = searchQueryState,
            placeholder = "Enter City",
            isSearch = true,
            onAction = KeyboardActions {
                if (!valid)
                    return@KeyboardActions
                onSearch(searchQueryState.value.trim())
                searchQueryState.value = ""
                keyboardController?.hide()
            }
        )
    }
}

