package com.inno.impl.di

import com.inno.impl.ui.fragments.map.MapViewModel
import com.yandex.mapkit.mapview.MapView
import dagger.BindsInstance
import dagger.Component

@MainScope
@Component(
    dependencies = [MapDependencies::class],
    modules = []
)
interface MapComponent {

    fun mapViewModel(): MapViewModel

    @Component.Factory
    interface Factory {
        fun create(
            dependencies: MapDependencies,
            @BindsInstance mapView: MapView
        ): MapComponent
    }
}