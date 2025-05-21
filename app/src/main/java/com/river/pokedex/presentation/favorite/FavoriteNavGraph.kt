package com.river.pokedex.presentation.favorite

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.river.pokedex.core.navigation.Route
import com.river.pokedex.presentation.detail.DetailRoute

fun NavGraphBuilder.favoriteNavGraph(navController: NavHostController) {
    composable(Route.Favorite.route) {
        FavoriteRoute(navController = navController)
    }
    composable(
        route = Route.Detail("").rawRoute,
        arguments = listOf(navArgument(Route.Detail.POKEMON_NAME_KEY) { type = NavType.StringType }),
    ) { backStackEntry ->
        val pokemonName = backStackEntry.arguments?.getString(Route.Detail.POKEMON_NAME_KEY)
            ?: throw IllegalArgumentException("pokemonName is null")

        DetailRoute(
            pokemonName = pokemonName,
            onClickBack = { navController.popBackStack() },
        )
    }
}
