package org.ucb.appp1.earthquake.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.ucb.appp1.earthquake.domain.usecase.GetEarthquakesUseCase

class EarthquakeViewModel(
    private val getEarthquakesUseCase: GetEarthquakesUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(EarthquakeState())
    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<EarthquakeEffect>()
    val effect = _effect.asSharedFlow()

    init {
        fetchEarthquakes()
    }

    fun emitEvent(event: EarthquakeEvent) {
        when (event) {
            EarthquakeEvent.OnFetch -> fetchEarthquakes()
            is EarthquakeEvent.OnMinMagnitudeChange -> {
                _state.update { it.copy(minMagnitude = event.value) }
            }
            is EarthquakeEvent.OnLimitChange -> {
                _state.update { it.copy(limit = event.value) }
            }
        }
    }

    private fun fetchEarthquakes() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }
            val minMag = _state.value.minMagnitude.toDoubleOrNull() ?: 5.0
            val lim = _state.value.limit.toIntOrNull() ?: 3

            getEarthquakesUseCase.invoke(minMag, lim).fold(
                onSuccess = { list ->
                    _state.update {
                        it.copy(isLoading = false, earthquakes = list)
                    }
                },
                onFailure = { err ->
                    _state.update {
                        it.copy(isLoading = false, error = err.message ?: "Error desconocido")
                    }
                }
            )
        }
    }
}
