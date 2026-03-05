package kt.academy.advanced

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.hasScrollAction
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipe
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class OrderTrackingScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun noExcessRecomposition() = runTest {
        val counter = ActualRecompositionCounter()
        val tracker = FakeOrderTracker()

        composeTestRule.setContent {
            CompositionLocalProvider(LocalCompositionCounter provides counter) {
                OrderTrackingScreen(tracker)
            }
        }

        delay(1000)
        assertEquals(1, counter.get("OrderTrackingScreen") ?: 0)
    }
}
