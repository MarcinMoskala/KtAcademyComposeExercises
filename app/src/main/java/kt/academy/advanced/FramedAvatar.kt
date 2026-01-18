package kt.academy.advanced

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kt.academy.R

@Composable
fun FramedAvatar() {
    Image(
        modifier = Modifier
            .padding(10.dp)
            .border(2.dp, Color.Black)
            .padding(10.dp)
            .clip(CircleShape)
            .size(50.dp),
        contentDescription = null,
        painter = painterResource(id = R.drawable.avatar)
    )
}

@Preview
@Composable
private fun PreviewFramedAvatar() {
    FramedAvatar()
}