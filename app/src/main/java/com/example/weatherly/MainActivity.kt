package com.example.weatherly

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.weatherly.navigation.WeatherNavigation
import com.example.weatherly.network.WeatherApi
import com.example.weatherly.ui.theme.WeatherlyTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var weatherApi1: WeatherApi

    @Inject
    lateinit var weatherApi2: WeatherApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent{
            Log.d("WeatherApiInstance", "Instance 1: $weatherApi1")
            Log.d("WeatherApiInstance", "Instance 2: $weatherApi2")
            WeatherApp()
        }
    }
}

@Composable
fun WeatherApp()
{
    WeatherlyTheme {
            Surface(modifier = Modifier
                .fillMaxSize(),
                color = MaterialTheme.colorScheme.background

            )
            {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center

                ) {
                    WeatherNavigation()
                }
            }

        }
    }

