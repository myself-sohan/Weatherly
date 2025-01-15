package com.example.weatherly.screens.settings

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherly.model.Unit
import com.example.weatherly.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(private val repository: WeatherRepository)
    : ViewModel()
{
    private val _unitList = MutableStateFlow<List<Unit>>(emptyList())
    val unitList = _unitList.asStateFlow()
    init {
        viewModelScope.launch(Dispatchers.IO)
        {
            repository.getUnits().distinctUntilChanged()
                .collect{ listOfUnits ->
                    if (listOfUnits.isEmpty())
                        Log.d("Units", "Empty Units")
                    else {
                        _unitList.value = listOfUnits
                    }
                }

        }
    }
    fun deleteAllUnits() = viewModelScope.launch{repository.deleteALlUnits()}
    fun insertUnit(unit: Unit) = viewModelScope.launch{repository.insertUnit(unit)}
}