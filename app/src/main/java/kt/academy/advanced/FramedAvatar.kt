package kt.academy.advanced

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import kt.academy.Avatar

@Composable
fun FramedAvatar() {
    Icon(
        imageVector = Avatar,
        modifier = Modifier,
        contentDescription = "Avatar",
        tint = Color.Unspecified
    )
}

@Preview
@Composable
private fun PreviewFramedAvatar() {
    FramedAvatar()
}