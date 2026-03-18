package kt.academy.examples

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kt.academy.ui.theme.ComposeExercisesTheme

@Composable
fun UserCard(
    name: String,
    avatarUrl: String,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier.fillMaxWidth()) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            AsyncImage(
                model = avatarUrl,
                contentDescription = name,
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
            )
            Text(
                text = name,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
    }
}

@AllDevicesPreview
@Composable
private fun UserCardPreview() {
    ComposeExercisesTheme {
        UserCard(
            name = "John Doe",
            avatarUrl = "https://example.com/avatar.jpg"
        )
    }
}

@AllDevicesPreview
@Composable
private fun UserCardListPreview() {
    ComposeExercisesTheme {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(previewUsers) { (name, url) ->
                UserCard(name = name, avatarUrl = url)
            }
        }
    }
}

@Preview(
    name = "1. Mobile Light",
    device = Devices.PHONE,
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL,
    showBackground = false
)
@Preview(
    name = "2. Mobile Dark",
    device = Devices.PHONE,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL,
    showBackground = false
)
@Preview(
    name = "3. Desktop Light",
    device = Devices.DESKTOP,
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL,
    showBackground = false
)
@Preview(
    name = "4. Desktop Dark",
    device = Devices.DESKTOP,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL,
    showBackground = false
)
annotation class AllDevicesPreview

private val previewUsers = listOf(
    "Alice Johnson" to "https://example.com/alice.jpg",
    "Bob Smith" to "https://example.com/bob.jpg",
    "Carol White" to "https://example.com/carol.jpg",
    "David Brown" to "https://example.com/david.jpg",
    "Eve Wilson" to "https://example.com/eve.jpg",
    "Frank Miller" to "https://example.com/frank.jpg",
)

@Composable
fun AsyncImage(model: String, contentDescription: String, modifier: Modifier) {
    Icon(
        imageVector = Icons.Filled.AccountCircle,
        contentDescription = contentDescription,
        modifier = modifier
    )
}
