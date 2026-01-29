package kt.academy

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview


@Preview
@Composable
fun Playground() {
    Image(
        imageVector = Avatar,
        contentDescription = null,
        modifier = Modifier
            .fillMaxSize()
    )
}