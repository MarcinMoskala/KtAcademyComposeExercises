package kt.academy.polished.theme

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val lightThemeColors = AppColor(
    background = Color(0xFFFFFBFF),
    onBackground = Color.Black,
    cardContainer = Color(red = 231, green = 224, blue = 236),
    onCardContainer = Color(red = 73, green = 69, blue = 79),
    textPrimary = Color(0xFF1C1B1F),
    textSecondary = Color(0xFF49454F),
    accent = Color(0xFF0061A4),
    onAccent = Color.White,
    danger = Color(0xFFBA1A1A),
    onDanger = Color.White,
    divider = Color(0xFFCAC4D0),
)

private val darkThemeColor = AppColor(
    background = Color(0xFF141218),
    onBackground = Color.White,
    cardContainer = Color(red = 73, green = 69, blue = 79),
    onCardContainer = Color(red = 202, green = 196, blue = 208),
    textPrimary = Color(0xFFE6E1E5),
    textSecondary = Color(0xFFCAC4D0),
    accent = Color(0xFF9CCAFF),
    onAccent = Color(0xFF003257),
    danger = Color(0xFFFFB4AB),
    onDanger = Color(0xFF690005),
    divider = Color(0xFF49454F),
)

private val themeTypography = AppTypography(
    title = TextStyle(
        fontSize = 18.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight.SemiBold
    ),
    body = TextStyle(
        fontSize = 16.sp,
        lineHeight = 24.sp
    ),
    label = TextStyle(
        fontSize = 14.sp,
        lineHeight = 20.sp,
        fontWeight = FontWeight.Medium
    ),
)

private val themeShapes = AppShape(
    container = RoundedCornerShape(12.dp),
    pill = RoundedCornerShape(percent = 50),
)

val themeSizes = AppSize(
    s = 4.dp,
    m = 8.dp,
    l = 12.dp,
    xl = 16.dp,
    xxl = 24.dp,
    iconSize = 20.dp,
    screenPadding = 16.dp,
)

object AppTheme {

    //these vals are convenience accessors to the theme tokens.
    val colors: AppColor
        @Composable
        @ReadOnlyComposable
        get() = LocalAppColor.current

    val typography: AppTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalAppTypography.current

    val shapes: AppShape
        @Composable
        @ReadOnlyComposable
        get() = LocalAppShape.current

    val sizes: AppSize
        @Composable
        @ReadOnlyComposable
        get() = LocalAppSize.current

    @Composable
    operator fun invoke(
        isDarkTheme: Boolean = isSystemInDarkTheme(),
        content: @Composable () -> Unit,
    ) {
        val themeColors = if (isDarkTheme) darkThemeColor else lightThemeColors
        CompositionLocalProvider(
            LocalAppColor provides themeColors,
            LocalAppTypography provides themeTypography,
            LocalAppShape provides themeShapes,
            LocalAppSize provides themeSizes,
            LocalIndication provides ripple(),
            content = content
        )
    }
}

/**
 * Optionally, we can map our own theme values into MaterialTheme.
 * That way, the standard composables that come with the SDK (like Button, Checkbox) will
 * automatically pick up the mapped tokens from our theme.
 */
@Composable
fun AppThemeMaterial(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colors = if (isDarkTheme) darkThemeColor else lightThemeColors
    val typography = themeTypography
    val shapes = themeShapes
    val sizes = themeSizes

    val materialColorScheme = if (isDarkTheme) {
        darkColorScheme(
            primary = colors.accent,
            onPrimary = colors.onAccent,
            background = colors.background,
            onBackground = colors.textPrimary,
            surface = colors.background,
            onSurface = colors.textPrimary,
            surfaceVariant = colors.onBackground,
            outline = colors.divider,
            error = colors.danger,
            onError = colors.onDanger,
        )
    } else {
        lightColorScheme(
            primary = colors.accent,
            onPrimary = colors.onAccent,
            background = colors.background,
            onBackground = colors.textPrimary,
            surface = colors.background,
            onSurface = colors.textPrimary,
            surfaceVariant = colors.onBackground,
            outline = colors.divider,
            error = colors.danger,
            onError = colors.onDanger,
        )
    }

    CompositionLocalProvider(
        LocalAppColor provides colors,
        LocalAppTypography provides typography,
        LocalAppShape provides shapes,
        LocalAppSize provides sizes,
        LocalIndication provides ripple(),
    ) {
        MaterialTheme(
            colorScheme = materialColorScheme,
            typography = Typography(
                titleLarge = typography.title,
                bodyLarge = typography.body,
                labelLarge = typography.label
            ),
            shapes = Shapes(
                small = shapes.container.copy(CornerSize(8.dp)),
                medium = shapes.container,
                large = shapes.container.copy(CornerSize(16.dp)),
            ),
            content = content,
        )
    }
}