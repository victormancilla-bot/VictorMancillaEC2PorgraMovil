package org.ucb.appp1.earthquake.presentation.viewmodel

sealed interface EarthquakeEffect {
    data class ShowToast(val message: String) : EarthquakeEffect
}
