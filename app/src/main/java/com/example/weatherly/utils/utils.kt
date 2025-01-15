package com.example.weatherly.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.example.weatherly.model.freeweatherlatestmodel.Weather
import com.example.weatherly.model.freeweatherlatestmodel.WeatherObject
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale
import java.util.Calendar

fun formatDate(timestamp: Int): String {
    val sdf = SimpleDateFormat("EEE, MMM d")
    val date = java.util.Date(timestamp.toLong() * 1000)

    return sdf.format(date)
}

fun formatDateTime(timestamp: Int): String {
    val sdf = SimpleDateFormat("hh:mm:aa")
    val date = java.util.Date(timestamp.toLong() * 1000)

    return sdf.format(date)
}

fun formatDecimals(item: Double): String {
    return " %.0f".format(item)
}
fun filterForecastByDay(forecasts: List<WeatherObject>): List<WeatherObject> {
    // Safely get today's date or return an empty list if no forecasts exist
    val todayDate = forecasts.firstOrNull()?.dt_txt?.split(" ")?.getOrNull(0) ?: return emptyList()

    return forecasts
        .filter { forecast -> forecast.dt_txt.split(" ")[0] >= todayDate } // Filter from today onward
        .groupBy { forecast -> forecast.dt_txt.split(" ")[0] }            // Group by date
        .map { (_, dailyForecasts) -> dailyForecasts.first() }            // Take the first entry for each day
}

fun getFormattedDate(dtTxt: String): String {
    // Parse the string into a LocalDateTime object
    val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    val dateTime = LocalDateTime.parse(dtTxt, inputFormatter)

    // Format the date into the desired output
    val outputFormatter = DateTimeFormatter.ofPattern("EEE, MMM dd", Locale.getDefault())
    return dateTime.format(outputFormatter)
}
fun filterClosestPreviousForecast(dataList: List<WeatherObject>): List<WeatherObject> {
    val currentTime = System.currentTimeMillis() // Current time in milliseconds
    val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())

    // Parse and sort the forecasts by timestamp
    val sortedForecasts = dataList.mapNotNull { forecast ->
        val forecastTime = dateFormat.parse(forecast.dt_txt)?.time
        forecastTime?.let { Pair(forecast, it) }
    }

    // Find the closest previous timestamp
    val closestForecastIndex = sortedForecasts.indexOfLast { (_, time) ->
        time <= currentTime // Latest timestamp less than or equal to current time
    }

    // Return a sublist starting from the closest previous timestamp to the end
    return if (closestForecastIndex != -1) {
        sortedForecasts.drop(closestForecastIndex).map { it.first }
    } else {
        emptyList() // No valid previous timestamp
    }
}
fun getCurrentFormattedTime(): String {
    val currentTime = System.currentTimeMillis()
    // Format for the current time (e.g., 9:05 PM or 12:02 AM)
    val timeFormatter = SimpleDateFormat("h:mm a", Locale.getDefault())
    return timeFormatter.format(currentTime)
}


