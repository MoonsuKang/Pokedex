package com.river.pokedex.core.designsystem.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.SnackbarVisuals
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.river.pokedex.core.designsystem.theme.PokeDexTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun PokeDexSnackBar(
    modifier: Modifier = Modifier,
    message: String,
    label: String,
    onAction: () -> Unit,
    shape: Shape = RoundedCornerShape(6.dp),
    containerColor: Color = PokeDexTheme.colors.black,
) {
    Surface(
        modifier = modifier,
        shape = shape,
        color = containerColor,
        shadowElevation = 6.dp,
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 14.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = message,
                color = PokeDexTheme.colors.white,
                style = PokeDexTheme.typography.detail1SemiBold,
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = label,
                color = PokeDexTheme.colors.gray06,
                style = PokeDexTheme.typography.detail2SemiBold,
                modifier = Modifier.clickable(onClick = onAction),
            )
        }
    }
}

suspend fun showCustomSnackBar(
    scope: CoroutineScope,
    snackBarHostState: SnackbarHostState,
    message: String,
    iconRes: Int? = null,
    durationMillis: Long = 2000L,
    onDismiss: () -> Unit = {},
    onAction: () -> Unit = {},
): SnackbarResult {
    val result = with(scope) {
        launch {
            snackBarHostState.showSnackbar(
                CustomSnackBarVisuals(
                    message = message,
                    iconRes = iconRes,
                ),
            )
        }.let { job ->
            launch {
                delay(durationMillis)
                if (job.isActive) {
                    job.cancel()
                    onDismiss()
                }
            }
            job.join()
            if (job.isCancelled) SnackbarResult.Dismissed else SnackbarResult.ActionPerformed
        }
    }

    when (result) {
        SnackbarResult.Dismissed -> onDismiss()
        SnackbarResult.ActionPerformed -> onAction()
    }

    return result
}

data class CustomSnackBarVisuals(
    override val message: String,
    override val actionLabel: String? = null,
    override val withDismissAction: Boolean = false,
    override val duration: SnackbarDuration = SnackbarDuration.Short,
    val iconRes: Int? = null,
) : SnackbarVisuals

@Preview(showBackground = true)
@Composable
fun PokeDexSnackBarPreview() {
    PokeDexTheme {
        PokeDexSnackBar(
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp, bottom = 12.dp)
                .fillMaxWidth(),
            message = "이미 저장된 포켓몬",
            label = "확인",
            onAction = {},
        )
    }
}
