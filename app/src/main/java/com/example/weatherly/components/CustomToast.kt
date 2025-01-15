package com.example.weatherly.components

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import com.example.weatherly.R

@Composable
fun CustomToast(
    message: String,
    duration: Long = 2000L, // Default duration in milliseconds
    onDismiss: () -> Unit
) {
    val context = LocalContext.current
    // Animation to show and hide the toast
    AnimatedVisibility(
        visible = true,
        enter = fadeIn(),
        exit = fadeOut(),
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp)
                .wrapContentSize(Alignment.Center),
        ) {
            Card(
                modifier = Modifier
                    .padding(2.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(231, 236, 156, 247), // Solid color
                ),
                elevation = CardDefaults.cardElevation(18.dp), // Optional for shadow effect
                shape = RoundedCornerShape(10.dp)
            ) {
                Row(
                    modifier = Modifier
                        .padding(5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                )
                {
                    Surface(
                        modifier = Modifier
                            .padding(5.dp)
                            .size(30.dp),
                        shape = RoundedCornerShape(5.dp)
                    )
                    {
                        Image(
                            painter = painterResource(R.mipmap.my_launcher_foreground),
                            contentDescription = "Toast Icon",
                            contentScale = ContentScale.Fit
                        )
                    }
                    Text(
                        text = message,
                        modifier = Modifier
                            .padding(0.dp),
                        style = MaterialTheme.typography.bodyMedium,
                        fontStyle = FontStyle.Italic
                    )
                }
            }
        }
    }

    // Automatically dismiss after duration
    LaunchedEffect(Unit) {
        delay(duration)
        onDismiss()
    }
}