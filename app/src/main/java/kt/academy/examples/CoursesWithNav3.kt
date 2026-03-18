package kt.academy.examples

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import kotlinx.serialization.Serializable
import kt.academy.*
import kotlin.reflect.KClass

//@Preview
//@Composable
//fun CoursesAppOld() {
//    val backStack = rememberNavBackStack(Screen.Courses)
//    NavDisplay(backStack) { key ->
//        when (key) {
//            is Screen.Courses -> NavEntry(key) {
//                CoursesScreen(
//                    onNavigateToCourse = { id, name ->
//                        backStack.add(Screen.Course(id, name))
//                    }
//                )
//            }
//
//            is Screen.Course -> NavEntry(key) {
//                CourseScreen(
//                    courseId = key.courseId,
//                    courseName = key.courseName,
//                    onNavigateToLesson = { lessonId ->
//                        backStack.add(Screen.Lesson(key.courseId, key.courseName, lessonId))
//                    },
//                    onBackToCourses = {
//                        backStack.removeLastOrNull()
//                    }
//                )
//            }
//
//            is Screen.Lesson -> NavEntry(key) {
//                LessonScreen(
//                    courseId = key.courseId,
//                    courseName = key.courseName,
//                    lessonId = key.lessonId,
//                    onNavigateToLesson = { lessonId ->
//                        backStack.add(Screen.Lesson(key.courseId, key.courseName, lessonId))
//                    },
//                    onBackToCourse = {
//                        backStack.removeAll { it is Screen.Lesson }
//                    },
//                    onBackToCourses = {
//                        backStack.clear()
//                        backStack.add(Screen.Courses)
//                    }
//                )
//            }
//
//            else -> error("Unknown key: $key")
//        }
//    }
//}

@Preview
@Composable
fun CoursesApp() {
    val navBackStack = rememberNavBackStack(Screen.Courses)
    NavDisplay(
        backStack = navBackStack,
        entryProvider = entryProvider {
            entry<Screen.Course> { course ->
                CourseScreen(
                    courseId = course.courseId,
                    courseName = course.courseName,
                    onNavigateToLesson = { lessonId ->
                        navBackStack.add(Screen.Lesson(course.courseId, course.courseName, lessonId))
                    },
                    onBackToCourses = { navBackStack.removeLastOrNull() }
                )
            }

            entry<Screen.Courses> {
                CoursesScreen { id, name ->
                    navBackStack.add(Screen.Course(id, name))
                }
            }

            entry<Screen.Lesson> { lesson ->
                LessonScreen(
                    courseId = lesson.courseId,
                    courseName = lesson.courseName,
                    lessonId = lesson.lessonId,
                    onNavigateToLesson = { lessonId ->
                        navBackStack.add(
                            Screen.Lesson(lesson.courseId, lesson.courseName, lessonId)
                        )
                    },
                    onBackToCourse = { navBackStack.removeAll { it is Screen.Lesson } },
                    onBackToCourses = {
                        navBackStack.clear()
                        navBackStack.add(Screen.Courses)
                    }
                )
            }
        }
    )
}

private class EntryProviderBuilder<T : Any> {
    private val providers = mutableMapOf<KClass<out T>, (T) -> NavEntry<T>>()

    inline fun <reified K : T> entry(noinline content: @Composable (K) -> Unit) {
        providers[K::class] = { key ->
            val typedKey = key as? K ?: error("Unexpected key type: ${key::class}")
            NavEntry(typedKey) {
                content(typedKey)
            }
        }
    }

    fun build(): (T) -> NavEntry<T> = { key ->
        providers[key::class]?.invoke(key)
            ?: error("No entry registered for key type: ${key::class}")
    }
}
private fun <T : Any> entryProvider(
    block: EntryProviderBuilder<T>.() -> Unit
): (T) -> NavEntry<T> = EntryProviderBuilder<T>().apply(block).build()

sealed interface Screen : NavKey {
    @Serializable
    data object Courses : Screen

    @Serializable
    data class Course(val courseId: String, val courseName: String) : Screen

    @Serializable
    data class Lesson(val courseId: String, val courseName: String, val lessonId: Int) : Screen
}

fun loremIpsum(length: Int): String = "Lorem ipsum ".repeat(length / 10 + 1).take(length)

private val allIcons = listOf(
    MilkIcon,
    AppleIcon,
    FavoriteIcon,
    SomeLogoIcon,
    AvatarIcon,
    ComposeLogoIcon,
    AddIcon,
    CheckIcon,
    TruckIcon
)

@Composable
private fun CoursesScreen(onNavigateToCourse: (String, String) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5)),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(100) { index ->
            CourseItem(
                courseId = "course$index",
                courseName = "Course $index",
                onNavigateToCourse = onNavigateToCourse
            )
        }
    }
}

@Composable
private fun CourseItem(
    courseId: String,
    courseName: String,
    onNavigateToCourse: (String, String) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onNavigateToCourse(courseId, courseName) },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(12.dp)
        ) {
            val iconIndex = remember(courseId) { Math.abs(courseId.hashCode()) % allIcons.size }
            Image(
                imageVector = allIcons[iconIndex],
                contentDescription = null,
                modifier = Modifier
                    .size(56.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .weight(1f)
            ) {
                Text(
                    text = courseName,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = "Unlock your potential",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }
    }
}

@Composable
private fun CourseScreen(
    courseId: String,
    courseName: String,
    onNavigateToLesson: (Int) -> Unit,
    onBackToCourses: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {
        Column {
            TextButton(
                onClick = onBackToCourses,
                modifier = Modifier.padding(start = 16.dp, top = 8.dp)
            ) {
                Text("← Back to Courses List", color = MaterialTheme.colorScheme.primary)
            }
            Text(
                text = courseName,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp)
            )
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(100) { index ->
                    LessonItem(
                        lessonId = index,
                        lessonName = "Lesson $index",
                        onNavigateToLesson = onNavigateToLesson
                    )
                }
            }
        }
    }
}

@Composable
private fun LessonItem(
    lessonId: Int,
    lessonName: String,
    onNavigateToLesson: (Int) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onNavigateToLesson(lessonId) },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            val iconIndex = remember(lessonId) { lessonId % allIcons.size }
            Image(
                imageVector = allIcons[iconIndex],
                contentDescription = null,
                modifier = Modifier
                    .size(64.dp)
                    .padding(8.dp)
            )
            Text(
                text = lessonName,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

@Composable
private fun LessonScreen(
    courseId: String,
    courseName: String,
    lessonId: Int,
    onNavigateToLesson: (Int) -> Unit,
    onBackToCourse: () -> Unit,
    onBackToCourses: () -> Unit,
) {
    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            confirmButton = {
                Button(onClick = {
                    showDialog = false
                    onBackToCourse()
                }) {
                    Text("OK")
                }
            },
            title = { Text(if (lessonId == 99) "Course Completed!" else "Lesson Completed!") },
            text = {
                Text(
                    if (lessonId == 99) "Congratulations! You've finished the entire course."
                    else "Great job! You've finished Lesson $lessonId."
                )
            }
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            TextButton(
                onClick = onBackToCourses,
                modifier = Modifier.padding(bottom = 0.dp)
            ) {
                Text("← Back to Courses List", color = MaterialTheme.colorScheme.primary)
            }
            TextButton(
                onClick = onBackToCourse,
                modifier = Modifier.padding(bottom = 8.dp)
            ) {
                Text("← Back to Course $courseName", color = MaterialTheme.colorScheme.primary)
            }
            Text(
                text = "Lesson $lessonId",
                fontSize = 32.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = "Course: $courseName",
                fontSize = 16.sp,
                color = Color.Gray,
                modifier = Modifier.padding(bottom = 24.dp)
            )
            Text(
                text = loremIpsum(2000),
                fontSize = 18.sp,
                lineHeight = 28.sp,
                color = Color.DarkGray
            )
            Box(modifier = Modifier.height(100.dp))
        }

        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .background(Color.White.copy(alpha = 0.95f))
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (lessonId > 0) {
                Button(onClick = { onNavigateToLesson(lessonId - 1) }) {
                    Text("Prev")
                }
            } else {
                Box(modifier = Modifier.size(48.dp))
            }

            Button(
                onClick = { showDialog = true },
                colors = buttonColors(
                    containerColor = Color(0xFF4CAF50),
                    contentColor = Color.White
                )
            ) {
                Text("Complete")
            }

            if (lessonId < 99) {
                Button(onClick = { onNavigateToLesson(lessonId + 1) }) {
                    Text("Next")
                }
            } else {
                Box(modifier = Modifier.size(48.dp))
            }
        }
    }
}

@Preview
@Composable
private fun CoursesScreenPreview() {
    CoursesScreen(
        onNavigateToCourse = { _, _ -> }
    )
}

@Preview
@Composable
private fun CourseScreenPreview() {
    CourseScreen(
        courseId = "course123",
        courseName = "Course 123",
        onNavigateToLesson = {},
        onBackToCourses = {}
    )
}

@Preview
@Composable
private fun LessonScreenPreview() {
    LessonScreen(
        courseId = "course123",
        courseName = "Course 123",
        lessonId = 123,
        onNavigateToLesson = {},
        onBackToCourses = {},
        onBackToCourse = {}
    )
}