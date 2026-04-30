package com.example.tourguide.di

import android.content.Context
import com.google.android.gms.location.LocationServices
import com.google.android.libraries.places.api.Places
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideFusedLocationProviderClient(@ApplicationContext context: Context) = 
        LocationServices.getFusedLocationProviderClient(context)

    @Provides
    fun providePlacesClient(@ApplicationContext context: Context) = 
        Places.createClient(context)
}
