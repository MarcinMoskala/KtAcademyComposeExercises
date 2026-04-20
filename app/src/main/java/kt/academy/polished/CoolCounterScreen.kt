package kt.academy.polished

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
private fun CoolCounterScreen() {
    var number by remember { mutableIntStateOf(0) }
    val offset = 200
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        AnimatedContent(
            targetState = number,
            transitionSpec = {
                if (targetState > initialState) {
                    slideIn(initialOffset = { IntOffset(0, -offset) }) togetherWith
                            slideOut(targetOffset = { IntOffset(0, offset) })
//                    slideIn(initialOffset = { IntOffset(offset, 0) }) + scaleIn() togetherWith
//                            slideOut(targetOffset = { IntOffset(-offset, 0) }) + scaleOut()
                } else {
                    slideIn(initialOffset = { IntOffset(0, offset) }) togetherWith
                            slideOut(targetOffset = { IntOffset(0, -offset) })
//                    slideIn(initialOffset = { IntOffset(-offset, 0) }) + scaleIn() togetherWith
//                            slideOut(targetOffset = { IntOffset(offset, 0) }) + scaleOut()
                }
            },
            modifier = Modifier.padding(16.dp)
                .fillMaxWidth()
        ) { num ->
            Text(
                text = "$num",
                fontSize = 50.sp,
                fontFamily = FontFamily.Monospace,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
        Button(
            onClick = { number++ }
        ) {
            Text("Increment")
        }
        Button(
            onClick = { number-- }
        ) {
            Text("Decrement")
        }
    }
}