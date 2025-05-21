package com.river.pokedex.core.designsystem.component

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.river.pokedex.core.designsystem.theme.PokeDexTheme

@Composable
fun PokemonAbility(
    modifier: Modifier = Modifier,
    hp: Int,
    attack: Int,
    defense: Int,
    spAttack: Int,
    spDefense: Int,
    speed: Int,
) {
    val maxStat = 150f

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Base Stats",
            style = PokeDexTheme.typography.body1SemiBold,
            color = PokeDexTheme.colors.white,
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            StatCircularProgress(
                modifier = Modifier.weight(1f),
                percentage = hp / maxStat,
                number = hp,
                label = "HP",
                color = Color.Red,
            )
            StatCircularProgress(
                modifier = Modifier.weight(1f),
                percentage = attack / maxStat,
                number = attack,
                label = "Attack",
                color = Color.Yellow,
            )
            StatCircularProgress(
                modifier = Modifier.weight(1f),
                percentage = defense / maxStat,
                number = defense,
                label = "Defense",
                color = Color.Cyan,
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            StatCircularProgress(
                modifier = Modifier.weight(1f),
                percentage = spAttack / maxStat,
                number = spAttack,
                label = "SpAttack",
                color = Color.Magenta,
            )
            StatCircularProgress(
                modifier = Modifier.weight(1f),
                percentage = spDefense / maxStat,
                number = spDefense,
                label = "SpDefense",
                color = Color.Green,
            )
            StatCircularProgress(
                modifier = Modifier.weight(1f),
                percentage = speed / maxStat,
                number = speed,
                label = "Speed",
                color = Color.Blue,
            )
        }
    }
}

@Composable
private fun StatCircularProgress(
    percentage: Float,
    number: Int,
    label: String,
    color: Color,
    modifier: Modifier = Modifier,
) {
    val animatedPercentage by animateFloatAsState(
        targetValue = percentage,
        animationSpec = tween(
            durationMillis = 800,
            delayMillis = 100,
            easing = FastOutSlowInEasing,
        ),
        label = "StatProgressAnimation",
    )

    BoxWithConstraints(
        modifier = modifier
            .aspectRatio(1f)
            .padding(4.dp),
        contentAlignment = Alignment.Center,
    ) {
        val size = maxWidth * 0.8f

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize(),
        ) {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.size(size)) {
                Canvas(modifier = Modifier.size(size)) {
                    drawArc(
                        color = color.copy(alpha = 0.3f),
                        startAngle = -90f,
                        sweepAngle = 360f,
                        useCenter = false,
                        style = Stroke(width = 8f, cap = StrokeCap.Round),
                    )
                    drawArc(
                        color = color,
                        startAngle = -90f,
                        sweepAngle = 360 * animatedPercentage,
                        useCenter = false,
                        style = Stroke(width = 8f, cap = StrokeCap.Round),
                    )
                }
                Text(
                    text = number.toString(),
                    style = PokeDexTheme.typography.body1SemiBold,
                    color = color,
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = label,
                style = PokeDexTheme.typography.body3SemiBold,
                color = Color.White,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PokemonAbilityPreview() {
    PokeDexTheme {
        PokemonAbility(
            modifier = Modifier.fillMaxWidth(),
            hp = 100,
            attack = 120,
            defense = 80,
            spAttack = 90,
            spDefense = 70,
            speed = 110,
        )
    }
}
