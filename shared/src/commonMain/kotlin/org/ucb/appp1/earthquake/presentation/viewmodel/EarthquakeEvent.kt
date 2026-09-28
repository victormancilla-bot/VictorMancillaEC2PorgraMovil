package org.ucb.appp1.earthquake.presentation.viewmodel

sealed interface EarthquakeEvent {
    object OnFetch : EarthquakeEvent
    data class OnMinMagnitudeChange(val value: String) : EarthquakeEvent
    data class OnLimitChange(val value: String) : EarthquakeEvent
}
