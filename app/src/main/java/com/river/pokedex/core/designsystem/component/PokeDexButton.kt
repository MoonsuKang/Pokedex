package com.river.pokedex.core.designsystem.component

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.river.pokedex.core.designsystem.theme.PokeDexTheme

@Composable
fun PokeDexButton(
    label: String,
    modifier: Modifier = Modifier,
    icon: ImageVector,
    containerColor: Color = PokeDexTheme.colors.mainGreen,
    contentColor: Color = PokeDexTheme.colors.black,
    pressedContainerColor: Color = PokeDexTheme.colors.mainGreen.copy(alpha = 0.8f),
    pressedContentColor: Color = PokeDexTheme.colors.gray07,
    onClick: () -> Unit,
    shape: Shape = RoundedCornerShape(16.dp)
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val currentContainerColor = if (isPressed) pressedContainerColor else containerColor
    val currentContentColor = if (isPressed) pressedContentColor else contentColor

    Button(
        onClick = onClick,
        shape = shape,
        colors = ButtonDefaults.buttonColors(
            containerColor = currentContainerColor,
            contentColor = currentContentColor
        ),
        interactionSource = interactionSource,
        modifier = modifier.fillMaxWidth()
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = currentContentColor
        )
        Text(
            text = label,
            color = currentContentColor,
            style = PokeDexTheme.typography.head3
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PokeDexButtonPreview() {
    PokeDexTheme {
        PokeDexButton(
            label = "버튼",
            icon = Icons.Default.Add,
            onClick = {},
            modifier = Modifier.fillMaxWidth()
        )
    }
}
