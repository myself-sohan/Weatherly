package com.example.weatherly.screens.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.weatherly.components.CustomToast
import com.example.weatherly.components.HumidityWindPressureRow
import com.example.weatherly.components.SunsetSunriseRow
import com.example.weatherly.components.WeatherAppBar
import com.example.weatherly.components.WeatherDetailRow
import com.example.weatherly.components.WeatherStateImage
import com.example.weatherly.model.freeweatherlatestmodel.Weather
import com.example.weatherly.navigation.WeatherScreens
import com.example.weatherly.screens.settings.SettingsViewModel
import com.example.weatherly.utils.filterClosestPreviousForecast
import com.example.weatherly.utils.filterForecastByDay
import com.example.weatherly.utils.formatDecimals
import com.example.weatherly.utils.getCurrentFormattedTime
import com.example.weatherly.utils.getFormattedDate
import kotlinx.coroutines.delay

@Composable
fun MainScreen(navController: NavController,
               mainViewModel: MainViewModel = hiltViewModel(),
               city: String?,
               settingsViewModel: SettingsViewModel = hiltViewModel()
)
{
//    val weatherData = produceState<DataOrException<Weather,Boolean,Exception>>(
//        initialValue = DataOrException(loading = true))
//    {
//        value = mainViewModel.getWeatherData(city = city.toString())
//    }.value
//                                                                                  BAD PRACTICE FOR SMALL APPS.... Generates REDUNDANCY
                                                                                 // in multiple data calls in big APPS
//    if(weatherData.loading == true)
//        CircularProgressIndicator()
//    else if(weatherData.data != null)
//        MainScaffold(weather = weatherData.data!!,
//            navController = navController)
    // Observe the weather data from ViewModel
    val unitFromDb = settingsViewModel.unitList.collectAsState().value
    val unit = remember { mutableStateOf("metric") }
    val isImperial = remember { mutableStateOf(false) }
    if(unitFromDb.isNotEmpty())
    {
        unit.value = unitFromDb[0].unit.split(" ")[0].lowercase()
        isImperial.value = unit.value == "imperial"
        val weatherData = mainViewModel.weatherData.value
        // Call the function to fetch data (only once per recomposition)
        LaunchedEffect(city  ) {
            mainViewModel.getWeatherData(city.toString(), units = unit.value)
        }
        // Display UI based on state
        when {
            weatherData.loading == true -> CircularProgressIndicator(
                modifier = Modifier.wrapContentSize(Alignment.Center)
            )
            weatherData.data != null -> MainScaffold(
                weather = weatherData.data!!,
                navController = navController,
                isImperial = isImperial.value
            )
            weatherData.e != null ->
            {
                Column(
                    modifier = Modifier
                        .fillMaxHeight(0.2f)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally
                )
                {
                    Text(
                        text = "Error: ${weatherData.e!!.message}",
                        fontWeight = FontWeight.ExtraBold,
                        style = MaterialTheme.typography.titleLarge
                    )
                    CircularProgressIndicator()
                    Text(
                        text = "Going back to Search Screen...",
                        fontWeight = FontWeight.Light,
                        style = MaterialTheme.typography.titleLarge
                    )
                }
                LaunchedEffect (weatherData.e )
                {
                    delay(2000)
                    navController.navigate(WeatherScreens.SearchScreen.name +"/true")
                }
            }
            else -> Text(text = "Something went wrong.")
        }
    }

}
@Composable
fun MainScaffold(weather: Weather,
                 navController: NavController,
                 isImperial: Boolean
)
{
    val toastMessage = remember { mutableStateOf<String?>(null) }
    Scaffold(
        topBar = {
            WeatherAppBar(
                    title = weather.city.name+" , ${weather.city.country}",
                    navController = navController,
                    onAddActionClicked = {
                        navController.navigate(WeatherScreens.SearchScreen.name + "/false")
                    },
                    elevation = 29.dp,
            )
            {
                toastMessage.value = it.toString()
            }
        }
    )
    {

        MainContent(
            modifier = Modifier.padding(it),
            data = weather.copy(list = filterClosestPreviousForecast(weather.list)),
            toastMessage =toastMessage,
            isImperial = isImperial
        )
    }
}
@Composable
fun MainContent(data: Weather,
                modifier: Modifier = Modifier,
                toastMessage: MutableState<String?>,
                isImperial: Boolean
)
{
    val imageUrl="https://openweathermap.org/img/wn/${data.list[0].weather[0].icon}.png"
    Column (
        modifier=modifier
            .padding(4.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    )
    {
        // Show the custom toast if a message is present
        toastMessage.value?.let { message ->
            CustomToast(
                message = message,
                duration = 2000L, // Toast duration
                onDismiss = { toastMessage.value = null }
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        )
        {
            Text(
                text = getFormattedDate(data.list[0].dt_txt),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(6.dp)
            )
            Text(
                text = getCurrentFormattedTime(),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(6.dp)
            )
        }
        Surface(
            modifier= Modifier
                .padding(4.dp)
                .size(200.dp),
            shape = CircleShape,
            color = Color(0xFFFFC400)
        )
        {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {
                //Image
                WeatherStateImage(
                    imageUrl = imageUrl
                )
                Text(
                    text = formatDecimals(data.list[0].main.temp)+"°",
                    style = MaterialTheme.typography.displayMedium,
                    fontWeight = FontWeight.ExtraBold
                )
                Text(
                    text = data.list[0].weather[0].main,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        HumidityWindPressureRow(weather = data.list[0], isImperial = isImperial)
        HorizontalDivider()
        SunsetSunriseRow(weather = data.city)
        Text(
            text = "This Week",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(3.dp),
            color = Color(0xFFEEF1EF),
            shape = RoundedCornerShape(14.dp)
        )
        {
            LazyColumn(
                modifier = Modifier.padding(2.dp),
                contentPadding = PaddingValues(1.dp)

            )
            {
                items(items = filterForecastByDay(data.list))
                {
                    item ->
                    WeatherDetailRow(weather = item)
                }
            }
        }

    }
}

