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
    var cardContainer: Color,
    var onCardContainer: Color,
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
    val title: TextStyle,
    val body: TextStyle,
    val label: TextStyle,
)

@Immutable
data class AppShape(
    val container: CornerBasedShape,
    val pill: CornerBasedShape,
)

@Immutable
data class AppSize(
    val s: Dp,
    val m: Dp,
    val l: Dp,
    val xl: Dp,
    val xxl: Dp,
    val iconSize: Dp,
    val screenPadding: Dp,
)

val LocalAppColor = staticCompositionLocalOf {
    AppColor(
        background = Color.Unspecified,
        onBackground = Color.Unspecified,
        cardContainer = Color.Unspecified,
        onCardContainer = Color.Unspecified,
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
        title = TextStyle.Default,
        body = TextStyle.Default,
        label = TextStyle.Default,
    )
}

val LocalAppShape = staticCompositionLocalOf {
    AppShape(
        container = RoundedCornerShape(0.dp),
        pill = RoundedCornerShape(0.dp),
    )
}

val LocalAppSize = staticCompositionLocalOf {
    AppSize(
        s = Dp.Unspecified,
        m = Dp.Unspecified,
        l = Dp.Unspecified,
        xl = Dp.Unspecified,
        xxl = Dp.Unspecified,
        iconSize = Dp.Unspecified,
        screenPadding = Dp.Unspecified,
    )
}