package com.river.pokedex.core.navigation

import android.net.Uri
import androidx.navigation.NavController
import kotlin.reflect.KClass

fun NavController.navigateTo(
    route: KClass<out Route>,
    vararg args: Pair<String, String>,
    popUpToRoute: String? = null,
    inclusive: Boolean = false
) {
    val baseRoute = Route.routeMap[route] ?: error("Route not found for $route")

    val fullRoute = if (args.isNotEmpty()) {
        var routeWithArgs = baseRoute
        args.forEach { (key, value) ->
            routeWithArgs = routeWithArgs.replace("{$key}", Uri.encode(value))
        }
        routeWithArgs
    } else {
        baseRoute
    }

    this.navigate(fullRoute) {
        popUpToRoute?.let { route ->
            popUpTo(route) { this.inclusive = inclusive }
        }
        launchSingleTop = true
        restoreState = true
    }
}

fun NavController.navigateBack() {
    navigateUp()
    if (currentBackStackEntry?.lifecycle?.currentState == androidx.lifecycle.Lifecycle.State.RESUMED) {
        popBackStack()
    }
}
