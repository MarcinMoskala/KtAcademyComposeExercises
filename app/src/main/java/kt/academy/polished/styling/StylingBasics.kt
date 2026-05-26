package kt.academy.polished.styling

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kt.academy.polished.theme.AppTheme

@Composable
fun CourseProgressScreen(
    courses: ImmutableList<CourseProgress>,
    onContinue: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            // TODO: use AppTheme background and screen padding
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Text(
            text = "Polished Compose",
            // TODO: use AppTheme typography and colors
            style = MaterialTheme.typography.headlineSmall,
        )

        courses.forEach { course ->
            CourseProgressCard(course = course)
        }

        Spacer(modifier = Modifier.weight(1f))

        // TODO: replace with a reusable PrimaryButton
        Button(
            onClick = onContinue,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF0061A4),
                contentColor = Color.White,
            ),
        ) {
            Text("Continue learning")
        }
    }
}

@Composable
private fun CourseProgressCard(
    course: CourseProgress,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF0061A4)),
                contentAlignment = Alignment.Center,
            ) {
                Text("C", color = Color.White)
            }

            Column(modifier = Modifier.weight(1f)) {
                Text(course.title, fontWeight = FontWeight.SemiBold)
                Text(course.subtitle, color = MaterialTheme.colorScheme.onSurfaceVariant)
                Text(course.progress)
            }

            // TODO: replace direct status styling with a reusable StatusChip
            Surface(
                shape = RoundedCornerShape(50),
                color = when (course.status) {
                    CourseStatus.Active -> Color(0xFF0061A4)
                    CourseStatus.Draft -> Color.LightGray
                    CourseStatus.Blocked -> Color(0xFFBA1A1A)
                },
            ) {
                Text(
                    text = course.status.name,
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                    color = Color.White,
                )
            }
        }
    }
}

// Hide

@Preview
@Composable
private fun CourseProgressScreenPreview() {
    AppTheme {
        CourseProgressScreen(
            courses = persistentListOf(
                CourseProgress(
                    "Styling custom components",
                    "Reusable visual contracts",
                    "6 of 8 lessons",
                    CourseStatus.Active
                ),
                CourseProgress(
                    "Styling beyond Material",
                    "BasicTextField and semantics",
                    "2 of 6 lessons",
                    CourseStatus.Draft
                ),
                CourseProgress(
                    "Shared transitions",
                    "Optional advanced polish",
                    "Locked",
                    CourseStatus.Blocked
                ),
            ),
            onContinue = {},
        )
    }
}

data class CourseProgress(
    val title: String,
    val subtitle: String,
    val progress: String,
    val status: CourseStatus,
)

enum class CourseStatus {
    Active,
    Draft,
    Blocked,
}