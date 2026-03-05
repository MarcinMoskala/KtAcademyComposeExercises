package kt.academy.advanced

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun BoxScrollbar(
    listState: LazyListState,
    modifier: Modifier = Modifier
) {
    RecompositionCounterEffect("BoxScrollbar")
    val layoutInfo = listState.layoutInfo
    val visibleItems = layoutInfo.visibleItemsInfo
    if (layoutInfo.totalItemsCount == 0 || visibleItems.isEmpty()) {
        return
    }
    val viewportHeightPx = (layoutInfo.viewportEndOffset - layoutInfo.viewportStartOffset).toFloat()
    val visibleHeightPx = visibleItems.sumOf { it.size }.toFloat()
    val avgItemHeightPx = visibleHeightPx / visibleItems.size.toFloat()
    val totalHeightPx = avgItemHeightPx * layoutInfo.totalItemsCount.toFloat()
    if (totalHeightPx <= viewportHeightPx) {
        return
    }
    val scrollOffsetPx =
        listState.firstVisibleItemIndex * avgItemHeightPx + listState.firstVisibleItemScrollOffset
    val maxScrollPx = (totalHeightPx - viewportHeightPx).coerceAtLeast(1f)
    val scrollFraction = (scrollOffsetPx / maxScrollPx).coerceIn(0f, 1f)
    val density = LocalDensity.current
    val thumbHeightPx = (viewportHeightPx * (viewportHeightPx / totalHeightPx))
        .coerceAtLeast(with(density) { 24.dp.toPx() })
    val maxOffsetPx = (viewportHeightPx - thumbHeightPx).coerceAtLeast(0f)
    val thumbOffsetPx = maxOffsetPx * scrollFraction
    val trackColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f)
    val thumbColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.45f)
    val thumbHeight = with(density) { thumbHeightPx.toDp() }
    val thumbOffset = with(density) { thumbOffsetPx.toDp() }

    Box(
        modifier = modifier
            .background(trackColor, RoundedCornerShape(2.dp))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(thumbHeight)
                .offset(y = thumbOffset)
                .background(thumbColor, RoundedCornerShape(2.dp))
        )
    }
}

@Preview
@Composable
private fun BoxScrollbarPreview() {
    val listState = rememberLazyListState()
    CountAndDisplayRecompositions {
        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                items(List(100) { "Item $it" }) {
                    Text(it, modifier = Modifier.padding(10.dp))
                }
            }
            BoxScrollbar(
                listState = listState,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .fillMaxHeight()
                    .width(5.dp)
            )
        }
    }
}

