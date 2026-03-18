import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kt.academy.AvatarIcon

@Preview
@Composable
fun App() {
    Image(
        AvatarIcon,
        contentDescription = null,
        modifier = Modifier
            .padding(20.dp)
            .clip(CircleShape)
            .size(200.dp)
    )
}