package com.river.pokedex.presentation.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.river.pokedex.core.navigation.Route

@Stable
class PokeDexAppState(
    val navController: NavHostController,
) {
    val startDestination = Route.List.route
}

@Composable
fun rememberPokeDexAppState(
    navController: NavHostController = rememberNavController(),
): PokeDexAppState = remember { PokeDexAppState(navController) }
