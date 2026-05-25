package kt.academy.polished.theme

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class AppColor(
    val background: Color,
    val onBackground: Color,
    val textPrimary: Color,
    val textSecondary: Color,
    val accent: Color,
    val onAccent: Color,
    val danger: Color,
    val onDanger: Color,
    val divider: Color,
)

@Immutable
data class AppTypography(
    val titleLarge: TextStyle,
    val titleMedium: TextStyle,
    val body: TextStyle,
    val bodyStrong: TextStyle,
    val label: TextStyle,
)

@Immutable
data class AppShape(
    val small: CornerBasedShape,
    val medium: CornerBasedShape,
    val large: CornerBasedShape,
    val pill: CornerBasedShape,
)

@Immutable
data class AppSize(
    val xs: Dp,
    val sm: Dp,
    val md: Dp,
    val lg: Dp,
    val xl: Dp,
    val buttonHeight: Dp,
    val iconSize: Dp,
    val screenPadding: Dp,
)

val LocalAppColor = staticCompositionLocalOf {
    AppColor(
        background = Color.Unspecified,
        onBackground = Color.Unspecified,
        textPrimary = Color.Unspecified,
        textSecondary = Color.Unspecified,
        accent = Color.Unspecified,
        onAccent = Color.Unspecified,
        danger = Color.Unspecified,
        onDanger = Color.Unspecified,
        divider = Color.Unspecified,
    )
}

val LocalAppTypography = staticCompositionLocalOf {
    AppTypography(
        titleLarge = TextStyle.Default,
        titleMedium = TextStyle.Default,
        body = TextStyle.Default,
        bodyStrong = TextStyle.Default,
        label = TextStyle.Default,
    )
}

val LocalAppShape = staticCompositionLocalOf {
    AppShape(
        small = RoundedCornerShape(0.dp),
        medium = RoundedCornerShape(0.dp),
        large = RoundedCornerShape(0.dp),
        pill = RoundedCornerShape(0.dp),
    )
}

val LocalAppSize = staticCompositionLocalOf {
    AppSize(
        xs = Dp.Unspecified,
        sm = Dp.Unspecified,
        md = Dp.Unspecified,
        lg = Dp.Unspecified,
        xl = Dp.Unspecified,
        buttonHeight = Dp.Unspecified,
        iconSize = Dp.Unspecified,
        screenPadding = Dp.Unspecified,
    )
}