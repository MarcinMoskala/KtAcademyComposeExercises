package com.example.userprofile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kt.academy.EditIcon
import kt.academy.EmailIcon
import kt.academy.LocationIcon
import kt.academy.PhoneIcon
import kt.academy.StarIcon

data class Preference(
    val icon: ImageVector,
    val title: String,
    val value: String
)

data class Stat(
    val label: String,
    val value: String
)

@Composable
fun UserProfileScreen(preferences: List<Preference>, tags: List<String>, stats: List<Stat>) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            // TODO: Use a better layout for that, and configure it properly
            // to support big list of items, and 12.dp space between items
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
        ) {
            ProfileHeader(
                name = "Jane Doe",
                handle = "@janedoe"
            )
            StatsRow(stats = stats)
            SectionTitle(title = "Interests")
            Spacer(modifier = Modifier.height(8.dp))
            TagsFlowRow(tags = tags)
            SectionTitle(title = "Details")
            preferences.forEach { preference ->
                PreferenceItem(preference = preference)
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun ProfileHeader(
    name: String,
    handle: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box(
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primaryContainer),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = name.take(1),
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }

        // TODO: Add extra 4dp space between above Box and name
        Text(
            text = name,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = handle,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun StatsRow(stats: List<Stat>) {
    Row(
        // TODO: Add appropriate arrangement and alignment
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(vertical = 16.dp, horizontal = 8.dp),
    ) {
        // TODO:
        // - Each value should take even amount of space
        // - Configure this column with alignment and arrangement (there should be 4dp space between value and label)
        // - Add spacers of size 1x32 dp and color outlineVariant between values
        stats.forEachIndexed { index, stat ->
            Column {
                Text(
                    text = stat.value,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = stat.label,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun SectionTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun TagsFlowRow(tags: List<String>) {
    // TODO: Use better layout for that, specify arrangement and alignment to have 8dp space between tags
    Row {
        tags.forEach { tag ->
            TagChip(text = tag)
        }
    }
}

@Composable
private fun TagChip(text: String) {
    Surface(
        shape = RoundedCornerShape(50),
        color = MaterialTheme.colorScheme.secondaryContainer
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
        )
    }
}

@Composable
private fun PreferenceItem(preference: Preference) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        // TODO: Ensure 16dp space between items, add 16dp padding around this Row,
        //  add alignment to center items vertically and ensure rows fill max space
        Row {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = preference.icon,
                    contentDescription = preference.title,
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }

            Column {
                // TODO: Add 2dp space between title and value
                Text(
                    text = preference.title,
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = preference.value,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Medium
                )
            }

            // TODO: Icon should be on right, it should never be made invisible because of a too long text
            Icon(
                imageVector = EditIcon,
                contentDescription = "Edit ${preference.title}",
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Preview
@Composable
private fun UserProfileScreenPreview() {
    val preferences = listOf(
        Preference(EmailIcon, "Email", "jane.doe@example.com"),
        Preference(PhoneIcon, "Phone", "+1 555 0123 456"),
        Preference(
            LocationIcon,
            "Location (Postal Code, City, State, Country)",
            "12345, San Francisco, California, USA"
        ),
        Preference(StarIcon, "Membership", "Gold Tier"),
        Preference(EditIcon, "Bio", "Android developer, coffee lover.")
    )

    val tags = listOf(
        "Kotlin", "Compose", "Android", "Coroutines", "Flow",
        "Architecture", "Testing", "Coffee", "Hiking", "Photography"
    )

    val stats = listOf(
        Stat("Posts", "128"),
        Stat("Followers", "4.2k"),
        Stat("Following", "312")
    )
    MaterialTheme {
        UserProfileScreen(
            preferences = preferences,
            tags = tags,
            stats = stats
        )
    }
}
