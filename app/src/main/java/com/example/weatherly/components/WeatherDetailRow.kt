package com.example.weatherly.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.example.weatherly.model.freeweatherlatestmodel.WeatherObject
import com.example.weatherly.utils.formatDate
import com.example.weatherly.utils.formatDecimals
import com.example.weatherly.utils.getFormattedDate
import com.example.weatherly.R

@Composable
fun WeatherDetailRow(weather: WeatherObject) {
    val imageUrl="https://openweathermap.org/img/wn/${weather.weather[0].icon}.png"
    Surface(
        modifier = Modifier.Companion
            .padding(3.dp)
            .fillMaxWidth(),
        shape = CircleShape.copy(topEnd = CornerSize(15)),
        color = Color(165, 235, 245, 255)
    )
    {
        Row(
            modifier = Modifier.Companion
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.Companion.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        )
        {
            Text(
                text = getFormattedDate(weather.dt_txt)
                    .split(",")[0],
                modifier = Modifier.Companion.padding(start = 5.dp)
            )
            WeatherStateImage(
                imageUrl = imageUrl
            )
            Surface(
                modifier = Modifier.Companion
                    .padding(0.dp),
                shape = CircleShape,
                color = Color(0xFFFFC400)
            )
            {
                Text(
                    text = weather.weather[0].description,
                    modifier = Modifier.Companion.padding(4.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Column(
                    modifier = Modifier.fillMaxHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.high_temperature),
                        contentDescription = "temp_max",
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    color = Color.Companion.Blue.copy(0.9f),
                                    fontWeight = FontWeight.Companion.SemiBold
                                )
                            )
                            {
                                append(formatDecimals(weather.main.temp_max) + "°")
                            }
                        }
                    )
                }

                Column {
                    Image(
                        painter = painterResource(id = R.drawable.low_temperature),
                        contentDescription = "temp_min",
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    color = Color.DarkGray
                                )
                            )
                            {
                                append(formatDecimals(weather.main.temp_min) + "°")
                            }
                        }
                    )
                }
            }
        }
    }
}