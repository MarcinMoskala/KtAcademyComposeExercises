package kt.academy.polished

import android.R.attr.label
import android.R.attr.maxHeight
import android.R.attr.maxWidth
import androidx.compose.animation.core.AnimationVector2D
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.TwoWayConverter
import androidx.compose.animation.core.animateValue
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kt.academy.ComposeLogoIcon
import kotlin.math.roundToInt

@Preview
@Composable
private fun RunningCompose() {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        val iconSize = 60.dp
        val top = 0.dp
        val left = 0.dp
        val centerX = maxWidth / 2 - iconSize / 2
        val centerY = maxHeight / 2 - iconSize / 2
        val bottom = maxHeight - iconSize
        val right = maxWidth - iconSize

        data class Position(val x: Dp, val y: Dp)

        val position by rememberInfiniteTransition().animateValue<Position, _>(
            initialValue = TODO(),
            targetValue = TODO(),
            animationSpec = infiniteRepeatable(
                animation = TODO(),
                repeatMode = RepeatMode.Reverse
            ),
            label = "",
            typeConverter = TwoWayConverter(
                convertToVector = { AnimationVector2D(it.x.value, it.y.value) },
                convertFromVector = { Position(Dp(it.v1), Dp(it.v2)) }
            )
        )
        Image(
            imageVector = ComposeLogoIcon,
            contentDescription = "Compose Logo",
            modifier = Modifier
                .size(iconSize)
                .offset { IntOffset(position.x.toPx().roundToInt(), position.y.toPx().roundToInt()) }
        )
    }
}