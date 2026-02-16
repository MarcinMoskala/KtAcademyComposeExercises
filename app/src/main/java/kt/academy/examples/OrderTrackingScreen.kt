package kt.academy.examples

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@Composable
fun OrderTrackingScreen(tracker: OrderTracker) {
    val transition = rememberInfiniteTransition()
    val truckOffset by transition.animateFloat(
        initialValue = 0f,
        targetValue = 280f,
        animationSpec = infiniteRepeatable(
            animation = tween(3000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )
    val status = tracker.getDetailedStatus()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Order #38291",
            style = MaterialTheme.typography.headlineSmall
        )
        Text(
            text = "Estimated delivery: Tomorrow, 2–5 PM",
            style = MaterialTheme.typography.bodyMedium
        )
        Icon(
            imageVector = Icons.Default.LocalShipping,
            contentDescription = "Delivery in progress",
            modifier = Modifier
                .size(48.dp)
                .offset(x = truckOffset.dp)
        )
        Text(
            text = status,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

interface OrderTracker {
    fun getDetailedStatus(): String
    val detailedStatus: StateFlow<String>
}

@Preview(showBackground = true)
@Composable
private fun OrderTrackingScreenPreview() {
    val tracker = remember {
        object : OrderTracker {
            val scope = CoroutineScope(SupervisorJob())
            init {
                scope.launch {
                    delay(2000)
                    detailedStatus.value = "Package is on its way to the distribution center"
                    delay(2000)
                    detailedStatus.value = "Package is on its way to your location"
                    delay(2000)
                    detailedStatus.value = "Package has been delivered"
                }
            }
            override val detailedStatus = MutableStateFlow("Package is on its way to the distribution center")
            override fun getDetailedStatus(): String {
                Thread.sleep(100)
                return detailedStatus.value
            }
        }
    }
    OrderTrackingScreen(tracker)
}
