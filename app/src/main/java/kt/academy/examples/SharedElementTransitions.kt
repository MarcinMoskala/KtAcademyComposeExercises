package kt.academy.examples

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Preview
@Composable
fun SharedElementTransitions() {
    val aligns = listOf(
        Alignment.CenterStart,
        Alignment.TopCenter,
        Alignment.CenterEnd,
        Alignment.BottomCenter
    )
    var i by remember { mutableStateOf(0) }
    LaunchedEffect(Unit) {
        while (true) {
            delay(1000)
            i++
        }
    }
    SharedTransitionLayout {
        AnimatedContent(i) { i ->
            Box(Modifier.fillMaxSize()) {
                Text(
                    "A",
                    color = Color.White,
                    modifier = Modifier
                        .sharedElement(
                            sharedContentState = rememberSharedContentState(key = "A"),
                            animatedVisibilityScope = this@AnimatedContent
                        )
                        .align(aligns[(i + 0) % 4])
                        .background(Color.Blue)
                        .padding(20.dp)
                )
                Text(
                    "B",
                    color = Color.White,
                    modifier = Modifier
                        .sharedElement(
                            sharedContentState = rememberSharedContentState(key = "B"),
                            animatedVisibilityScope = this@AnimatedContent
                        )
                        .align(aligns[(i + 1) % 4])
                        .background(Color.Red)
                        .padding(20.dp)
                )
                Text(
                    "C",
                    color = Color.White,
                    modifier = Modifier
                        .sharedElement(
                            sharedContentState = rememberSharedContentState(key = "C"),
                            animatedVisibilityScope = this@AnimatedContent
                        )
                        .align(aligns[(i + 2) % 4])
                        .background(Color.Magenta)
                        .padding(20.dp)
                )
                Text(
                    "D",
                    color = Color.Black,
                    modifier = Modifier
                        .sharedElement(
                            sharedContentState = rememberSharedContentState(key = "D"),
                            animatedVisibilityScope = this@AnimatedContent
                        )
                        .align(aligns[(i + 3) % 4])
                        .background(Color.Yellow)
                        .padding(20.dp)
                )
            }
        }
    }
}

@Preview
@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun TagsExample() {
    var comparator by remember { mutableStateOf(NaturalOrder) }
    var tags by remember { mutableStateOf(Tags.take(10)) }
    val sorted = remember(comparator, tags) { tags.sortedWith(comparator) }
    Column(Modifier.safeContentPadding()) {
        SharedTransitionLayout {
            AnimatedContent(sorted) { sorted ->
                FlowRow(
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    sorted.forEach { tag ->
                        TagChip(
                            text = tag, modifier = Modifier
                                .clickable { tags -= tag }
                                .sharedElement(
                                    sharedContentState = rememberSharedContentState(key = tag),
                                    animatedVisibilityScope = this@AnimatedContent
                                )
                        )
                    }
                }
            }
        }
        Spacer(Modifier.weight(1f))
        Button({ tags += Tags[tags.size % Tags.size] }) { Text("Add") }
        Button({ comparator = compareBy { it } }) { Text("Sort by name") }
        Button({ comparator = compareBy { it.length } }) { Text("Sort by length") }
        Button({ comparator = NaturalOrder }) { Text("Reset sort") }
    }
}

// Hide
val Tags = listOf(
    "Kotlin",
    "Compose",
    "Android",
    "Coroutines",
    "Flow",
    "Architecture",
    "Testing",
    "Coffee",
    "Hiking",
    "Photography",
    "Travel",
    "Music",
    "Cooking",
    "Design",
    "UI/UX",
    "KMP",
    "Multiplatform",
    "Server-side",
    "Ktor",
    "SQL",
    "NoSQL",
    "Git",
    "CI/CD",
    "Docker",
    "Kubernetes",
    "Cloud",
    "Firebase",
    "AI",
    "Machine Learning",
    "Data Science",
    "Security",
    "Privacy",
    "Open Source",
    "Community",
    "Speaking",
    "Writing",
    "Blogging",
    "Podcasting",
    "Gaming",
    "Fitness",
    "Yoga",
    "Meditation",
    "Nature",
    "Art",
    "History",
    "Science",
    "Math",
    "Philosophy",
    "Psychology",
    "Education",
)
val NaturalOrder = Comparator<String> { _, _ -> 0 }

@Composable
private fun TagChip(text: String, modifier: Modifier = Modifier) {
    Surface(
        shape = RoundedCornerShape(50),
        color = MaterialTheme.colorScheme.secondaryContainer,
        modifier = modifier,
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
        )
    }
}