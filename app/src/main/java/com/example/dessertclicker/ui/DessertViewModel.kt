package com.example.dessertclicker.ui

import androidx.lifecycle.ViewModel
import com.example.dessertclicker.data.Datasource.dessertList
import com.example.dessertclicker.model.Dessert
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * This is the ViewModel that will be used in the UI to interact with the uiState
 */
class DessertViewModel: ViewModel() {


    //
    private val _uiState = MutableStateFlow<DessertUiState>(DessertUiState())
    val uiState: StateFlow<DessertUiState> = _uiState.asStateFlow()

    fun onDessertClicked() {
        _uiState.update {uiState ->
            val dessertsSold = uiState.dessertsSold + 1
            val dessertToShow = determineDessertToShow(dessertsSold)
            uiState.copy(
                currentDessertIndex = dessertToShow,
                revenue = uiState.revenue + uiState.currentDessertPrice,
                dessertsSold = dessertsSold,
                currentDessertImageId = dessertList[dessertToShow].imageId,
                currentDessertPrice = dessertList[dessertToShow].price
            )
        }
        /*// Update the revenue
        revenue += currentDessertPrice
        dessertsSold++

        // Show the next dessert
        val dessertToShow = determineDessertToShow(dessertsSold)
        currentDessertImageId = dessertToShow.imageId
        currentDessertPrice = dessertToShow.price
*/
    }

    /**
     * Determine which dessert to show.
     */
    private fun determineDessertToShow(dessertsSold: Int) : Int {
        var dessertToShow = 0
        for (index in dessertList.indices) {
            if (dessertsSold >= dessertList[index].startProductionAmount) {
                dessertToShow = index
            } else {
                // The list of desserts is sorted by startProductionAmount. As you sell more desserts,
                // you'll start producing more expensive desserts as determined by startProductionAmount
                // We know to break as soon as we see a dessert who's "startProductionAmount" is greater
                // than the amount sold.
                break
            }
        }

        return dessertToShow
    }
}