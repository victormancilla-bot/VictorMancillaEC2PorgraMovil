package org.ucb.appp1.earthquake.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.compose.viewmodel.koinViewModel
import org.ucb.appp1.earthquake.domain.model.EarthquakeModel
import org.ucb.appp1.earthquake.presentation.viewmodel.EarthquakeEvent
import org.ucb.appp1.earthquake.presentation.viewmodel.EarthquakeViewModel

@Composable
fun EarthquakeScreen(
    viewModel: EarthquakeViewModel = koinViewModel()
) {
    val state = viewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "USGS Earthquakes",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            TextField(
                value = state.value.minMagnitude,
                onValueChange = { viewModel.emitEvent(EarthquakeEvent.OnMinMagnitudeChange(it)) },
                label = { Text("Min Magnitud") },
                modifier = Modifier.weight(1f)
            )
            TextField(
                value = state.value.limit,
                onValueChange = { viewModel.emitEvent(EarthquakeEvent.OnLimitChange(it)) },
                label = { Text("Límite") },
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = { viewModel.emitEvent(EarthquakeEvent.OnFetch) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Buscar Terremotos")
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (state.value.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        }

        state.value.error?.let { err ->
            Text(
                text = "Error: $err",
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            items(state.value.earthquakes) { earthquake ->
                EarthquakeItem(earthquake = earthquake)
            }
        }
    }
}

@Composable
fun EarthquakeItem(earthquake: EarthquakeModel) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Ubicación: ${earthquake.place}",
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Magnitud: ${earthquake.mag}")
            Text(text = "Fecha y hora (ms): ${earthquake.time}")
            Text(text = "Enlace: ${earthquake.url}")
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Coordenadas:")
            Text(text = " • Longitud: ${earthquake.longitude}")
            Text(text = " • Latitud: ${earthquake.latitude}")
            Text(text = " • Profundidad: ${earthquake.depth}")
        }
    }
}
