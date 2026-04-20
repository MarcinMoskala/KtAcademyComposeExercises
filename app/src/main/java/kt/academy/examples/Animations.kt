package kt.academy.examples

import android.R.attr.description
import android.R.attr.label
import androidx.compose.animation.core.AnimationVector2D
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.TwoWayConverter
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.animateIntOffsetAsState
import androidx.compose.animation.core.animateValue
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kt.academy.ComposeLogoIcon
import kt.academy.TruckIcon
import kotlin.math.roundToInt

@Preview
@Composable
private fun AnimatedNumber() {
    var number by remember { mutableIntStateOf(0) }
    val animatedNumber by animateIntAsState(
        targetValue = number,
        animationSpec = tween(
            durationMillis = 1000,
            easing = LinearEasing,
        )
    )

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = animatedNumber.toString(),
            fontSize = 30.sp,
            modifier = Modifier.padding(32.dp)
        )
        Button(onClick = {
            number += 1000
        }) {
            Text("Increase number")
        }
    }
}

@Preview
@Composable
fun AnimatedTrack() {
    var start by remember { mutableStateOf(true) }
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        AnimatedTrackWithDescription(
            start = start,
            description = "LinearEasing",
            easing = LinearEasing,
        )
        AnimatedTrackWithDescription(
            start = start,
            description = "FastOutSlowInEasing",
            easing = FastOutSlowInEasing,
        )
        AnimatedTrackWithDescription(
            start = start,
            description = "FastOutLinearInEasing",
            easing = FastOutLinearInEasing,
        )
        AnimatedTrackWithDescription(
            start = start,
            description = "LinearOutSlowInEasing",
            easing = LinearOutSlowInEasing,
        )
        AnimatedTrackWithDescription(
            start = start,
            description = "Quadratic easing",
            easing = Easing { it * it },
        )

        Button(onClick = {
            start = !start
        }) {
            Text("Move track")
        }
    }
}

@Composable
fun AnimatedTrackWithDescription(
    start: Boolean,
    description: String,
    easing: Easing,
    modifier: Modifier = Modifier,
) {
    BoxWithConstraints(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        val truckOffset by animateFloatAsState(
            targetValue = if (start) 0f else maxWidth.value - 48.dp.value,
            animationSpec = tween(
                durationMillis = 1000,
                easing = easing,
            )
        )
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = description,
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )
            Icon(
                imageVector = TruckIcon,
                contentDescription = "Delivery track $description",
                modifier = Modifier
                    .size(48.dp)
                    .offset(x = truckOffset.dp)
            )
        }
    }
}

@Preview
@Composable
fun ComposeMovingAround() {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        val iconSize = 60.dp
        val top = 0.dp
        val left = 0.dp
        val bottom = maxHeight - iconSize
        val right = maxWidth - iconSize

        data class Position(val x: Dp, val y: Dp)

        val position by rememberInfiniteTransition().animateValue(
            initialValue = Position(0.dp, 0.dp),
            targetValue = Position(0.dp, 0.dp),
            animationSpec = infiniteRepeatable(
            keyframes {
                durationMillis = 4000
                Position(right, top) atFraction 0.25f using LinearOutSlowInEasing
                Position(right, bottom) atFraction 0.5f using FastOutLinearInEasing
                Position(left, bottom) atFraction 0.75f
            },
                repeatMode = RepeatMode.Restart
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

@Preview
@Composable
fun ComposableFollowingClick() {
    val iconSize = 60.dp
    var targetPosition by remember { mutableStateOf(IntOffset(0, 0)) }
    val offset1 by animateIntOffsetAsState(
        targetValue = targetPosition,
        animationSpec = tween(1000)
    )
    val offset2 by animateIntOffsetAsState(
        targetValue = targetPosition,
        animationSpec = spring(stiffness = 30f)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures { tapOffset ->
                    targetPosition = IntOffset(
                        (tapOffset.x - iconSize.toPx() / 2).roundToInt(),
                        (tapOffset.y - iconSize.toPx() / 2).roundToInt()
                    )
                }
            }
    ) {
        Icon(
            imageVector = ComposeLogoIcon,
            contentDescription = "Compose Logo",
            tint = Color.Blue,
            modifier = Modifier
                .size(iconSize)
                .offset { offset1 }
        )
        Icon(
            imageVector = ComposeLogoIcon,
            contentDescription = "Compose Logo",
            tint = Color.Green,
            modifier = Modifier
                .size(iconSize)
                .offset { offset2 }
        )
    }
}

