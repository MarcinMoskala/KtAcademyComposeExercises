package kt.academy.advanced

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

private val logoColor = Color(0xFFB500DD)

@Composable
fun KotlinLogo() {
    Box(
        modifier = Modifier.size(200.dp)
    )
}

@Preview
@Composable
private fun KotlinLogoPreview() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        KotlinLogo()
    }
}