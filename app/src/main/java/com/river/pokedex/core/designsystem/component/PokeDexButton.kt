package com.river.pokedex.core.designsystem.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
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
    contentColor: Color = PokeDexTheme.colors.white,
    pressedContentColor: Color = PokeDexTheme.colors.gray07,
    borderColor: Color = PokeDexTheme.colors.white,
    pressedBorderColor: Color = PokeDexTheme.colors.gray07,
    onClick: () -> Unit,
    shape: Shape = RoundedCornerShape(16.dp),
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val currentContentColor = if (isPressed) pressedContentColor else contentColor
    val currentBorderColor = if (isPressed) pressedBorderColor else borderColor

    Button(
        onClick = onClick,
        shape = shape,
        interactionSource = interactionSource,
        modifier = modifier
            .fillMaxWidth()
            .border(BorderStroke(2.dp, currentBorderColor), shape),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = currentContentColor,
        ),
        elevation = ButtonDefaults.buttonElevation(0.dp),
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = currentContentColor,
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = label,
            color = currentContentColor,
            style = PokeDexTheme.typography.head3,
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
private fun PokeDexButtonPreview() {
    PokeDexTheme {
        PokeDexButton(
            label = "버튼",
            icon = Icons.Default.Add,
            onClick = {},
            modifier = Modifier.fillMaxWidth(),
        )
    }
}
