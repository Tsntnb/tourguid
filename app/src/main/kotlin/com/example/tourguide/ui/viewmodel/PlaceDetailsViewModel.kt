package com.example.tourguide.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.FetchPlaceRequest
import com.google.android.libraries.places.api.net.PlacesClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlaceDetailsViewModel @Inject constructor(
    private val placesClient: PlacesClient,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _placeDetails = MutableStateFlow<Place?>(null)
    val placeDetails = _placeDetails.asStateFlow()

    private val placeId: String = savedStateHandle.get<String>("placeId")!!

    init {
        fetchPlaceDetails()
    }

    private fun fetchPlaceDetails() {
        val placeFields = listOf(Place.Field.ID, Place.Field.NAME, Place.Field.ADDRESS, Place.Field.PHOTO_METADATAS)
        val request = FetchPlaceRequest.newInstance(placeId, placeFields)

        placesClient.fetchPlace(request).addOnSuccessListener {
            _placeDetails.value = it.place
        }
    }
}
