package com.river.pokedex.presentation.detail.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.river.pokedex.R
import com.river.pokedex.core.designsystem.theme.PokeDexTheme

@Composable
fun PokemonInformationBox(
    modifier: Modifier = Modifier,
    firstIconRes: Int,
    firstDescription: String,
    firstCategory: String,
    secondIconRes: Int,
    secondDescription: String,
    secondCategory: String,
) {
    Row(
        modifier = modifier
            .background(PokeDexTheme.colors.gray02, shape = RoundedCornerShape(12.dp))
            .padding(horizontal = 12.dp, vertical = 12.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(32.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(firstIconRes),
                contentDescription = null,
                tint = PokeDexTheme.colors.white,
            )
            Spacer(modifier = Modifier.padding(horizontal = 4.dp))
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = firstDescription,
                    color = PokeDexTheme.colors.blue,
                    style = PokeDexTheme.typography.body1SemiBold,
                )
                Text(
                    text = firstCategory,
                    color = PokeDexTheme.colors.white,
                    style = PokeDexTheme.typography.detail1SemiBold,
                )
            }
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(secondIconRes),
                contentDescription = null,
                tint = PokeDexTheme.colors.white,
            )
            Spacer(modifier = Modifier.padding(horizontal = 4.dp))
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = secondDescription,
                    color = PokeDexTheme.colors.blue,
                    style = PokeDexTheme.typography.body1SemiBold,
                )
                Text(
                    text = secondCategory,
                    color = PokeDexTheme.colors.white,
                    style = PokeDexTheme.typography.detail1SemiBold,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PokemonInformationBoxPreview() {
    PokeDexTheme {
        PokemonInformationBox(
            modifier = Modifier.padding(16.dp),
            firstIconRes = R.drawable.ic_height,
            firstDescription = "1.2m",
            firstCategory = "키",
            secondIconRes = R.drawable.ic_weight,
            secondDescription = "60kg",
            secondCategory = "몸무게",
        )
    }
}
