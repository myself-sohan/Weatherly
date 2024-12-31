package com.example.weatherly.screens.splashscreen

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import  com.example.weatherly.R
import com.example.weatherly.navigation.WeatherScreens
import kotlinx.coroutines.delay

@Composable
fun WeatherSplashScreen(navController: NavController)
{
    val scale = remember{
        Animatable(0f)
    }
    LaunchedEffect(
        key1 = true,
        block = {
            scale.animateTo(
                targetValue = 0.9f,


                animationSpec = tween(
                    durationMillis = 1000,
                    delayMillis = 200,
                    easing = {
                        OvershootInterpolator(8f)
                            .getInterpolation(it)
                    }
                )
            )
            delay(2000)
            navController.navigate(WeatherScreens.MainScreen.name)
        }
    )
    Surface (
        modifier = Modifier
            .padding(15.dp)
            .size(330.dp)
            .scale(scale.value)
            .background(Brush.verticalGradient(   listOf(Color(243, 84, 56, 255), Color(
                157,
                168,
                58,
                255
            )
            ))),

        shape = CircleShape,
        border = BorderStroke(
            width = 3.dp,
            color = Color(0xFF334BC4)
        )

    )
    {

        Column(
            modifier = Modifier.padding(1.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        )
        {

            Image(
                painter = painterResource(id = R.drawable.weathers),
                contentDescription = "sunny icon",
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(155.dp)
                )
            Text(
                text = "Find the Sun?",
                style = MaterialTheme.typography.displaySmall,
                color = Color(0xFFD7862E),
                fontFamily = FontFamily.SansSerif
            )
        }
    }
}
