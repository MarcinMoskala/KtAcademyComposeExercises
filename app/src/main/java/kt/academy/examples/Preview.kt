package kt.academy.examples

import android.R.attr.name
import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import kt.academy.AddIcon
import kt.academy.AvatarIcon
import kt.academy.R
import kt.academy.polished.AppTheme.Surface

@Composable
fun SpeakerCard(
    speaker: Speaker,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(8.dp))
            .clickable(onClick = onClick)
            .background(MaterialTheme.colorScheme.surface),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            imageVector = AvatarIcon,
            contentDescription = null,
            modifier = Modifier
                .padding(8.dp)
                .size(96.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(MaterialTheme.colorScheme.surfaceVariant),
            contentScale = ContentScale.Crop,
        )
        Column(
            Modifier.weight(1f)
                .padding(vertical = 8.dp)
        ) {
            Text(
                text = speaker.name,
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onSurface,
            )
            Spacer(modifier = Modifier.size(6.dp))
            Text(
                text = speaker.title,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
        Image(
            imageVector = AddIcon,
            contentDescription = null,
            modifier = Modifier
                .padding(end = 20.dp)
                .size(40.dp),
        )
    }
}

@Preview
@Composable
private fun SpeakerCardPreview() {
    Column {
        SpeakerCard(
            speaker = Speaker(name = "John Doe", title = "Role"),
            onClick = {},
        )
        SpeakerCard(
                speaker = Speaker(name = "Enrico Diego Giorgio Jesus Valentino Bruno Sanchez", title = "Role"),
            onClick = {},
        )
    }
}

data class Speaker(val name: String, val title: String)

//@Preview
//@Composable
//private fun SpeakerCardPreview(
//    @PreviewParameter(SpeakerProvider::class) speaker: Speaker
//) {
//    SpeakerCard(
//        speaker = speaker,
//        onClick = {},
//    )
//}
//
//class SpeakerProvider : PreviewParameterProvider<Speaker> {
//    val speakers = listOf(
//        Speaker(name = "John Doe", title = "Role"),
//        Speaker(name = "Enrico Diego Giorgio Jesus Valentino Bruno Sanchez", title = "Role"),
//        Speaker(name = "John Doe", title = "Role"),
//    )
//    override val values: Sequence<Speaker> = speakers.asSequence()
//    override fun getDisplayName(index: Int): String? = speakers[index].name
//}


@Composable
fun HelloWorldPreview() {
    MaterialTheme {
        Surface {
            Text(stringResource(R.string.hello_world))
        }
    }
}
















