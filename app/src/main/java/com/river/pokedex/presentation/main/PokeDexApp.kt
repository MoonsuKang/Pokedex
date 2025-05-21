package com.river.pokedex.presentation.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import com.river.pokedex.core.designsystem.component.MainTab
import com.river.pokedex.core.designsystem.component.PokeDexBottomBar
import com.river.pokedex.core.designsystem.theme.PokeDexTheme
import com.river.pokedex.core.navigation.Route
import com.river.pokedex.core.navigation.navigateTo

@Composable
fun PokeDexApp(appState: PokeDexAppState) {
    val navBackStackEntry by appState.navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val bottomBarRoutes = listOf(
        Route.List::class,
        Route.Favorite::class,
    )

    val isBottomBarVisible = bottomBarRoutes.any { routeClass ->
        Route.routeMap[routeClass] == currentRoute
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(PokeDexTheme.colors.gray01),
    ) {
        Scaffold(
            modifier = Modifier.systemBarsPadding(),
            bottomBar = {
                if (isBottomBarVisible) {
                    PokeDexBottomBar(
                        bottomTabs = MainTab.entries.toList(),
                        currentTab = MainTab.entries.firstOrNull { it.routeName == currentRoute },
                        onClickTab = { tab ->
                            appState.navController.navigateTo(tab.route)
                        },
                        modifier = Modifier.fillMaxWidth(),
                    )
                }
            },
        ) { padding ->
            PokeDexNavHost(
                appState = appState,
                modifier = Modifier.padding(padding),
            )
        }
    }
}
