package com.example.tourguide.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tourguide.ui.viewmodel.PlaceDetailsViewModel

@Composable
fun PlaceDetailsScreen(
    viewModel: PlaceDetailsViewModel = hiltViewModel()
) {
    val placeDetails by viewModel.placeDetails.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        placeDetails?.let {
            Text(text = it.name ?: "")
            Text(text = it.address ?: "")
        }
    }
}
