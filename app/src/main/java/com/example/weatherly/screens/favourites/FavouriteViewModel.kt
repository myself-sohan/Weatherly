package com.example.weatherly.screens.favourites

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherly.model.Favourite
import com.example.weatherly.repository.WeatherRepository
import com.example.weatherly.screens.main.MainViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.collections.any

@HiltViewModel
class FavouriteViewModel @Inject constructor(private val repository: WeatherRepository)
    : ViewModel()
{
    private val _favList = MutableStateFlow<List<Favourite>>(emptyList())
    val favList = _favList.asStateFlow()
    init {
        viewModelScope.launch(Dispatchers.IO)
        {
            repository.getFavourites().distinctUntilChanged()
                .collect{ listOfFavs ->
                    if (listOfFavs.isEmpty())
                        Log.d("Fvs", "Empty Favs")
                    else
                    {
                        _favList.value = listOfFavs
                        Log.d("s", "${_favList.value}")
                    }
                }
        }
    }

    fun insertFavourite(favourite: Favourite) = viewModelScope.launch{repository.insertFavourite(favourite)}
    fun deleteAllFavourite() = viewModelScope.launch{repository.deleteALlFavourites()}
    fun updateFavourite(favourite: Favourite) = viewModelScope.launch{repository.updateFavourite(favourite)}
    fun deleteFavourite(favourite: Favourite) = viewModelScope.launch {
        repository.deleteFavourite(favourite)
//        _favList.value = _favList.value.toMutableList().apply {
//            remove(favourite)
//    }
        _favList.value = repository.getFavourites().first()
    }
}