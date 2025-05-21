package com.river.pokedex.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.river.pokedex.core.designsystem.theme.PokeDexTheme

@Composable
fun <Tab : BottomTab> PokeDexBottomBar(
    bottomTabs: List<Tab>,
    currentTab: Tab?,
    onClickTab: (tab: Tab) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier.background(PokeDexTheme.colors.gray01)) {
        bottomTabs.forEach { tab ->
            val isSelected = currentTab == tab
            Button(
                onClick = { onClickTab(tab) },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = PokeDexTheme.colors.gray01,
                    contentColor = Color.Transparent,
                    disabledContainerColor = PokeDexTheme.colors.gray01,
                    disabledContentColor = PokeDexTheme.colors.gray01,
                ),
                elevation = null,
            ) {
                PokeDexBottomTab(
                    bottomTab = tab,
                    isSelected = isSelected,
                )
            }
        }
    }
}

@Composable
private fun PokeDexBottomTab(
    isSelected: Boolean,
    bottomTab: BottomTab,
    modifier: Modifier = Modifier,
) {
    val iconColor = if (isSelected) PokeDexTheme.colors.white else PokeDexTheme.colors.gray06
    val textColor = if (isSelected) PokeDexTheme.colors.white else PokeDexTheme.colors.gray06
    val backgroundColor = if (isSelected) PokeDexTheme.colors.red.copy(alpha = 0.7f) else Color.Transparent

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .size(36.dp)
                .background(
                    color = backgroundColor,
                    shape = CircleShape,
                ),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                painter = painterResource(bottomTab.icon),
                contentDescription = null,
                tint = iconColor,
            )
        }
        Text(
            text = bottomTab.contentDescription,
            style = PokeDexTheme.typography.body2SemiBold,
            fontSize = 12.sp,
            color = textColor,
            modifier = Modifier.padding(top = 6.dp),
        )
    }
}
