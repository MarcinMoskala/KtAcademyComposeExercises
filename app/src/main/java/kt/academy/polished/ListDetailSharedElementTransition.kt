package kt.academy.polished

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.rememberAsyncImagePainter
import kotlinx.collections.immutable.persistentListOf
import kt.academy.ArrowLeftIcon
import java.util.UUID
import kotlin.collections.plus

@Preview
@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
private fun ListDetailsShared() {
    var destinations by remember { mutableStateOf(listOf<NavKey>(ListScreenDestination)) }
    AnimatedContent(destinations.last()) { destination ->
        when (destination) {
            is ListScreenDestination ->
                ListScreen(
                    onOpenItemDetails = { courseId ->
                        destinations += DetailScreenDestination(courseId)
                    }
                )

            is DetailScreenDestination ->
                DetailScreen(
                    courseId = destination.courseId,
                    onNavigateBack = {
                        destinations = destinations.dropLast(1)
                    }
                )
        }
    }
}

@Composable
fun ListScreen(
    onOpenItemDetails: (courseId: String) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.consumeWindowInsets(paddingValues),
            contentPadding = paddingValues,
        ) {
            items(courses) { course ->
                CourseListItem(
                    course = course,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onOpenItemDetails(course.id) }
                        .padding(12.dp)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    courseId: String,
    onNavigateBack: () -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = ArrowLeftIcon,
                            contentDescription = "Navigate Up",
                        )
                    }
                },
                title = {
                    Text(text = "Details")
                }
            )
        }
    ) { paddingValues ->
        val course = courses.first { it.id == courseId }
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(course.teacherAvatar),
                contentDescription = course.teacherName,
                modifier = Modifier
            )
            Column(
                modifier = Modifier.padding(horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = course.title,
                    fontSize = 24.sp,
                    modifier = Modifier
                )
                Text(
                    text = course.description,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                )
            }
            Column(
                modifier = Modifier.padding(horizontal = 24.dp),
            ) {
                Text(text = "Teacher: ${course.teacherName}")
                Text(text = "Duration: ${course.durationInWeeks} weeks")
            }
        }
    }
}

@Composable
private fun CourseListItem(
    course: Course,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.clearAndSetSemantics {
            contentDescription =
                "${course.title} course. ${course.description}. Approximate duration: ${course.durationInWeeks} weeks."
        },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier.size(52.dp),
            painter = rememberAsyncImagePainter(course.teacherAvatar),
            contentDescription = null
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 8.dp)
        ) {
            Text(
                text = course.title,
                fontSize = 18.sp,
                modifier = Modifier
            )
            Text(
                text = course.description,
                modifier = Modifier
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "${course.durationInWeeks}",
                fontSize = 24.sp
            )
            Text(
                text = "weeks",
                fontSize = 12.sp
            )
        }
    }
}
// Hide
interface NavKey

data object ListScreenDestination : NavKey

data class DetailScreenDestination(
    val courseId: String
) : NavKey

data class Course(
    val id: String,
    val title: String,
    val description: String,
    val durationInWeeks: Int,
    val teacherName: String,
    val teacherAvatar: String
)

private val courses = persistentListOf(
    Course(
        id = UUID.randomUUID().toString(),
        title = "Advanced Compose",
        description = "Level up your Compose skills and master building UIs",
        durationInWeeks = 3,
        teacherName = "Marcin & Jov",
        teacherAvatar = "https://cdn.prod.website-files.com/68ef5b3464d787c12ae12974/6960ff03488773ecb6108447_2.webp"
    ),
    Course(
        id = UUID.randomUUID().toString(),
        title = "Polished Compose",
        description = "Master building beautiful UIs with Compose",
        durationInWeeks = 2,
        teacherName = "Marcin & Jov",
        teacherAvatar = "https://cdn.prod.website-files.com/68ef5b3464d787c12ae12974/6960ff020f61c32702bb5db0_3-p-1600.webp"
    ),
    Course(
        id = UUID.randomUUID().toString(),
        title = "Coroutines Mastery",
        description = "Master concurrency and get your code as optimal as it could be",
        durationInWeeks = 4,
        teacherName = "Marcin",
        teacherAvatar = "https://cdn.prod.website-files.com/68ef5b3464d787c12ae12974/6960ff03488773ecb6108447_2.webp"
    ),
)

@Preview
@Composable
private fun Preview() {
    MaterialTheme {
        ListDetailsShared()
    }
}