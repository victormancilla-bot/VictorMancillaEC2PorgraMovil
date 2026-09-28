package org.ucb.appp1.earthquake.presentation.viewmodel

import org.ucb.appp1.earthquake.domain.model.EarthquakeModel

data class EarthquakeState(
    val isLoading: Boolean = false,
    val earthquakes: List<EarthquakeModel> = emptyList(),
    val error: String? = null,
    val minMagnitude: String = "5",
    val limit: String = "3"
)
