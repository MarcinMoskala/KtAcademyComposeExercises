@file:Suppress("ComposeRules")

package com.marcinmoskala.composeexercises.exercises.advanced

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.ConcurrentHashMap

data class Product(val id: Int, val name: String, val price: Double)

object NaturalOrderComparator : Comparator<Product> {
    override fun compare(o1: Product, o2: Product): Int = 0
}

@Composable
fun ProductScreen(
    products: List<Product>,
    modifier: Modifier = Modifier,
    comparator: Comparator<Product> = NaturalOrderComparator,
) {
    RecompositionCounterEffect("ProductScreen")
    val resolvedListState = rememberLazyListState()
    val scope = rememberCoroutineScope()

    val showBackToTop = resolvedListState.firstVisibleItemScrollOffset > 0

    val sortedProducts =
        products.sortedWith(comparator)

    Box(modifier = modifier) {
        LazyColumn(state = resolvedListState) {
            items(sortedProducts) { product ->
                ProductItem(product)
            }
        }

        FirstPricePointer(
            scroll = resolvedListState.firstVisibleItemScrollOffset,
            modifier = Modifier.align(Alignment.TopEnd)
        )

        if (showBackToTop) {
            BackToTopButton(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(32.dp),
                onClick = { scope.launch { resolvedListState.scrollToItem(0) } }
            )
        }
    }
}

@Composable
private fun FirstPricePointer(
    scroll: Int,
    modifier: Modifier = Modifier
) {
    RecompositionCounterEffect("Header")
    Box(
        modifier = modifier
            .offset(y = with(LocalDensity.current) { (-scroll).toDp() })
            .padding(16.dp)
            .padding(top = 24.dp, end = 30.dp)
    ) {
        Text(
            text = "First price ↑",
            color = Color.Black
        )
    }
}

@Composable
private fun BackToTopButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    RecompositionCounterEffect("BackToTopButton")
    Text(
        text = "Back to top",
        color = Color.White,
        fontSize = 16.sp,
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .clickable { onClick() }
            .background(Color.Blue)
            .padding(16.dp)
    )
}

@Composable
fun ProductItem(product: Product, modifier: Modifier = Modifier) {
    RecompositionCounterEffect("ProductItem_${product.id}")
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFFF5F7FB))
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = product.name,
            style = MaterialTheme.typography.bodyLarge,
            color = Color(0xFF1B1F2B)
        )
        Text(
            text = "$${product.price}",
            style = MaterialTheme.typography.bodyMedium,
            color = Color(0xFF4A5568)
        )
    }
}

@Preview
@Composable
private fun PreviewProductScreen() {
    val counter = remember { ActualRecompositionCounter() }
    val products = remember {
        List(100) {
            Product(it, "Product $it", it * 10.0)
        }.toPersistentList()
    }
    CompositionLocalProvider(LocalCompositionCounter provides counter) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            ProductScreen(products, modifier = Modifier.weight(1f))
            AllCounters(counter)
        }
    }
}

@Composable
private fun AllCounters(counter: ActualRecompositionCounter, modifier: Modifier = Modifier) {
    var counts by remember { mutableStateOf(emptyMap<String, Int>()) }
    LaunchedEffect(counter) {
        while (true) {
            counts = counter.getCounts()
                .toList().sortedBy { it.first }.toMap()
            delay(1000)
        }
    }
    Column(modifier = modifier) {
        counts.filter { !it.key.startsWith("ProductItem") }.forEach { (key, count) ->
            Text(
                text = "Recompositions of $key: $count",
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
                fontSize = 16.sp,
                color = Color.Red
            )
        }
        val itemCounts = counts.filter { it.key.startsWith("ProductItem") }
        if (itemCounts.isNotEmpty()) {
            Text(
                text = "Recompositions of ProductItems: ${itemCounts.values.sum()} (across ${itemCounts.size} items)",
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
                fontSize = 16.sp,
                color = Color.Red
            )
        }
    }
}

interface RecompositionCounter {
    fun increment(key: String)
    fun get(key: String): Int?
}

class ActualRecompositionCounter : RecompositionCounter {
    private val counts = mutableMapOf<String, Int>()

    override fun increment(key: String) {
        counts[key] = (counts[key] ?: 0) + 1
    }

    override fun get(key: String): Int? = counts[key]

    fun getCounts(): Map<String, Int> = counts.toMap()
}

object NoOpRecompositionCounter : RecompositionCounter {
    override fun increment(key: String) {}
    override fun get(key: String): Int? = null
}

val LocalCompositionCounter =
    staticCompositionLocalOf<RecompositionCounter> { NoOpRecompositionCounter }

@Composable
inline fun RecompositionCounterEffect(key: String) {
    val counter = LocalCompositionCounter.current
    SideEffect {
        counter.increment(key)
    }
}