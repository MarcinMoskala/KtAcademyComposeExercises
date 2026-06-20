package kt.academy.polished.adaptive

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList
import kt.academy.polished.theme.AppTheme

@Composable
fun CourseCatalogScreen(
    categories: ImmutableList<CourseCategory>,
    courses: ImmutableList<Course>,
    selectedCategoryId: String,
    selectedCourseId: String?,
    layoutMode: CourseCatalogLayoutMode,
    onCategorySelected: (String) -> Unit,
    onCourseSelected: (String) -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    // TODO: Use layoutMode to choose between:
    // SinglePane, SupportingPane, and MultiPane.
    // The screen itself should not read WindowSizeClass directly.

    val filteredCourses = courses.filter { it.categoryId == selectedCategoryId }
    val selectedCourse = courses.firstOrNull { it.id == selectedCourseId }

    Surface(modifier = modifier.fillMaxSize()) {
        if (selectedCourse != null) {
            CourseDetailPane(
                course = selectedCourse,
                showBackButton = true,
                onBackClick = onBackClick,
                modifier = Modifier.fillMaxSize(),
            )
        } else {
            Column(modifier = Modifier.fillMaxSize()) {
                CategoryFilterRow(
                    categories = categories,
                    selectedCategoryId = selectedCategoryId,
                    onCategorySelected = onCategorySelected,
                    modifier = Modifier.fillMaxWidth(),
                )
                CourseListPane(
                    courses = filteredCourses,
                    selectedCourseId = selectedCourseId,
                    onCourseSelected = onCourseSelected,
                    modifier = Modifier.fillMaxSize(),
                )
            }
        }
    }
}

@Composable
private fun CategoryFilterRow(
    categories: List<CourseCategory>,
    selectedCategoryId: String,
    onCategorySelected: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .horizontalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        categories.forEach { category ->
            FilterChip(
                selected = category.id == selectedCategoryId,
                onClick = { onCategorySelected(category.id) },
                label = { Text(category.name) },
            )
        }
    }
}

@Composable
private fun CourseListPane(
    courses: List<Course>,
    selectedCourseId: String?,
    onCourseSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        items(courses, key = { it.id }) { course ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onCourseSelected(course.id) },
                colors = CardDefaults.cardColors(
                    containerColor = if (course.id == selectedCourseId) {
                        MaterialTheme.colorScheme.primaryContainer
                    } else {
                        MaterialTheme.colorScheme.surfaceVariant
                    },
                ),
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp),
                ) {
                    Text(course.title, style = MaterialTheme.typography.titleMedium)
                    Text(course.level, style = MaterialTheme.typography.labelMedium)
                    Text(course.description, maxLines = 2, overflow = TextOverflow.Ellipsis)
                }
            }
        }
    }
}

@Composable
private fun CourseDetailPane(
    course: Course,
    showBackButton: Boolean,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    // TODO: Keep detail readable on wide windows.
    // Do not let body text stretch forever.
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        item {
            if (showBackButton) {
                TextButton(onClick = onBackClick) {
                    Text("Back")
                }
            }

            Text(course.title, style = MaterialTheme.typography.headlineSmall)
            Text(course.level, style = MaterialTheme.typography.titleSmall)
            Text(course.description, style = MaterialTheme.typography.bodyLarge)

            LinearProgressIndicator(
                progress = { course.completedLessons / course.totalLessons.toFloat() },
                modifier = Modifier.fillMaxWidth(),
            )

            Text("${course.completedLessons} of ${course.totalLessons} lessons completed")
        }
    }
}

data class CourseCategory(
    val id: String,
    val name: String,
)

data class Course(
    val id: String,
    val categoryId: String,
    val title: String,
    val level: String,
    val description: String,
    val completedLessons: Int,
    val totalLessons: Int,
)

enum class CourseCatalogLayoutMode {
    SinglePane,
    SupportingPane,
    MultiPane,
}

private val sampleCourseCategories = persistentListOf(
    CourseCategory(id = "compose", name = "Compose"),
    CourseCategory(id = "architecture", name = "Architecture"),
    CourseCategory(id = "coroutines", name = "Coroutines"),
    CourseCategory(id = "testing", name = "Testing"),
)

private val sampleCourses = persistentListOf(
    Course(
        id = "compose-styling",
        categoryId = "compose",
        title = "Styling custom components",
        level = "Intermediate",
        description = "Learn how to move styling decisions out of screens and into reusable Compose components with clear defaults.",
        completedLessons = 6,
        totalLessons = 8,
    ),
    Course(
        id = "compose-adaptive",
        categoryId = "compose",
        title = "Building adaptive UI with Compose",
        level = "Intermediate",
        description = "Build layouts that adapt between single-pane, supporting-pane, and multi-pane structures.",
        completedLessons = 2,
        totalLessons = 7,
    ),
    Course(
        id = "compose-preview",
        categoryId = "compose",
        title = "Preview-driven UI development",
        level = "Beginner friendly",
        description = "Use previews to inspect themes, font scales, locales, and interaction states before running the full app.",
        completedLessons = 4,
        totalLessons = 5,
    ),
    Course(
        id = "architecture-state",
        categoryId = "architecture",
        title = "State holders and screen boundaries",
        level = "Advanced",
        description = "Design clear UI state contracts between ViewModels, screens, and reusable content composables.",
        completedLessons = 1,
        totalLessons = 6,
    ),
    Course(
        id = "coroutines-flow",
        categoryId = "coroutines",
        title = "Flow for UI state",
        level = "Intermediate",
        description = "Represent streams of UI state with Flow, StateFlow, and lifecycle-aware collection.",
        completedLessons = 5,
        totalLessons = 9,
    ),
)

private val longTitleCourse = Course(
    id = "compose-long-title",
    categoryId = "compose",
    title = "Designing adaptive Compose screens that remain readable, usable, and predictable across phones, tablets, foldables, and desktop windows",
    level = "Advanced",
    description = "This preview checks whether long titles wrap gracefully and whether the detail pane keeps readable line lengths.",
    completedLessons = 3,
    totalLessons = 10,
)

@Preview(widthDp = 390, heightDp = 844, name = "Single pane - list")
@Composable
private fun CourseCatalogSinglePaneListPreview() {
    AppTheme {
        CourseCatalogScreen(
            categories = sampleCourseCategories,
            courses = sampleCourses,
            selectedCategoryId = "compose",
            selectedCourseId = null,
            layoutMode = CourseCatalogLayoutMode.SinglePane,
            onCategorySelected = {},
            onCourseSelected = {},
            onBackClick = {},
        )
    }
}

@Preview(widthDp = 390, heightDp = 844, name = "Single pane - detail")
@Composable
private fun CourseCatalogSinglePaneDetailPreview() {
    AppTheme {
        CourseCatalogScreen(
            categories = sampleCourseCategories,
            courses = sampleCourses,
            selectedCategoryId = "compose",
            selectedCourseId = "compose-adaptive",
            layoutMode = CourseCatalogLayoutMode.SinglePane,
            onCategorySelected = {},
            onCourseSelected = {},
            onBackClick = {},
        )
    }
}

@Preview(widthDp = 840, heightDp = 900, name = "Supporting pane - selected")
@Composable
private fun CourseCatalogSupportingPaneSelectedPreview() {
    AppTheme {
        CourseCatalogScreen(
            categories = sampleCourseCategories,
            courses = sampleCourses,
            selectedCategoryId = "compose",
            selectedCourseId = "compose-styling",
            layoutMode = CourseCatalogLayoutMode.SupportingPane,
            onCategorySelected = {},
            onCourseSelected = {},
            onBackClick = {},
        )
    }
}

@Preview(widthDp = 840, heightDp = 900, name = "Supporting pane - empty")
@Composable
private fun CourseCatalogSupportingPaneEmptyPreview() {
    AppTheme {
        CourseCatalogScreen(
            categories = sampleCourseCategories,
            courses = sampleCourses,
            selectedCategoryId = "compose",
            selectedCourseId = null,
            layoutMode = CourseCatalogLayoutMode.SupportingPane,
            onCategorySelected = {},
            onCourseSelected = {},
            onBackClick = {},
        )
    }
}

@Preview(widthDp = 1280, heightDp = 900, name = "Multi pane")
@Composable
private fun CourseCatalogMultiPanePreview() {
    AppTheme {
        CourseCatalogScreen(
            categories = sampleCourseCategories,
            courses = sampleCourses,
            selectedCategoryId = "compose",
            selectedCourseId = "compose-adaptive",
            layoutMode = CourseCatalogLayoutMode.MultiPane,
            onCategorySelected = {},
            onCourseSelected = {},
            onBackClick = {},
        )
    }
}

@Preview(widthDp = 1280, heightDp = 900, name = "Multi pane - long title")
@Composable
private fun CourseCatalogLongTitlePreview() {
    AppTheme {
        CourseCatalogScreen(
            categories = sampleCourseCategories,
            courses = (sampleCourses + longTitleCourse).toPersistentList(),
            selectedCategoryId = "compose",
            selectedCourseId = "compose-long-title",
            layoutMode = CourseCatalogLayoutMode.MultiPane,
            onCategorySelected = {},
            onCourseSelected = {},
            onBackClick = {},
        )
    }
}

@Preview(widthDp = 840, heightDp = 900, fontScale = 1.4f, name = "Large font scale")
@Composable
private fun CourseCatalogLargeFontPreview() {
    AppTheme {
        CourseCatalogScreen(
            categories = sampleCourseCategories,
            courses = sampleCourses,
            selectedCategoryId = "compose",
            selectedCourseId = "compose-styling",
            layoutMode = CourseCatalogLayoutMode.SupportingPane,
            onCategorySelected = {},
            onCourseSelected = {},
            onBackClick = {},
        )
    }
}

@Preview(widthDp = 740, heightDp = 360, name = "Compact height")
@Composable
private fun CourseCatalogCompactHeightPreview() {
    AppTheme {
        CourseCatalogScreen(
            categories = sampleCourseCategories,
            courses = sampleCourses,
            selectedCategoryId = "compose",
            selectedCourseId = null,
            layoutMode = CourseCatalogLayoutMode.SinglePane,
            onCategorySelected = {},
            onCourseSelected = {},
            onBackClick = {},
        )
    }
}