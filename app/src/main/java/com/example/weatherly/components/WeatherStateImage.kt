package com.example.weatherly.components

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.weatherly.widgets.GlideImageLoader

@Composable
fun WeatherStateImage(imageUrl: String)
{
    GlideImageLoader(
        url = imageUrl,
        modifier = Modifier.Companion.size(80.dp)
    )
}