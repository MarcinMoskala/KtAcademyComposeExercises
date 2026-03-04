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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.hasScrollAction
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipe
import androidx.compose.ui.unit.dp
import com.marcinmoskala.composeexercises.exercises.advanced.ActualRecompositionCounter
import com.marcinmoskala.composeexercises.exercises.advanced.LocalCompositionCounter
import kotlinx.coroutines.delay
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class BoxScrollbarTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun scrollDoesNotTriggerBoxScrollbarRecomposition() {
        val counter = ActualRecompositionCounter()

        composeTestRule.setContent {
            CompositionLocalProvider(LocalCompositionCounter provides counter) {
                val listState = rememberLazyListState()
                Box(modifier = Modifier.fillMaxSize()) {
                    LazyColumn(
                        state = listState,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(100) { i ->
                            Text(
                                text = "Item $i",
                                modifier = Modifier.padding(16.dp)
                            )
                        }
                    }
                    BoxScrollbar(
                        listState = listState,
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .fillMaxHeight()
                            .width(8.dp)
                    )
                }
            }
        }

        composeTestRule.waitForIdle()
        composeTestRule.onNode(hasScrollAction())
            .performTouchInput {
                val start = center
                swipe(start, start.copy(y = start.y - 100f))
            }
        composeTestRule.waitForIdle()

        val initialNumberOfRecompositions = counter.get("BoxScrollbar") ?: 0

        // First swipe down — covers multiple scroll frames
        composeTestRule.onNode(hasScrollAction())
            .performTouchInput {
                val start = center
                swipe(start, start.copy(y = start.y - 500f))
            }
        composeTestRule.waitForIdle()

        val afterFirstScroll = counter.get("BoxScrollbar") ?: 0
        assertEquals(
            "BoxScrollbar should not recompose while scrolling; reading scroll state in the composable body instead of deferring it to the layout phase would increase this count",
            initialNumberOfRecompositions,
            afterFirstScroll
        )

        // Second swipe — verifies the count stays stable across continued scrolling
        composeTestRule.onNode(hasScrollAction())
            .performTouchInput {
                val start = center
                swipe(start, start.copy(y = start.y - 300f))
            }
        composeTestRule.waitForIdle()

        val afterSecondScroll = counter.get("BoxScrollbar") ?: 0
        assertEquals(
            "BoxScrollbar should not recompose on further scrolling",
            initialNumberOfRecompositions,
            afterSecondScroll
        )
    }
}
