package com.river.pokedex.presentation.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.river.pokedex.presentation.favorite.favoriteNavGraph
import com.river.pokedex.presentation.list.listNavGraph

@Composable
fun PokeDexNavHost(
    appState: PokeDexAppState,
    modifier: Modifier = Modifier,
) {
    val navController = appState.navController

    NavHost(
        navController = navController,
        startDestination = appState.startDestination,
        modifier = modifier.fillMaxSize(),
    ) {
        listNavGraph(navController)
        favoriteNavGraph(navController)
    }
}
