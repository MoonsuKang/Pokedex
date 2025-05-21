package com.river.pokedex.core.designsystem.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.river.pokedex.R
import com.river.pokedex.core.designsystem.theme.PokeDexTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokeDexTopBarWithBackButton(
    modifier: Modifier = Modifier,
    title: String = "",
    onBackClick: () -> Unit = {},
    windowInsets: WindowInsets = TopAppBarDefaults.windowInsets,
    trailingComponent: @Composable RowScope.() -> Unit = {},
) {
    Column {
        CenterAlignedTopAppBar(
            modifier = modifier
                .fillMaxWidth(),
            title = {
                Text(
                    text = title,
                    style = PokeDexTheme.typography.head3,
                    color = PokeDexTheme.colors.white,
                    textAlign = TextAlign.Center,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            },
            actions = trailingComponent,
            navigationIcon = {
                IconButton(
                    onClick = onBackClick,
                    modifier = Modifier.size(32.dp),
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_back),
                        contentDescription = null,
                        tint = PokeDexTheme.colors.white,
                    )
                }
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = Color.Transparent,
            ),
            windowInsets = windowInsets,
            expandedHeight = 60.dp,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokeDexTopBar(
    modifier: Modifier = Modifier,
    title: String = "",
    windowInsets: WindowInsets = TopAppBarDefaults.windowInsets,
    trailingComponent: @Composable RowScope.() -> Unit = {},
) {
    TopAppBar(
        modifier = modifier.fillMaxWidth(),
        title = {
            Text(
                text = title,
                style = PokeDexTheme.typography.head3,
                color = PokeDexTheme.colors.white,
                textAlign = TextAlign.Start,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        },
        actions = trailingComponent,
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = PokeDexTheme.colors.red.copy(alpha = 0.9f),
        ),
        windowInsets = windowInsets,
        expandedHeight = 60.dp,
    )
}

@Preview(showBackground = true)
@Composable
private fun PokeDexTopBarPreview() {
    PokeDexTheme {
        PokeDexTopBar(
            title = "타이틀",
            modifier = Modifier.padding(16.dp),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PokeDexTopBarWithBackButtonPreview() {
    PokeDexTheme {
        PokeDexTopBarWithBackButton(
            title = "타이틀",
            onBackClick = {},
            modifier = Modifier.padding(16.dp),
        )
    }
}
