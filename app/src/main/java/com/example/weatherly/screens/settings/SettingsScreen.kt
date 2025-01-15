package com.example.weatherly.screens.settings

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.weatherly.components.WeatherAppBar
import com.example.weatherly.model.Unit

@Composable
fun SettingsScreen(
    navController: NavController,
    settingsViewModel: SettingsViewModel = hiltViewModel()
)
{
    val context = LocalContext.current
    val unitToggleState = remember{
        mutableStateOf(false)
    }
    val measurementUnits = listOf(
        "Metric (C)",
        "Imperial (F)"
    )
    val choiceFromDb = settingsViewModel.unitList.collectAsState().value
    val choiceDef = remember{
        mutableIntStateOf(0)
    }
    val defaultChoice =
        if(choiceFromDb.isEmpty())
            measurementUnits[0]
        else
            choiceFromDb[0].unit
    val choiceState = remember{
        mutableStateOf(defaultChoice)
    }
    Scaffold(
        topBar = {
            WeatherAppBar(
                title = "Settings",
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
        Surface(
            modifier = Modifier
                .padding(it)
                .fillMaxWidth()
                .fillMaxHeight()
        )
        {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {
                Text(
                    text = "Change Units of Measurement",
                    modifier = Modifier
                        .padding(bottom = 15.dp)
                )
                IconToggleButton(
                    checked = !unitToggleState.value,
                    onCheckedChange = {
                        unitToggleState.value = !it
                        if(unitToggleState.value)
                        {
                            choiceState.value = measurementUnits[1]
                        }
                        else
                            choiceState.value = measurementUnits[0]
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .clip(RoundedCornerShape(14.dp))
                        .padding(5.dp)
                        .background(Color.Magenta.copy(0.4f))

                )
                {
                    Text(
                        text = if(unitToggleState.value)
                            "Fahrenheit ºF"
                        else
                            "Celsius ºC"
                    )
                }

                Button(
                    modifier = Modifier
                        .padding(3.dp)
                        .align(CenterHorizontally),
                    onClick = {
                        settingsViewModel.deleteAllUnits()
                        settingsViewModel.insertUnit(Unit(unit = choiceState.value))
                        if(choiceState.value == measurementUnits[0])
                            Toast.makeText(context,"Unit Changed to CELSIUS",Toast.LENGTH_SHORT).show()
                        else
                            Toast.makeText(context,"Unit Changed to FAHRENHEIT",Toast.LENGTH_SHORT).show()
                        navController.popBackStack()

                    },
                    shape = RoundedCornerShape(34.dp),
                    colors = ButtonDefaults.buttonColors(
                        Color(0xFFEFBE42)
                    )
                )
                {
                    Text(
                        text = "Save",
                        modifier = Modifier.padding(4.dp),
                        color = Color.White,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        }
    }
}