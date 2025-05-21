package com.river.pokedex.core.navigation

import android.net.Uri
import kotlin.reflect.KClass

sealed interface Route {
    val rawRoute: String
    val route: String
        get() = rawRoute

    companion object {
        val routeMap = mapOf<KClass<out Route>, String>(
            List::class to List.rawRoute,
            Favorite::class to Favorite.rawRoute,
            Detail::class to Detail("").rawRoute
        )
    }

    data object List : Route {
        override val rawRoute = "list"
    }

    data object Favorite : Route {
        override val rawRoute = "favorite"
    }

    data class Detail(val pokemonName: String) : Route {
        override val rawRoute = "detail/{$POKEMON_NAME_KEY}"

        override val route: String
            get() = "detail/${Uri.encode(pokemonName)}"

        companion object {
            const val POKEMON_NAME_KEY = "pokemonName"
        }
    }
}
