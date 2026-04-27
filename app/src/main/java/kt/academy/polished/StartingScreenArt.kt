package kt.academy.polished

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kt.academy.R

@Composable
fun StartingScreenArt(isCompact: Boolean, modifier: Modifier) {
    BoxWithConstraints(modifier = modifier.fillMaxSize()) {
        maxWidth
        maxHeight
        if (isCompact) {
            Image(
                painter = painterResource(R.drawable.compose_background_1),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier,
            )
        } else {
            Image(
                painter = painterResource(R.drawable.compose_background_1),
                contentDescription = null,
                modifier = Modifier,
            )
            Image(
                painter = painterResource(R.drawable.compose_background_2),
                contentDescription = null,
                modifier = Modifier,
            )
            Image(
                painter = painterResource(R.drawable.compose_background_3),
                contentDescription = null,
                modifier = Modifier,
            )
        }
    }
}

@Composable
fun ComposeStartScreen(
    title: String,
    opening: String,
    gameModes: List<CoroutinesGameMode>,
    onStart: (CoroutinesGameMode) -> Unit,
    modifier: Modifier = Modifier
) {
    BoxWithConstraints(modifier = modifier.fillMaxSize()) {
        val isCompact = maxWidth < 600.dp
        val horizontalPadding = if (isCompact) 24.dp else 96.dp
        val verticalPadding = if (isCompact) 28.dp else 64.dp

        StartingScreenArt(isCompact, Modifier)

        TitleBlock(
            title = title,
            opening = opening,
            isCompact = isCompact,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = horizontalPadding, end = horizontalPadding, top = verticalPadding)
        )
        ChallengeBlock(
            gameModes = gameModes,
            onStart = onStart,
            isCompact = isCompact,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(
                    start = horizontalPadding,
                    end = horizontalPadding,
                    bottom = verticalPadding
                )
        )
    }
}

@Preview
@Preview(device = Devices.PIXEL)
@Preview(device = Devices.NEXUS_10)
@Preview(device = Devices.FOLDABLE)
@Preview(device = Devices.DESKTOP)
@Preview(device = Devices.TV_720p)
@Preview(device = Devices.AUTOMOTIVE_1024p)
@Preview(device = "spec:width=1280dp,height=832dp")
@Preview(device = "spec:width=1080dp,height=832dp")
@Preview(device = "spec:width=1480dp,height=632dp")
@Preview(device = "spec:width=580dp,height=832dp")
// TODO: Add preview configurations
@Composable
private fun StartScreenCoroutinesGamePreview() {
    GameDesign {
        ComposeStartScreen(
            title = "Modifier Order Guesser",
            opening = "So you think you understand how modifiers work in Kotlin? Let's find out!",
            gameModes = CoroutinesGameMode.entries,
            onStart = {}
        )
    }
}

// Unrelated files, do not modify

enum class CoroutinesGameMode(val displayName: String) {
    Easy("Easy"),
    Medium("Medium"),
    Hard("Hard"),
    Extreme("Extreme")
}

@Composable
private fun TitleBlock(
    title: String,
    opening: String,
    isCompact: Boolean,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(if (isCompact) 12.dp else 16.dp),
        horizontalAlignment = Alignment.Start,
        modifier = modifier,
    ) {
        Text(
            title,
            textAlign = TextAlign.Start,
            color = MaterialTheme.colorScheme.secondary,
            style = MaterialTheme.typography.titleLarge,
        )
        Text(
            opening,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.headlineSmall,
        )
    }
}

@Composable
private fun ChallengeBlock(
    gameModes: List<CoroutinesGameMode>,
    onStart: (CoroutinesGameMode) -> Unit,
    isCompact: Boolean,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(if (isCompact) 12.dp else 16.dp),
        horizontalAlignment = Alignment.Start,
        modifier = modifier,
    ) {
        Text(
            "What kind of challenge do you want to face?",
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.bodyMedium,
        )
        DifficultyRow(gameModes = gameModes, onStart = onStart, isCompact = isCompact)
    }
}

@Composable
private fun DifficultyRow(
    gameModes: List<CoroutinesGameMode>,
    onStart: (CoroutinesGameMode) -> Unit,
    isCompact: Boolean,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(if (isCompact) 10.dp else 12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        val lastIndex = gameModes.lastIndex
        gameModes.forEachIndexed { index, mode ->
            RegularButton(
                leftRounded = index == 0,
                rightRounded = index == lastIndex,
                onClick = { onStart(mode) },
            ) {
                Text(
                    mode.displayName,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}

@Composable
fun RegularButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    leftRounded: Boolean = true,
    rightRounded: Boolean = true,
    content: @Composable () -> Unit = {},
) {
    val regularRoundingSize = 12.dp
    val roundedRoundingSize = 100.dp
    val shape = RoundedCornerShape(
        topStart = if (leftRounded) roundedRoundingSize else regularRoundingSize,
        bottomStart = if (leftRounded) roundedRoundingSize else regularRoundingSize,
        topEnd = if (rightRounded) roundedRoundingSize else regularRoundingSize,
        bottomEnd = if (rightRounded) roundedRoundingSize else regularRoundingSize,
    )
    Button(
        onClick = onClick,
        shape = shape,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
        ),
        contentPadding = PaddingValues(
            horizontal = 20.dp,
            vertical = 16.dp,
        ),
        modifier = modifier.defaultMinSize(minWidth = 84.dp, minHeight = 56.dp),
    ) {
        content()
    }
}

@Composable
private fun GameDesign(
    modifier: Modifier = Modifier,
    content: @Composable (Boolean) -> Unit,
) {
    val fontBody = FontFamily(Font(R.font.geist))
    val fontHeader = FontFamily(Font(R.font.geist))
    val density = LocalDensity.current
    val screenWidth = with(density) { LocalWindowInfo.current.containerSize.width.toDp() }
    Box(
        modifier = modifier
            .background(Color(0xFF000000))
            .safeDrawingPadding()
    ) {
        val isSmallScreen = screenWidth < 600.dp
        MaterialTheme(
            typography = MaterialTheme.typography.copy(
                titleLarge = MaterialTheme.typography.titleLarge.copy(
                    fontFamily = fontHeader,
                    fontSize = if (isSmallScreen) 40.sp else 60.sp,
                    lineHeight = if (isSmallScreen) 44.sp else 64.sp
                ),
                titleMedium = MaterialTheme.typography.titleMedium.copy(
                    fontFamily = fontHeader,
                    fontSize = if (isSmallScreen) 30.sp else 42.sp,
                    lineHeight = if (isSmallScreen) 34.sp else 46.sp
                ),
                bodyMedium = MaterialTheme.typography.bodyMedium.copy(
                    fontFamily = fontBody,
                    fontSize = if (isSmallScreen) 18.sp else 24.sp,
                    lineHeight = if (isSmallScreen) 22.sp else 28.sp
                ),
                bodySmall = MaterialTheme.typography.bodySmall.copy(
                    fontFamily = fontBody,
                    fontSize = if (isSmallScreen) 14.sp else 16.sp,
                    lineHeight = if (isSmallScreen) 18.sp else 20.sp
                ),
                headlineSmall = MaterialTheme.typography.headlineSmall.copy(
                    fontFamily = fontHeader,
                    fontSize = if (isSmallScreen) 24.sp else 32.sp,
                    lineHeight = if (isSmallScreen) 28.sp else 36.sp
                ),
            ),
            colorScheme = darkColorScheme(
                primary = Color(0xFFFF4528),
                onPrimary = Color(0xFFFFFFFF),
                primaryContainer = Color(0xFFFF4528),
                onPrimaryContainer = Color(0xFFFFFFFF),
                secondary = Color(0xFFF6FEFF),
                onSecondary = Color(0xFF061E26),
                secondaryContainer = Color(0xFF061E26),
                onSecondaryContainer = Color(0xFFF6FEFF),
                tertiary = Color(0xFF29E9FF),
                onTertiary = Color(0xFF00363F),
                tertiaryContainer = Color(0xFF29E9FF),
                onTertiaryContainer = Color(0xFF042328),
                error = Color(0xFFF02121),
                onError = Color(0xFF3F181C),
                errorContainer = Color(0xFF3F181C),
                onErrorContainer = Color(0xFFF02121),
                background = Color(0xFF031217),
                onBackground = Color(0xFFE1E9ED),
                surface = Color(0xFF031217),
                onSurface = Color(0xFFE1E9ED),
                surfaceVariant = Color(0xFF072631),
                onSurfaceVariant = Color(0xFFBEC9CE),
                outline = Color(0xFFD6E3E9),
                outlineVariant = Color(0xFF396575),
                scrim = Color(0xFF000000),
                inverseSurface = Color(0xFFF4FCFF),
                inverseOnSurface = Color(0xFF041E2A),
                inversePrimary = Color(0xFFC61E04),
                surfaceDim = Color(0xFF010405),
                surfaceBright = Color(0xFF0B3747),
                surfaceContainerLowest = Color(0xFF031015),
                surfaceContainerLow = Color(0xFF04151B),
                surfaceContainer = Color(0xFF051920),
                surfaceContainerHigh = Color(0xFF07202A),
                surfaceContainerHighest = Color(0xFF072631),
            )
        ) {
            content(isSmallScreen)
        }
    }
}