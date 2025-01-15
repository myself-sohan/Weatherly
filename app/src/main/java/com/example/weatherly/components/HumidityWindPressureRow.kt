package com.example.weatherly.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.weatherly.R
import com.example.weatherly.model.freeweatherlatestmodel.WeatherObject

@Composable
fun HumidityWindPressureRow(
    weather: WeatherObject,
    isImperial: Boolean
)
{
    Row(
        modifier = Modifier.Companion
            .padding(12.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.Companion.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    )
    {
        Row(
            modifier = Modifier.Companion
                .padding(4.dp)
        )
        {
            Image(
                painter = painterResource(id = R.drawable.humidity),
                contentDescription = "Humidity Icon",
                modifier = Modifier.Companion.size(20.dp)
            )
            Text(
                text = "${weather.main.humidity}%",
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Row(
            modifier = Modifier.Companion
                .padding(4.dp)
        )
        {
            Image(
                painter = painterResource(id = R.drawable.pressure),
                contentDescription = "Pressure Icon",
                modifier = Modifier.Companion.size(20.dp)
            )
            Text(
                text = "${weather.main.pressure} psi",
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Row(
            modifier = Modifier.Companion
                .padding(4.dp)
        )
        {
            Image(
                painter = painterResource(id = R.drawable.wind),
                contentDescription = "Wind Icon",
                modifier = Modifier.Companion.size(20.dp)
            )
            Text(
                text = "${weather.wind.speed} ${if (isImperial) "mph" else "m/s"}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}