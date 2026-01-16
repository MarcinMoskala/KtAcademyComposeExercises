package kt.academy.advanced

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import kt.academy.R

@Preview
@Composable
fun ImagePlayground() {
    Image(
        modifier = Modifier,
        contentDescription = null,
        painter = painterResource(id = R.drawable.avatar)
    )
}