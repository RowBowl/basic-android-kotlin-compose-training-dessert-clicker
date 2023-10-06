package com.example.dessertclicker.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.dessertclicker.data.Datasource.dessertList

/**
 * This is the UI State that will be used within the UI to display data. It is interactable via
 * the DessertViewModel
 */
data class DessertUiState(
    val revenue: Int = 0,
    val dessertsSold: Int = 0,
    val currentDessertIndex: Int = 0,
    var currentDessertPrice: Int = dessertList[currentDessertIndex].price,
    var currentDessertImageId: Int = dessertList[currentDessertIndex].imageId
)
