package com.example.multimodulepractice.common.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder

interface FeatureEntry {

    val featureRoute: String

    val arguments: List<NamedNavArgument>
        get() = emptyList()

    fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavController,
        modifier: Modifier = Modifier
    )
}

typealias Destinations = Map<Class<out FeatureEntry>, @JvmSuppressWildcards FeatureEntry>

inline fun <reified T : FeatureEntry> Destinations.find(): T =
    findOrNull() ?: error("Unable to find '${T::class.java}' destination.")

inline fun <reified T : FeatureEntry> Destinations.findOrNull(): T? =
    this[T::class.java] as? T
