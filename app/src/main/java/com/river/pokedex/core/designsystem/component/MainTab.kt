package com.river.pokedex.core.designsystem.component

import androidx.annotation.DrawableRes
import com.river.pokedex.R
import com.river.pokedex.core.navigation.Route
import kotlin.reflect.KClass

enum class MainTab(
    @DrawableRes override val icon: Int,
    override val contentDescription: String,
    override val route: KClass<out Route>
) : BottomTab {
    ListTab(R.drawable.ic_navbar_list, "LIST", Route.List::class),
    FavoriteTab(R.drawable.ic_navbar_favorite, "FAVORITE", Route.Favorite::class);

    override val routeName: String
        get() = Route.routeMap[route] ?: error("Route not found for $route")
}
