package com.river.pokedex.core.designsystem.component

import com.river.pokedex.core.navigation.Route
import kotlin.reflect.KClass

interface BottomTab {
    val icon: Int
    val contentDescription: String
    val route: KClass<out Route>
    val routeName: String
}
